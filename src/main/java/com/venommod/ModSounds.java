package com.venommod;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VenomMod.MOD_ID);

    public static final RegistryObject<SoundEvent> VENOM_TRANSFORM =
            SOUND_EVENTS.register("venom_transform",
                    () -> SoundEvent.createVariableRangeEvent(
                            new ResourceLocation(VenomMod.MOD_ID, "venom_transform")));

    public static final RegistryObject<SoundEvent> VENOM_ATTACK =
            SOUND_EVENTS.register("venom_attack",
                    () -> SoundEvent.createVariableRangeEvent(
                            new ResourceLocation(VenomMod.MOD_ID, "venom_attack")));

    public static final RegistryObject<SoundEvent> VENOM_ROAR =
            SOUND_EVENTS.register("venom_roar",
                    () -> SoundEvent.createVariableRangeEvent(
                            new ResourceLocation(VenomMod.MOD_ID, "venom_roar")));

    public static final RegistryObject<SoundEvent> TENTACLE_SLASH =
            SOUND_EVENTS.register("tentacle_slash",
                    () -> SoundEvent.createVariableRangeEvent(
                            new ResourceLocation(VenomMod.MOD_ID, "tentacle_slash")));

    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
