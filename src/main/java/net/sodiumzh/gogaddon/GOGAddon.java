package net.sodiumzh.gogaddon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityAttributes;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityTypes;

@Mod(GOGAddon.MOD_ID)
public class GOGAddon {
    public static final String MOD_ID = "gogaddon";

    public GOGAddon() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        GOGAddonEntityTypes.ENTITY_TYPES.register(bus);
        GOGAddonEntityAttributes.COLLETION.merge();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

}
