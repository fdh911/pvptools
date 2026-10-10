package com.github.fdh911.client

import net.minecraft.client.Minecraft
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.sounds.SoundEvent

fun SoundEvent?.play() {
    if(this == null) return
    Minecraft.getInstance().soundManager.play(SimpleSoundInstance.forUI(this, 1.0f, 100.0f))
}