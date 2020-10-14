package com.theonlytails.ruby.registries;

import com.theonlytails.ruby.RubyMod;
import com.theonlytails.ruby.containers.RubyBarrelContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeRegistry {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, RubyMod.MOD_ID);

    public static final RegistryObject<ContainerType<RubyBarrelContainer>> RUBY_BARREL = CONTAINER_TYPES.register(
            "ruby_barrel",
            () -> IForgeContainerType.create(RubyBarrelContainer::new)
    );
}
