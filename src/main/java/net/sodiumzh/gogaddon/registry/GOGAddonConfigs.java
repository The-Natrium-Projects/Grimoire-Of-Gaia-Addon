package net.sodiumzh.gogaddon.registry;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.sodiumzh.gogaddon.GOGAddon;

@Mod.EventBusSubscriber(modid = GOGAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GOGAddonConfigs {

    protected static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec CONFIG;

    public static ForgeConfigSpec.BooleanValue MOBS_USE_ADVANCED_BEHAVIORS;
    public static ForgeConfigSpec.DoubleValue DHAMPIR_CONVERSION_CHANCE;
    public static ForgeConfigSpec.DoubleValue DHAMPIR_CONVERSION_DAMAGE;

    static {
        BUILDER.push("gameplay");
        MOBS_USE_ADVANCED_BEHAVIORS = BUILDER.comment("If true, the mobs will use advanced behaviors. " +
            "Otherwise, they will keep original Gaia AI.")
            .define("mobsUseAdvancedBehaviors", true);
        DHAMPIR_CONVERSION_CHANCE = BUILDER.comment("The chance of Dhampir converting " +
            "to Vampire on each attempt.")
                .defineInRange("dhampirConversionChance", 0.1d, 0d, 1d);
        DHAMPIR_CONVERSION_DAMAGE = BUILDER.comment("Dhampir will try converting to Vampire "
            + "after dealing this amount of damage.")
                .defineInRange("dhampirConversionDamage", 50d, 1d, Double.MAX_VALUE);
        BUILDER.pop();
        CONFIG = BUILDER.build();
    }

    public static class ValueCache {
        public static class Gameplay {
            public static boolean MOBS_USE_ADVANCED_BEHAVIORS = true;
            public static double DHAMPIR_CONVERSION_CHANCE = 0.1d;
            public static double DHAMPIR_CONVERSION_DAMAGE = 50d;
        }

        public static void refresh() {
            Gameplay.MOBS_USE_ADVANCED_BEHAVIORS = GOGAddonConfigs.MOBS_USE_ADVANCED_BEHAVIORS.get();
            Gameplay.DHAMPIR_CONVERSION_CHANCE = GOGAddonConfigs.DHAMPIR_CONVERSION_CHANCE.get();
            Gameplay.DHAMPIR_CONVERSION_DAMAGE = GOGAddonConfigs.DHAMPIR_CONVERSION_DAMAGE.get();
        }

    }

    @SubscribeEvent
    public static void loadConfig(final ModConfigEvent event)
    {
        if (event.getConfig().getSpec() == CONFIG)
        {
            ValueCache.refresh();
        }

    }
}
