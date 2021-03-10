package com.theonlytails.rubymod.client.render

import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.id
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.client.renderer.entity.model.SheepModel
import javax.annotation.Nonnull

/**
 * The model class for [RubySheepEntity].
 *
 * @author TheOnlyTails
 */
class RubySheepModel : SheepModel<RubySheepEntity>()

/**
 * The renderer class for [RubySheepEntity].
 *
 * @author TheOnlyTails
 */
class RubySheepRenderer(renderManagerIn: EntityRendererManager) :
	MobRenderer<RubySheepEntity, RubySheepModel>(renderManagerIn, RubySheepModel(), 0.7f) {

	init {
		addLayer(RubySheepWoolLayer(this))
	}

	@Nonnull
	override fun getTextureLocation(entity: RubySheepEntity) = id("textures/entity/ruby_sheep/ruby_sheep.png")
}
