package com.heipiao.wm.container;

import com.heipiao.wm.tileentity.WitherExtractorTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.capability.templates.FluidTank;
public class WitherExtractorContainer extends AbstractContainerMenu {
    private ContainerData intArray;
    private WitherExtractorTileEntity tileEntity;

    public WitherExtractorContainer(int id, Inventory playerInventory, BlockPos pos, Level world, ContainerData intArray) {
        super(ContainerTypeRegistry.witherExtractorContainer.get(), id);
        this.intArray = intArray;
        addDataSlots(this.intArray);
        tileEntity = (WitherExtractorTileEntity) world.getBlockEntity(pos);
        this.addSlot(new Slot(tileEntity.getContainer(), 0, 80, 32){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return tileEntity.getContainer().canPlaceItem(0, stack);
            }
        });
        this.addSlot(new Slot(tileEntity.getContainer(), 1, 40, 20){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return tileEntity.getContainer().canPlaceItem(1, stack);
            }
        });
        this.addSlot(new Slot(tileEntity.getContainer(), 2, 120, 20){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return tileEntity.getContainer().canPlaceItem(2, stack);
            }
        });
        layoutPlayerInventorySlots(playerInventory, 8, 84);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return true;
    }
    public FluidTank getTank(){
        return this.tileEntity.getTank();
    }
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 1) {
                if (!this.moveItemStackTo(itemstack1, 1, this.slots.size(), true)) {
                return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    private int addSlotRange(Container inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(Container inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(Container inventory, int leftCol, int topRow) {
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    public ContainerData getIntArray() {
        return intArray;
    }
}
