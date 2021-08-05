package com.heipiao.wm.items;

import com.heipiao.wm.screen.TutorialScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TutorialItem extends Item{
    public TutorialItem(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if(worldIn.isClientSide){
            Minecraft.getInstance().setScreen(new TutorialScreen(new TranslatableComponent("wm.screen.tutorial")));
        }
        return InteractionResultHolder.consume(playerIn.getItemInHand(handIn));
    }
}
