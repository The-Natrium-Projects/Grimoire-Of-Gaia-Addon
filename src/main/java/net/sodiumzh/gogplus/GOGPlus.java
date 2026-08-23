package net.sodiumzh.gogplus;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sodiumzh.gogplus.registry.*;

@Mod(GOGPlus.MOD_ID)
public class GOGPlus {
    public static final String MOD_ID = "gogplus";

    public GOGPlus() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GOGPlusConfigs.CONFIG);
        bus.addListener(GOGPlusConfigs::loadConfig);

        GOGPlusEntityTypes.ENTITY_TYPES.register(bus);
        GOGPlusBlocks.REG.register(bus);
        GOGPlusItems.REG.register(bus);
        GOGPlusEntityAttributes.COLLECTION.merge();
        GOGPlusEntityComponents.COLLECTION.merge();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

}
