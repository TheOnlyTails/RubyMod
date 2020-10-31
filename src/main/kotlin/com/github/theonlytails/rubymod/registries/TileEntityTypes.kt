package com.github.theonlytails.rubymod.registries

import com.github.theonlytails.rubymod.RubyMod
import com.github.theonlytails.rubymod.tileentities.RubyBarrelTileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom tile entities.
 *
 * @author TheOnlyTails
 */
object TileEntityTypes {
	val TILE_ENTITIES = KDeferredRegister(ForgeRegistries.TILE_ENTITIES, RubyMod.MOD_ID)

	val RUBY_BARREL: TileEntityType<RubyBarrelTileEntity> by TILE_ENTITIES.register("ruby_barrel") {
		@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
		TileEntityType.Builder
			.create(::RubyBarrelTileEntity, BlockRegistry.RUBY_BARREL)
			.build(null)
	}
}
