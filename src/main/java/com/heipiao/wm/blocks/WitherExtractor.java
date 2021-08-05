package com.heipiao.wm.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import com.heipiao.wm.pollute.PolluteUtils;
import com.heipiao.wm.tileentity.TileEntityTypeRegistry;
import com.heipiao.wm.tileentity.WitherExtractorTileEntity;

public class WitherExtractor extends BaseEntityBlock{
    // private static IntegerProperty LEVEL=IntegerProperty.create("level", 0, 81);
    public WitherExtractor() {
        super(Block.Properties
                .of(Material.STONE, MaterialColor.COLOR_BLUE)
                .requiresCorrectToolForDrops()
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1)
                .strength(5)
        );
        // this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, 0));
    }
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state){
        return new WitherExtractorTileEntity(pos,state);
    }
    // public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){
    //     ItemStack heldStack=player.getHeldItem(Hand.MAIN_HAND);
    //     int lvl=state.get(LEVEL);
    //     if(heldStack.getItem().equals(Items.NETHER_STAR)){
    //         if(lvl<81){
    //             BlockState newState=state.with(LEVEL, lvl+9);
    //             worldIn.setBlockState(pos, newState, 3);
    //         }
    //     }
    //     //player.sendMessage(new StringTextComponent("hello"),UUID.randomUUID());
    //     return ActionResultType.SUCCESS;
    // }
    // @Override
    // protected void fillStateContainer(StateContainer.Builder<Block,BlockState> builder){
    //     builder.add(LEVEL);
    //     super.fillStateContainer(builder);
    // }
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit){
        if(!worldIn.isClientSide() && handIn == InteractionHand.MAIN_HAND){
            WitherExtractorTileEntity witherExtractorTileEntity = (WitherExtractorTileEntity)worldIn.getBlockEntity(pos);
            NetworkHooks.openGui((ServerPlayer)player, witherExtractorTileEntity, (FriendlyByteBuf packetBuffer)->{
                packetBuffer.writeBlockPos(witherExtractorTileEntity.getBlockPos());
            });
        }
        return InteractionResult.SUCCESS;
    }
    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState state2, boolean p_60519_){
        WitherExtractorTileEntity blockEntity;
        BlockEntity entity=worldIn.getBlockEntity(pos);
        if(!(entity instanceof WitherExtractorTileEntity))return;
        blockEntity=(WitherExtractorTileEntity)entity;
        Containers.dropContents(worldIn, pos, blockEntity.getContainer());
        PolluteUtils.add(worldIn, blockEntity.getTank().getFluidAmount());
        blockEntity.setRemoved();
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, TileEntityTypeRegistry.witherExtractorTileEntity.get(), WitherExtractorTileEntity::tick);
    }
}
