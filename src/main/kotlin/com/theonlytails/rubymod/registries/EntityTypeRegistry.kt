package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.id
import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom entities, i.e. mobs.
 *
 * @author TheOnlyTails
 */
object EntityTypeRegistry {
	val entityTypes = KDeferredRegister(ForgeRegistries.ENTITIES, MOD_ID)

	val rubySheep: EntityType<RubySheepEntity> by entityTypes.registerObject("ruby_sheep") {
		EntityType.Builder.create(::RubySheepEntity,
			EntityClassification.CREATURE).size(0.625f, 1.25f)
			.build(id("ruby_sheep").toString())
	}
}
