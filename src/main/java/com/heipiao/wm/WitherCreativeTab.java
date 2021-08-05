package com.heipiao.wm;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class WitherCreativeTab extends CreativeModeTab {
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.NETHER_STAR);
    }
    public WitherCreativeTab(){
        super("wither_group");
    }
}
