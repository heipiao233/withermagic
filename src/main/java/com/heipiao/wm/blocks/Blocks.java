package com.heipiao.wm.blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import com.heipiao.wm.Utils;
public class Blocks {
    //Blocks has no class
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,Utils.modid);
    public static final RegistryObject<Block> netherStarBlock = BLOCKS.register("nether_star_block",
            ()->new Block(Block.Properties
                    .create(Material.ROCK)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
            )
    );
}
