package com.github.fdh911

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.sounds.SoundEvent

object Sounds {
    val crit = register("crit")
    val strong = register("strong")
    val weak = register("weak")
    val miss = register("miss")
    val epic = register("epic")
    val combo = arrayOf(
        null,
        register("combo1"),
        register("combo2"),
        register("combo3"),
        register("combo4"),
        register("combo5"),
        register("combo6"),
        register("combo7"),
        register("combo8"),
    )

    private fun register(name: String): SoundEvent {
        val id = Identifier.fromNamespaceAndPath("pvptools", name)
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id))
    }

    fun init() {}
}