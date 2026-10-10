package com.github.fdh911.client

import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component

object Chat {
    fun send(msg: String) {
        Minecraft.getInstance().gui.chat.addClientSystemMessage(Component.literal(msg))
    }
}