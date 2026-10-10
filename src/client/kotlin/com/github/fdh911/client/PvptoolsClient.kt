package com.github.fdh911.client

import net.fabricmc.api.ClientModInitializer

object PvptoolsClient : ClientModInitializer {
	override fun onInitializeClient() {
		HitManager.init()
	}
}