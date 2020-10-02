package com.theonlytails.ruby.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.theonlytails.ruby.container.RubyBarrelContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RubyBarrelScreen extends ContainerScreen<RubyBarrelContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE =
            new ResourceLocation("ruby", "textures/gui/ruby_barrel/ruby_barrel.png");

    public RubyBarrelScreen(RubyBarrelContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 204;
        this.titleX = 8;
        this.titleY = 6;
        this.playerInventoryTitleX = 8;
        this.playerInventoryTitleY = 109;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        super.drawGuiContainerForegroundLayer(matrixStack, x, y);
        this.font.drawString(matrixStack,
                this.title.getString(), titleX, titleY, 4210752);
        this.font.drawString(matrixStack,
                this.playerInventory.getDisplayName().getString(),
                playerInventoryTitleX, playerInventoryTitleY, 4210752);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);

        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
            int x = (this.width - this.xSize) / 2;
            int y = (this.height - this.ySize) / 2;
            this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
        }
    }
}
