package com.heipiao.wm.blocks;

import com.heipiao.wm.Utils;
import com.heipiao.wm.fluids.Fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,Utils.modid);
    public static final RegistryObject<Block> NETHER_STAR_BLOCK = BLOCKS.register("nether_star_block",
            ()->new Block(Block.Properties
                    .of(new Material.Builder(MaterialColor.COLOR_BLUE).build())
                    .requiresCorrectToolForDrops()
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
                    .strength(5)
            )
    );
    public static final RegistryObject<Block> WITHER_EXTRACTOR = BLOCKS.register("wither_extractor",
            ()->new WitherExtractor()
    );
    public static final RegistryObject<LiquidBlock> WITHER_FLUID_BLOCK = BLOCKS.register("wither_fluid",
            ()->new LiquidBlock(Fluids.WITHER_FLUID_SOURCE, Block.Properties.of(Material.WATER,MaterialColor.NETHER).noCollission().strength(100F).noDrops()){
                @Override
                public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn){
					if(entityIn instanceof LivingEntity){
                        ((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.WITHER,20,1));
                    }
                }
                public boolean isPathfindable(BlockState p_54704_, net.minecraft.world.level.BlockGetter p_54705_, BlockPos p_54706_, net.minecraft.world.level.pathfinder.PathComputationType p_54707_) {
                        return false;
                };
            }
    );
}
