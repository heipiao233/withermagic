package com.heipiao.wm.items;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import com.heipiao.wm.Utils;
import com.heipiao.wm.blocks.Blocks;

public class Items {
    public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS,Utils.modid);
    public static final Item netherStarNuggetItem=new Item(new Item.Properties().group(Utils.group));
    public static final RegistryObject<Item> netherStarBlockItem=ITEMS.register(
            "nether_star_block",
            ()->new BlockItem(Blocks.netherStarBlock.get(),new Item.Properties()
                    .group(Utils.group)));
}
