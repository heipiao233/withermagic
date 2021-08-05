package com.heipiao.wm.screen;

// import com.heipiao.wm.Utils;
import com.heipiao.wm.container.WitherExtractorContainer;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.FluidStack;

public class WitherExtractorScreen extends AbstractContainerScreen<WitherExtractorContainer>{
    private final int textureWidth = 176;
    private final int textureHeight = 166;
    // private final ResourceLocation WITHER_EXTRACTOR_RESOURCE = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");
    public WitherExtractorScreen(WitherExtractorContainer container, Inventory inv, Component titleIn) {
        super(container, inv, titleIn);
        this.imageWidth = textureWidth;
        this.imageHeight = textureHeight;
    }
    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
        // this.renderBackground(matrixStack);
        // this.minecraft.getTextureManager().bindTexture(WITHER_EXTRACTOR_RESOURCE);
        // int i = (this.width - this.xSize) / 2;
        // int j = (this.height - this.ySize) / 2;
        // blit(matrixStack, i, j, 0, 0, xSize, ySize, this.textureWidth, textureHeight);
    }
    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderDirtBackground(0);//招收美术，绘制背景图等材质
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderTooltip(matrixStack, mouseX, mouseY);
        FluidStack fluidStack = menu.getTank().getFluid();
        drawCenteredString(matrixStack, this.font, fluidStack.getDisplayName().getString()+"*"+String.valueOf(fluidStack.getAmount())+Language.getInstance().getOrDefault("fluidamount.mb"), this.width/2, 0, 0xFFFFFF);
    }
}
