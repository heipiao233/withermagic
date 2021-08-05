package com.heipiao.wm.tileentity;

import com.heipiao.wm.Utils;
import com.heipiao.wm.blocks.Blocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeRegistry {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Utils.modid);
    public static final RegistryObject<BlockEntityType<WitherExtractorTileEntity>> witherExtractorTileEntity = TILE_ENTITIES.register("wither_extractor", 
        ()->BlockEntityType.Builder.of(WitherExtractorTileEntity::new, Blocks.WITHER_EXTRACTOR.get()).build(null)
    );
}
