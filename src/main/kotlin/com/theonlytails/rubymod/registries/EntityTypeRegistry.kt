package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.entities.RubySheepEntity
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
	val ENTITY_TYPES =
		KDeferredRegister(ForgeRegistries.ENTITIES, RubyMod.MOD_ID)

	val RUBY_SHEEP: EntityType<RubySheepEntity> by ENTITY_TYPES.registerObject("ruby_sheep") {
		EntityType.Builder.create(::RubySheepEntity,
			EntityClassification.CREATURE).size(0.625f, 1.25f)
			.build(RubyMod.id("ruby_sheep").toString())
	}
}
