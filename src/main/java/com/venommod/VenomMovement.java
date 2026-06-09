package com.venommod;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VenomMod.MOD_ID, value = Dist.CLIENT)
public class VenomMovement {
    private static boolean isClimbing = false;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (player.level().isClientSide) return;
        if (!VenomState.isActive(player)) {
            isClimbing = false;
            return;
        }

        boolean touchingWall = player.horizontalCollision;

        if (!touchingWall) {
            isClimbing = false;
            return;
        }

        if (!isClimbing) {
            isClimbing = true;
            player.fallDistance = 0;
        }

        // Wall climb mechanics
        Minecraft mc = Minecraft.getInstance();
        boolean jumpPressed = mc.options.keyJump.isDown();
        boolean sneakPressed = mc.options.keyShift.isDown();

        double speed = 0.12;
        double y = 0;

        if (jumpPressed) {
            y = speed;
        } else if (sneakPressed) {
            y = -speed;
        }

        player.setDeltaMovement(0, y, 0);
        player.fallDistance = 0;
    }

    public static boolean isClimbing() {
        return isClimbing;
    }
}
