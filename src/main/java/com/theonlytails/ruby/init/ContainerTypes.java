package com.theonlytails.ruby.init;

import com.theonlytails.ruby.TheOnlyTails;
import com.theonlytails.ruby.container.RubyBarrelContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypes {
    public static DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, TheOnlyTails.MOD_ID);

    public static final RegistryObject<ContainerType<RubyBarrelContainer>> RUBY_BARREL = CONTAINERS.register("ruby_barrel",
            () -> IForgeContainerType.create(RubyBarrelContainer::new));

    public static void init() {
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
