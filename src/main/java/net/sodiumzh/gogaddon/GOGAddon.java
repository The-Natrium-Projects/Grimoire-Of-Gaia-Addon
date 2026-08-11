package net.sodiumzh.gogaddon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sodiumzh.gogaddon.registry.GOGAddonBlocks;
import net.sodiumzh.gogaddon.registry.GOGAddonConfigs;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityAttributes;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityComponents;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityTypes;
import net.sodiumzh.gogaddon.registry.GOGAddonItems;

@Mod(GOGAddon.MOD_ID)
public class GOGAddon {
    public static final String MOD_ID = "gogaddon";

    public GOGAddon() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GOGAddonConfigs.CONFIG);
        bus.addListener(GOGAddonConfigs::loadConfig);

        GOGAddonEntityTypes.ENTITY_TYPES.register(bus);
        GOGAddonBlocks.REG.register(bus);
        GOGAddonItems.REG.register(bus);
        GOGAddonEntityComponents.COLLECTION.merge();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

}
