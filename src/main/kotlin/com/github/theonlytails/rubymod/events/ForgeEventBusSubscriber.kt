package com.github.theonlytails.rubymod.events

import com.github.theonlytails.rubymod.RubyMod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber(modid = RubyMod.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
object ForgeEventBusSubscriber