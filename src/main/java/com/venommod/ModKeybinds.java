package com.venommod;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = VenomMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModKeybinds {
    public static KeyMapping TOGGLE_VENOM;
    public static KeyMapping VENOM_ATTACK;

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        TOGGLE_VENOM = new KeyMapping(
                "key.venom.toggle",
                GLFW.GLFW_KEY_V,
                "key.categories.venom"
        );

        VENOM_ATTACK = new KeyMapping(
                "key.venom.attack",
                GLFW.GLFW_KEY_R,
                "key.categories.venom"
        );

        event.register(TOGGLE_VENOM);
        event.register(VENOM_ATTACK);
    }
}

@Mod.EventBusSubscriber(modid = VenomMod.MOD_ID, value = Dist.CLIENT)
class ModKeybindsClient {
    @SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) return;

        while (ModKeybinds.TOGGLE_VENOM.consumeClick()) {
            VenomState.toggle(mc.player);
        }

        while (ModKeybinds.VENOM_ATTACK.consumeClick()) {
            VenomState.attack(mc.player);
        }
    }
}
