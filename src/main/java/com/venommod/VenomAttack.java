package com.venommod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundSource;

public class VenomAttack {
    private static final float ATTACK_RANGE = 8.0f;
    private static final float ATTACK_DAMAGE = 8.0f;
    private static final float KNOCKBACK = 1.5f;

    public static void performAttack(Player player) {
        if (!VenomState.isActive(player)) {
            return;
        }

        // Play attack sound
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                ModSounds.TENTACLE_SLASH.get(), SoundSource.PLAYERS, 1.0f, 1.0f);

        // Get entities in range
        AABB attackBox = new AABB(
                player.getX() - ATTACK_RANGE,
                player.getY() - ATTACK_RANGE,
                player.getZ() - ATTACK_RANGE,
                player.getX() + ATTACK_RANGE,
                player.getY() + ATTACK_RANGE,
                player.getZ() + ATTACK_RANGE
        );

        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class, attackBox)) {
            if (entity == player) continue;

            // Damage entity
            entity.hurt(player.level().damageSources().playerAttack(player), ATTACK_DAMAGE);

            // Apply knockback
            double dx = entity.getX() - player.getX();
            double dz = entity.getZ() - player.getZ();
            double distance = Math.sqrt(dx * dx + dz * dz);

            if (distance > 0) {
                entity.setDeltaMovement(
                        entity.getDeltaMovement().x + (dx / distance) * KNOCKBACK,
                        entity.getDeltaMovement().y + 0.5,
                        entity.getDeltaMovement().z + (dz / distance) * KNOCKBACK
                );
            }
        }
    }
}
