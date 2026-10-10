package com.github.fdh911

import net.fabricmc.api.ModInitializer
import net.minecraft.resources.Identifier
import org.slf4j.LoggerFactory

object Pvptools : ModInitializer {
	const val MOD_ID: String = "pvptools"
	private val LOGGER = LoggerFactory.getLogger(MOD_ID)
	fun id(path: String): Identifier
			= Identifier.fromNamespaceAndPath(MOD_ID, path)

	override fun onInitialize() {
		Sounds.init()
	}
}
