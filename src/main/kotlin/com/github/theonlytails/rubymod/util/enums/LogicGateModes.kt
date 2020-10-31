package com.github.theonlytails.rubymod.util.enums

import net.minecraft.util.IStringSerializable

enum class LogicGateModes(name: String) : IStringSerializable {
	AND("and"),
	OR("or");

	override fun toString() = this.name

	override fun getString() = this.name
}