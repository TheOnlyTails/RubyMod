@file:Suppress("DEPRECATION")

package com.theonlytails.rubymod.client.gui

import com.mojang.blaze3d.matrix.MatrixStack
import com.mojang.blaze3d.systems.RenderSystem
import com.theonlytails.rubymod.blocks.RubyBarrel
import com.theonlytails.rubymod.containers.RubyBarrelContainer
import com.theonlytails.rubymod.id
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.util.text.ITextComponent
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

/**
 * The GUI/screen class for [RubyBarrel].
 *
 * @author TheOnlyTails
 */
@OnlyIn(Dist.CLIENT)
class RubyBarrelScreen(screenContainer: RubyBarrelContainer, inv: PlayerInventory, titleIn: ITextComponent) :
	ContainerScreen<RubyBarrelContainer>(screenContainer, inv, titleIn) {

	init {
		leftPos = 0
		topPos = 0
		imageWidth = 176
		imageHeight = 204
		titleLabelX = 8
		titleLabelY = 6
		inventoryLabelX = 8
		inventoryLabelY = 110
	}

	override fun render(matrixStack: MatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
		this.renderBackground(matrixStack)
		super.render(matrixStack, mouseX, mouseY, partialTicks)
		this.renderTooltip(matrixStack, mouseX, mouseY)
	}

	override fun renderLabels(matrixStack: MatrixStack, x: Int, y: Int) {
		super.renderLabels(matrixStack, x, y)
		font.draw(matrixStack, title.string, titleLabelX.toFloat(), titleLabelY.toFloat(), 4210752)
		font.draw(
			matrixStack,
			inventory.displayName.string,
			inventoryLabelX.toFloat(),
			inventoryLabelY.toFloat(),
			4_210_752
		)
	}

	override fun renderBg(
		matrixStack: MatrixStack,
		partialTicks: Float,
		mouseX: Int,
		mouseY: Int,
	) {
		RenderSystem.color4f(1f, 1f, 1f, 1f)
		if (minecraft != null) {
			minecraft?.getTextureManager()?.bind(id("textures/gui/ruby_barrel/ruby_barrel.png"))
			val x = (width - xSize) / 2
			val y = (height - ySize) / 2
			this.blit(matrixStack, x, y, 0, 0, xSize, ySize)
		}
	}
}
