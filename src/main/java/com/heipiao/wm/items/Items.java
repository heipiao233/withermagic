package com.heipiao.wm.items;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraftforge.registries.ForgeRegistries;
import com.heipiao.wm.Utils;
import com.heipiao.wm.blocks.Blocks;
import com.heipiao.wm.fluids.Fluids;

public class Items {
    public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS,Utils.modid);
    public static final RegistryObject<Item> NETHER_STAR_NUGGET=ITEMS.register(
        "nether_star_nugget",
        ()->new Item(new Item.Properties().tab(Utils.group)));
    public static final RegistryObject<Item> NETHER_STAR_BLOCK_ITEM=ITEMS.register(
        "nether_star_block",
        ()->new BlockItem(Blocks.NETHER_STAR_BLOCK.get(),new Item.Properties()
                .tab(Utils.group)));
    public static final RegistryObject<Item> WITHER_EXTRACTOR_ITEM=ITEMS.register(
        "wither_extractor",
        ()->new BlockItem(Blocks.WITHER_EXTRACTOR.get(),new Item.Properties()
            .tab(Utils.group)));
    public static final RegistryObject<Item> WITHER_FLUID_BUCKET=ITEMS.register(
        "wither_fluid_bucket",
        ()->new BucketItem(Fluids.WITHER_FLUID_SOURCE,new Item.Properties()
                .tab(Utils.group).stacksTo(1)));
    public static final RegistryObject<Item> TUTORIAL=ITEMS.register(
        "tutorial",
        ()->new TutorialItem(new Item.Properties().tab(Utils.group)));
}
