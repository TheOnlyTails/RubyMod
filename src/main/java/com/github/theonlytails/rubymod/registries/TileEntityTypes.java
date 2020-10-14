package com.github.theonlytails.rubymod.registries;

import com.github.theonlytails.rubymod.RubyMod;
import com.github.theonlytails.rubymod.tileentities.RubyBarrelTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, RubyMod.MOD_ID);

    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<TileEntityType<RubyBarrelTileEntity>> RUBY_BARREL = TILE_ENTITIES.register(
            "ruby_barrel",
            () -> TileEntityType.Builder
                    .create(RubyBarrelTileEntity::new,
                            BlockRegistry.RUBY_BARREL.get())
                    .build(null)
    );
}
