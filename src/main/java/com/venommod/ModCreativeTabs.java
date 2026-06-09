package com.venommod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VenomMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> VENOM_TAB =
            TABS.register("venom_tab", () -> CreativeModeTab.builder()
                    .title(Component.literal("Venom Mod"))
                    .icon(() -> new ItemStack(ModItems.SYMBIONT.get()))
                    .displayItems((itemDisplayParams, output) -> {
                        output.accept(new ItemStack(ModItems.SYMBIONT.get()));
                    })
                    .build());

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
    }
}
