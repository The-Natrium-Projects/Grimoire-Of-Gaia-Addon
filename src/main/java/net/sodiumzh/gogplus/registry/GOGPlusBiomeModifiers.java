package net.sodiumzh.gogplus.registry;

import gaia.modifier.AddGaiaSpawnModifier;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.sodiumzh.gogplus.GOGPlus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class GOGPlusBiomeModifiers {

    public static void init(){}

    // Spawnable biome args
    public static final List<TagKey<Biome>> OVERWORLD = List.of(BiomeTags.IS_OVERWORLD);
    public static final List<TagKey<Biome>> OVERWORLD_SANDY = List.of(BiomeTags.IS_OVERWORLD, Tags.Biomes.IS_SANDY);
    public static final List<TagKey<Biome>> OVERWORLD_PLATEAU = List.of(BiomeTags.IS_OVERWORLD, Tags.Biomes.IS_PLATEAU);
    public static final List<TagKey<Biome>> OVERWORLD_MOUNTAIN = List.of(BiomeTags.IS_OVERWORLD, Tags.Biomes.IS_MOUNTAIN);
    public static final List<TagKey<Biome>> OVERWORLD_BEACH = List.of(BiomeTags.IS_OVERWORLD, BiomeTags.IS_BEACH);
    public static final List<TagKey<Biome>> OVERWORLD_WATER = List.of(BiomeTags.IS_OVERWORLD, Tags.Biomes.IS_WATER);
    public static final List<TagKey<Biome>> OVERWORLD_JUNGLE = List.of(BiomeTags.IS_OVERWORLD, BiomeTags.IS_JUNGLE);
    public static final List<TagKey<Biome>> OVERWORLD_SPOOKY_FOREST = List.of(BiomeTags.IS_OVERWORLD, BiomeTags.IS_FOREST, Tags.Biomes.IS_SPOOKY);
    public static final List<TagKey<Biome>> OVERWORLD_PLAINS = List.of(BiomeTags.IS_OVERWORLD, Tags.Biomes.IS_PLAINS);
    public static final List<TagKey<Biome>> OVERWORLD_BADLANDS = List.of(BiomeTags.IS_OVERWORLD, BiomeTags.IS_BADLANDS);
    public static final List<TagKey<Biome>> OVERWORLD_SAVANNA = List.of(BiomeTags.IS_OVERWORLD, BiomeTags.IS_SAVANNA);
    public static final List<TagKey<Biome>> OVERWORLD_CONIFEROUS = List.of(BiomeTags.IS_OVERWORLD, Tags.Biomes.IS_CONIFEROUS);
    public static final List<TagKey<Biome>> OVERWORLD_FOREST = List.of(BiomeTags.IS_OVERWORLD, BiomeTags.IS_FOREST);
    public static final List<TagKey<Biome>> OVERWORLD_RARE_DENSE_FOREST = List.of(BiomeTags.IS_OVERWORLD, BiomeTags.IS_FOREST, Tags.Biomes.IS_DENSE, Tags.Biomes.IS_RARE);
    public static final List<TagKey<Biome>> OVERWORLD_SNOWY = List.of(BiomeTags.IS_OVERWORLD, Tags.Biomes.IS_SNOWY);
    public static final List<TagKey<Biome>> OVERWORLD_SWAMP = List.of(BiomeTags.IS_OVERWORLD, Tags.Biomes.IS_SWAMP);
    public static final List<TagKey<Biome>> NETHER = List.of(BiomeTags.IS_NETHER);

    // Spawnable-exclusion args
    private static final TagKey<Biome> NO_DEFAULT_MONSTERS = TagKey.create(Registries.BIOME, new ResourceLocation("forge", "no_default_monsters"));
    public static final List<TagKey<Biome>> NG_PEACEFUL = List.of(BiomeTags.HAS_ANCIENT_CITY, Tags.Biomes.IS_MUSHROOM, NO_DEFAULT_MONSTERS);
    public static final List<TagKey<Biome>> NG_BADLANDS = List.of(BiomeTags.IS_BADLANDS);
    public static final List<TagKey<Biome>> NG_COLD_HOT_DENSE = List.of(Tags.Biomes.IS_COLD, Tags.Biomes.IS_HOT, Tags.Biomes.IS_DENSE);
    public static final List<TagKey<Biome>> NG_HOT_DENSE = List.of(Tags.Biomes.IS_HOT, Tags.Biomes.IS_DENSE);
    public static final List<TagKey<Biome>> NG_SAVANNA = List.of(BiomeTags.IS_SAVANNA);
    public static final List<TagKey<Biome>> NG_SNOWY = List.of(Tags.Biomes.IS_SNOWY);
    public static final List<TagKey<Biome>> NG_CONIFEROUS_COLD_HOT_SPARSE_SPOOKY_DENSE = List.of(Tags.Biomes.IS_CONIFEROUS, Tags.Biomes.IS_COLD_OVERWORLD, Tags.Biomes.IS_HOT_OVERWORLD, Tags.Biomes.IS_SPARSE, Tags.Biomes.IS_SPOOKY, Tags.Biomes.IS_DENSE);
    public static final List<TagKey<Biome>> NG_CONIFEROUS_COLD_HOT_SPARSE_SPOOKY = List.of(Tags.Biomes.IS_CONIFEROUS, Tags.Biomes.IS_COLD, Tags.Biomes.IS_HOT, Tags.Biomes.IS_SPARSE, Tags.Biomes.IS_SPOOKY);
    public static final List<TagKey<Biome>> NG_OCEAN_RIVER_BEACH_FOREST = List.of(BiomeTags.IS_OCEAN, BiomeTags.IS_RIVER, BiomeTags.IS_BEACH, BiomeTags.IS_FOREST);

    public static void registerSpawnBiomeModifiers(BootstapContext<BiomeModifier> context) {
        registerBiomeModifier(context, "add_baphomet",
            NETHER, null,
            new MobSpawnSettings.SpawnerData(GOGPlusEntityTypes.BAPHOMET.getEntityType(), 1, 1, 1)
        );
        registerBiomeModifier(context, "add_dhampir",
            OVERWORLD_CONIFEROUS, null,
            new MobSpawnSettings.SpawnerData(GOGPlusEntityTypes.DHAMPIR.getEntityType(), 5, 1, 1)
        );
        registerBiomeModifier(context, "add_gorgon",
            OVERWORLD_SWAMP, null,
            new MobSpawnSettings.SpawnerData(GOGPlusEntityTypes.GORGON.getEntityType(), 1, 1, 1)
        );
        registerBiomeModifier(context, "add_futakuchi_onna",
            OVERWORLD_SPOOKY_FOREST, null,
            new MobSpawnSettings.SpawnerData(GOGPlusEntityTypes.FUTAKUCHI_ONNA.getEntityType(), 10, 2, 3)
        );
        registerBiomeModifier(context, "add_sahuagin",
            OVERWORLD_SWAMP, null,
            new MobSpawnSettings.SpawnerData(GOGPlusEntityTypes.SAHUAGIN.getEntityType(), 10, 2, 3)
        );
        registerBiomeModifier(context, "add_kikimora",
            OVERWORLD_CONIFEROUS, null,
            new MobSpawnSettings.SpawnerData(GOGPlusEntityTypes.KIKIMORA.getEntityType(), 10, 1, 2)
        );
        registerBiomeModifier(context, "add_vampire",
            OVERWORLD_CONIFEROUS, null,
            new MobSpawnSettings.SpawnerData(GOGPlusEntityTypes.VAMPIRE.getEntityType(), 1, 1, 1)
        );
        registerBiomeModifier(context, "add_selkie",
            OVERWORLD_SNOWY, null,
            new MobSpawnSettings.SpawnerData(GOGPlusEntityTypes.SELKIE.getEntityType(), 5, 2, 3)
        );

    }



    /** Mirroring GaiaBiomeModifiers#registerBiomeModifier */
    private static void registerBiomeModifier(BootstapContext<BiomeModifier> context,
                                              String name,
                                              @NotNull List<TagKey<Biome>> tags,
                                              @Nullable List<TagKey<Biome>> blacklistTags,
                                              MobSpawnSettings.SpawnerData spawner) {
        final HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
        final ResourceKey<BiomeModifier> key = generateKey(name);
        final List<HolderSet<Biome>> tagHolders = tags.stream().map(tag -> biomeGetter.getOrThrow(tag)).collect(Collectors.toList());
        final List<HolderSet<Biome>> blacklistTagHolders = (blacklistTags == null || blacklistTags.isEmpty()) ? List.of() : blacklistTags.stream().map(tag -> biomeGetter.getOrThrow(tag)).collect(Collectors.toList());
        final BiomeModifier addFeature = AddGaiaSpawnModifier.singleSpawn(tagHolders, blacklistTagHolders, spawner);
        context.register(key, addFeature);
    }

    /** Mirroring GaiaBiomeModifiers#generateKey */
    private static ResourceKey<BiomeModifier> generateKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(GOGPlus.MOD_ID, name));
    }
}
