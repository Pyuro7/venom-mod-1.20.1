package com.venommod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod("venommod")
public class VenomMod {
    public static final String MOD_ID = "venommod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VenomMod(IEventBus modEventBus) {
        LOGGER.info("VenomMod initializing...");
        
        // Initialize GeckoLib
        GeckoLib.initialize();
        
        // Register items and creative tabs
        modEventBus.addListener(this::commonSetup);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModSounds.register(modEventBus);
        
        LOGGER.info("VenomMod initialized successfully!");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Venom Mod common setup!");
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Client setup complete!");
        }
    }
}
