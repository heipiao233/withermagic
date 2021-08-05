package com.heipiao.wm.tileentity;

import com.heipiao.wm.container.WitherExtractorContainer;
import com.heipiao.wm.pollute.PolluteUtils;
// import com.heipiao.wm.inventory.HandlerInventory;
import com.heipiao.wm.recipes.RecipeRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.RangedWrapper;

public class WitherExtractorTileEntity extends BlockEntity implements MenuProvider{
    private ContainerData intArray;
    private byte progress=0;
    private final BlockPos[] targetPoses=new BlockPos[]{
        worldPosition.below(),
        worldPosition.east(),
        worldPosition.west(),
        worldPosition.north(),
        worldPosition.south()
    };
    private final FluidTank tank = new FluidTank(10000);
    private final Container inventory = new SimpleContainer(3){
        public boolean canPlaceItem(int index, ItemStack stack) {
            switch(index){
                case 0:
                    return !level.getRecipeManager().getRecipeFor(RecipeRegistry.WITHER_EXTRACTOR_TYPE, new SimpleContainer(stack), level).isEmpty();
                case 1:
                    return stack.getItem() instanceof BucketItem;
                case 2:
                    return false;
            }
            throw new IllegalArgumentException();
        };
    };
    private final IItemHandlerModifiable invCapa=new RangedWrapper(new InvWrapper(this.inventory), 0, 1);
    public WitherExtractorTileEntity(BlockPos pos, BlockState state) {
        super(TileEntityTypeRegistry.witherExtractorTileEntity.get(), pos, state);
        intArray = new SimpleContainerData(1);
    }
    @Override
    public BaseComponent getDisplayName() {
        return new TranslatableComponent("gui.wm.wither_extractor");
    }
    @Override
    public AbstractContainerMenu createMenu(int sycID, Inventory inventory, Player player) {
        return new WitherExtractorContainer(sycID, inventory, worldPosition, level, intArray);
    }
    @Override
    public void load(CompoundTag nbt){
        inventory.setItem(0, ItemStack.of((CompoundTag)nbt.get("item")));
        this.tank.readFromNBT(nbt.getCompound("tank"));
        this.progress=nbt.getByte("progress");
        super.load(nbt);
    }
    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.put("item", inventory.getItem(0).save(new CompoundTag()));
        nbt.put("tank",this.tank.writeToNBT(new CompoundTag()));
        nbt.putByte("progress",this.progress);
        return super.save(nbt);
    }

    public Container getContainer() {
        return inventory;
    }
    public static void tick(Level level, BlockPos pos, BlockState state, WitherExtractorTileEntity tileEntity){
        // if (!world.isRemote) {
            tileEntity.intArray.set(0, tileEntity.progress);
            if(tileEntity.progress++>=100){
                tileEntity.progress=0;
                level.getRecipeManager().getRecipeFor(RecipeRegistry.WITHER_EXTRACTOR_TYPE, tileEntity.inventory, level).ifPresent((recipe)->{
                        tileEntity.intArray.set(0,tileEntity.tank.getFluidAmount());
                        if(tileEntity.tank.getCapacity()-tileEntity.tank.getFluidAmount()>=recipe.getOutputFluid().getAmount()){
                            tileEntity.tank.fill(recipe.getOutputFluid(), FluidAction.EXECUTE);
                            tileEntity.inventory.getItem(0).shrink(recipe.getInputItem().getCount());
                            PolluteUtils.add(level,recipe.getOutputFluid().getAmount()*0.1);
                        }
                    }
                );
            }
            for(BlockPos targetPos:tileEntity.targetPoses){
                BlockEntity tile = level.getBlockEntity(targetPos);
                if(tile!=null){
                    tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                        .ifPresent((s) -> {
                            s.fill(tileEntity.tank.getFluid(),FluidAction.EXECUTE);
                            tileEntity.tank.drain(tileEntity.tank.getFluidAmount(),FluidAction.EXECUTE);
                        });
                }
            }
        // }
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.of(()->invCapa).cast();
        }
        return super.getCapability(cap, side);
    }
    public FluidTank getTank() {
        return tank;
    }
}
