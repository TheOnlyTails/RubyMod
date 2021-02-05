@file:Suppress("DEPRECATION")

package com.theonlytails.rubymod.client.gui

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.systems.RenderSystem
import com.theonlytails.rubymod.blocks.RubyBarrelBlock
import com.theonlytails.rubymod.containers.RubyBarrelContainer
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.util.text.ITextComponent
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

/**
 * The GUI/screen class for [RubyBarrelBlock].
 *
 * @author TheOnlyTails
 */
@OnlyIn(Dist.CLIENT)
class RubyBarrelScreen(screenContainer: RubyBarrelContainer, inv: PlayerInventory, titleIn: ITextComponent) :
	ContainerScreen<RubyBarrelContainer>(screenContainer, inv, titleIn) {

	init {
		guiLeft = 0
		guiTop = 0
		xSize = 176
		ySize = 204
		titleX = 8
		titleY = 6
		playerInventoryTitleX = 8
		playerInventoryTitleY = 110
	}

	override fun render(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
		this.renderBackground(matrixStack)
		super.render(matrixStack, mouseX, mouseY, partialTicks)
		renderHoveredTooltip(matrixStack, mouseX, mouseY)
	}

	override fun drawGuiContainerForegroundLayer(matrixStack: MatrixStack, x: Int, y: Int) {
		super.drawGuiContainerForegroundLayer(matrixStack, x, y)
		font.drawString(matrixStack, title.string, titleX.toFloat(), titleY.toFloat(), 4210752)
		font.drawString(
			matrixStack,
			playerInventory.displayName.string,
			playerInventoryTitleX.toFloat(),
			playerInventoryTitleY.toFloat(),
			4210752
		)
	}

	override fun drawGuiContainerBackgroundLayer(
		matrixStack: MatrixStack,
		partialTicks: Float,
		mouseX: Int,
		mouseY: Int,
	) {
		RenderSystem.color4f(1f, 1f, 1f, 1f)
		if (minecraft != null) {
			minecraft!!.getTextureManager().bindTexture(BACKGROUND_TEXTURE)
			val x = (width - xSize) / 2
			val y = (height - ySize) / 2
			this.blit(matrixStack, x, y, 0, 0, xSize, ySize)
		}
	}

	companion object {
		private val BACKGROUND_TEXTURE = com.theonlytails.rubymod.RubyMod.id("textures/gui/ruby_barrel/ruby_barrel.png")
	}
}
