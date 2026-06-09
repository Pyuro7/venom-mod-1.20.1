package com.venommod;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.sounds.SoundSource;

@Mod.EventBusSubscriber(modid = VenomMod.MOD_ID, value = Dist.CLIENT)
public class VenomEffects {
    private static int tick = 0;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null) return;
        if (!VenomState.isActive(player)) {
            tick = 0;
            return;
        }

        tick++;

        // Apply effects
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 220, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 220, 0, false, false));

        // Ambient sounds
        if (tick % 100 == 0) {
            player.level().playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    ModSounds.VENOM_ROAR.get(),
                    SoundSource.PLAYERS,
                    0.5f,
                    0.8f + (float) Math.random() * 0.4f
            );
        }

        if (tick > 1000) tick = 0;
    }
}
