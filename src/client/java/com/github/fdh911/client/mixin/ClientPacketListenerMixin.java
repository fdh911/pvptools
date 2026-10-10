package com.github.fdh911.client.mixin;

import com.github.fdh911.client.HitManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(method = "handleAnimate", at = @At("TAIL"))
    private void mymod$onAnimate(ClientboundAnimatePacket packet, CallbackInfo ci) {
        if(Minecraft.getInstance().level == null)
            return;
        if (packet.getAction() == ClientboundAnimatePacket.CRITICAL_HIT) {
            Entity target = Minecraft.getInstance().level.getEntity(packet.getId());
            if(target != null)
                HitManager.INSTANCE.onCrit(target);
        }
    }

    @Inject(method = "handleDamageEvent", at = @At("TAIL"))
    private void mymod$onDamage(ClientboundDamageEventPacket packet, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;
        int selfId = mc.player.getId();

        if (packet.entityId() == selfId) {
            HitManager.INSTANCE.onHurt();
        } else if (packet.sourceCauseId() == selfId && packet.sourceDirectId() == selfId) {
            Entity target = mc.level.getEntity(packet.entityId());
            if (target != null) HitManager.INSTANCE.onHit(target);
        }
    }
}