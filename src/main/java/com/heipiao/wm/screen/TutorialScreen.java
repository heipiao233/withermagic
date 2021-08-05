package com.heipiao.wm.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class TutorialScreen extends Screen{
    public TutorialScreen(Component titleIn) {
        super(titleIn);
    }
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderDirtBackground(0);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
