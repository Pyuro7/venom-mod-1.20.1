package com.venommod;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.HashSet;
import java.util.UUID;

public class VenomState {
    private static final HashSet<UUID> INFECTED = new HashSet<>();
    private static final HashSet<UUID> ACTIVE = new HashSet<>();

    public static void infect(Player player) {
        INFECTED.add(player.getUUID());
        player.displayClientMessage(Component.literal("🧬 Du bist jetzt infiziert!"), true);
    }

    public static boolean isInfected(Player player) {
        return INFECTED.contains(player.getUUID());
    }

    public static boolean isActive(Player player) {
        return ACTIVE.contains(player.getUUID());
    }

    public static void toggle(Player player) {
        if (!isInfected(player)) {
            player.displayClientMessage(Component.literal("❌ Du bist nicht infiziert!"), true);
            return;
        }

        UUID id = player.getUUID();
        if (ACTIVE.contains(id)) {
            ACTIVE.remove(id);
            player.displayClientMessage(Component.literal("🕷 Venom DEAKTIVIERT"), true);
        } else {
            ACTIVE.add(id);
            player.displayClientMessage(Component.literal("🕷 Venom AKTIVIERT"), true);
        }
    }

    public static void attack(Player player) {
        if (!isActive(player)) {
            return;
        }
        VenomAttack.performAttack(player);
    }
}
