package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.MOD_ID
import com.theonlytails.rubymod.tileentities.RubyBarrelTileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

/**
 * Registers custom tile entities.
 *
 * @author TheOnlyTails
 */
object TileEntityTypes {
	val tileEntities = KDeferredRegister(ForgeRegistries.TILE_ENTITIES, MOD_ID)

	val rubyBarrel: TileEntityType<RubyBarrelTileEntity> by tileEntities.registerObject("ruby_barrel") {
		@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
		TileEntityType.Builder
			.create(::RubyBarrelTileEntity, BlockRegistry.rubyBarrel)
			.build(null)
	}
}
