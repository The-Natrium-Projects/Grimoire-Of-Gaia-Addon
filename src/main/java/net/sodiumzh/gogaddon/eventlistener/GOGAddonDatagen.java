package net.sodiumzh.gogaddon.eventlistener;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.registry.GOGAddonBiomeModifiers;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GOGAddonDatagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = CompletableFuture.supplyAsync(GOGAddonDatagen::getProvider);
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
                packOutput, lookupProvider, Set.of(GOGAddon.MOD_ID)));
        }
    }

    private static HolderLookup.Provider getProvider() {
        final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
        registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, GOGAddonBiomeModifiers::registerSpawnBiomeModifiers);
        // We need the BIOME registry to be present so we can use a biome tag, doesn't matter that it's empty
        registryBuilder.add(Registries.BIOME, context -> {
        });
        RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
        return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
    }

}
