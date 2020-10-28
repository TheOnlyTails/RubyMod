package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.entities.RubySheepEntity
import net.minecraft.entity.EntityClassification
import net.minecraft.entity.EntityType
import net.minecraft.util.ResourceLocation
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

	val RUBY_SHEEP: EntityType<RubySheepEntity> by ENTITY_TYPES.register("ruby_sheep") {
		EntityType.Builder.create(::RubySheepEntity,
			EntityClassification.CREATURE).size(0.625f, 1.25f)
			.build(ResourceLocation(RubyMod.MOD_ID, "ruby_sheep").toString())
	}
}