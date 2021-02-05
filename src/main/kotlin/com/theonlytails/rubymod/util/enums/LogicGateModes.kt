package com.theonlytails.rubymod.util.enums

import net.minecraft.util.IStringSerializable

enum class LogicGateModes(val type: String, val function: (Boolean, Boolean) -> Int) : IStringSerializable {
	OR("or", { isFirstInputOn, isSecondInputOn -> if (isFirstInputOn || isSecondInputOn) 15 else 0 }),
	AND("and", { isFirstInputOn, isSecondInputOn -> if (isFirstInputOn && isSecondInputOn) 15 else 0 });

	override fun getString() = this.type

	operator fun invoke(firstInput: Boolean, secondInput: Boolean) = this.function(firstInput, secondInput)
}