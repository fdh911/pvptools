package com.github.fdh911.client

import com.github.fdh911.Sounds
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.HitResult
import java.lang.Integer.min

object HitManager {
    private var target: Entity? = null

    private var combo = 0

    init {
        ClientPreAttackCallback.EVENT.register { client, player, clickCount ->
            if(clickCount <= 0)
                return@register false

            if(client.hitResult?.type != HitResult.Type.ENTITY) {
                Sounds.miss.play()
                combo = 0
            }

            return@register false
        }

        AttackEntityCallback.EVENT.register { player, level, interactionHand, entity, entityHitResult ->
            target = entity

            return@register InteractionResult.PASS
        }
    }

    fun onConfirmedHit(wasCrit: Boolean) {
        if(wasCrit) {
            Sounds.crit.play()
        }
        else {
            Sounds.strong.play()
        }
        Sounds.combo[combo].play()
        combo++
        combo = min(combo, 8)
    }

    fun onHurt() {
        combo = 0
    }

    fun onHit(entity: Entity) {
        if(entity.id != target?.id)
            return
        onConfirmedHit(wasCrit = false)
    }

    fun onCrit(entity: Entity) {
        if(entity.id != target?.id)
            return
        onConfirmedHit(wasCrit = true)
    }

    fun init() {}
}