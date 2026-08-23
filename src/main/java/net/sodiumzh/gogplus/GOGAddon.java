package net.sodiumzh.gogplus;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sodiumzh.gogplus.registry.GOGAddonBlocks;
import net.sodiumzh.gogplus.registry.GOGAddonConfigs;
import net.sodiumzh.gogplus.registry.GOGAddonEntityAttributes;
import net.sodiumzh.gogplus.registry.GOGAddonEntityComponents;
import net.sodiumzh.gogplus.registry.GOGAddonEntityTypes;
import net.sodiumzh.gogplus.registry.GOGAddonItems;

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
        GOGAddonEntityAttributes.COLLECTION.merge();
        GOGAddonEntityComponents.COLLECTION.merge();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

}
