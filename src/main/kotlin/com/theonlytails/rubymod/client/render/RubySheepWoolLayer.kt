package com.theonlytails.rubymod.client.render

import com.mojang.blaze3d.matrix.MatrixStack
import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.id
import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.client.renderer.entity.IEntityRenderer
import net.minecraft.client.renderer.entity.layers.LayerRenderer
import net.minecraft.client.renderer.entity.model.SheepWoolModel
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.item.DyeColor
import javax.annotation.Nonnull

/**
 * The renderer class for the wool layer of [RubySheepEntity].
 *
 * @author TheOnlyTails
 */
class RubySheepWoolLayer(rendererIn: IEntityRenderer<RubySheepEntity, RubySheepModel>) :
	LayerRenderer<RubySheepEntity, RubySheepModel>(rendererIn) {
	private val sheepModel = SheepWoolModel<RubySheepEntity>()

	override fun render(
		@Nonnull matrixStackIn: MatrixStack,
		bufferIn: IRenderTypeBuffer,
		packedLightIn: Int,
		entitylivingbaseIn: RubySheepEntity,
		limbSwing: Float,
		limbSwingAmount: Float,
		partialTicks: Float,
		ageInTicks: Float,
		netHeadYaw: Float,
		headPitch: Float,
	) {
		if (!entitylivingbaseIn.isSheared && !entitylivingbaseIn.isInvisible) {
			val f: Float
			val f1: Float
			val f2: Float
			if (entitylivingbaseIn.hasCustomName() && "jeb_" == entitylivingbaseIn.name.contents) {
				val i = entitylivingbaseIn.tickCount / 25 + entitylivingbaseIn.id
				val j = DyeColor.values().size
				val k = i % j
				val l = (i + 1) % j
				val f3 = ((entitylivingbaseIn.tickCount % 25).toFloat() + partialTicks) / 25
				val dyeRgbOfK = SheepEntity.getColorArray(DyeColor.byId(k))
				val dyeRgbOfL = SheepEntity.getColorArray(DyeColor.byId(l))
				f = dyeRgbOfK[0] * (1.0f - f3) + dyeRgbOfL[0] * f3
				f1 = dyeRgbOfK[1] * (1.0f - f3) + dyeRgbOfL[1] * f3
				f2 = dyeRgbOfK[2] * (1.0f - f3) + dyeRgbOfL[2] * f3
			} else {
				val dyeRgb = SheepEntity.getColorArray(entitylivingbaseIn.color)
				f = dyeRgb[0]
				f1 = dyeRgb[1]
				f2 = dyeRgb[2]
			}

			coloredCutoutModelCopyLayerRender(
				parentModel,
				sheepModel,
				id("textures/entity/ruby_sheep/ruby_sheep_fur.png"),
				matrixStackIn,
				bufferIn,
				packedLightIn,
				entitylivingbaseIn,
				limbSwing,
				limbSwingAmount,
				ageInTicks,
				netHeadYaw,
				headPitch,
				partialTicks,
				f,
				f1,
				f2)
		}
	}
}
