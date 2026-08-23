package net.sodiumzh.gogplus.registry;

import gaia.registry.GaiaRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sodiumzh.gogplus.GOGAddon;
import net.sodiumzh.nfu.item.NFUBlockItem;
import net.sodiumzh.nfu.item.NFUItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = GOGAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GOGAddonItems {

    public static final DeferredRegister<Item> REG = DeferredRegister.create(ForgeRegistries.ITEMS, GOGAddon.MOD_ID);
    private static List<String> NO_TAB_KEY_LIST = new ArrayList<>();

    public static <T extends Item> RegistryObject<T> registerNoTab(String name, Supplier<? extends T> supplier) {
        NO_TAB_KEY_LIST.add(name);
        return REG.register(name, supplier);
    }

    // Tier 1 Loots
    public static final RegistryObject<NFUItem> ANT_CHITIN = REG.register("ant_chitin",
        () -> new NFUItem(new Item.Properties()).descTranslatable("tooltip.gogaddon.unimplemented_ingredient"));
    public static final RegistryObject<NFUItem> ANT_CHITIN_FRAGMENT = REG.register("ant_chitin_fragment",
        () -> new NFUItem(new Item.Properties()).descTranslatable("tooltip.gogaddon.unimplemented_ingredient"));
    public static final RegistryObject<NFUItem> GHOST_FIRE = REG.register("ghost_fire",
        () -> new NFUItem(new Item.Properties()).descTranslatable("tooltip.gogaddon.unimplemented_ingredient"));
    public static final RegistryObject<NFUItem> MYSTERIOUS_BRANCH = REG.register("mysterious_branch",
        () -> new NFUItem(new Item.Properties()).descTranslatable("tooltip.gogaddon.unimplemented_ingredient"));
    public static final RegistryObject<NFUItem> SHINY_SCALE = REG.register("shiny_scale",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<NFUItem> ENCHANTED_FEATHER = REG.register("enchanted_feather",
        () -> new NFUItem(new Item.Properties()).descTranslatable("tooltip.gogaddon.unimplemented_ingredient"));
    public static final RegistryObject<NFUItem> EXPLOSIVE_CORE = REG.register("explosive_core",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<NFUBlockItem> ENCHANTED_COBBLESTONE = REG.register("enchanted_cobblestone",
        () -> new NFUBlockItem(GOGAddonBlocks.ENCHANTED_COBBLESTONE.get(), new Item.Properties()));

    // Tier 2 Loots
    public static final RegistryObject<NFUItem> GOLDEN_SCALE = REG.register("golden_scale",
        () -> new NFUItem(new Item.Properties().rarity(Rarity.UNCOMMON)).descTranslatable("tooltip.gogaddon.unimplemented_ingredient"));
    
    // Tier 3 Loots
    public static final RegistryObject<NFUItem> HOLY_FEATHER = REG.register("holy_feather",
        () -> new NFUItem(new Item.Properties().rarity(Rarity.RARE)).descTranslatable("tooltip.gogaddon.unimplemented_ingredient"));

    @SubscribeEvent
    public static void onCreativeTabRegister(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab().equals(GaiaRegistry.GAIA_TAB.get())) {
            ForgeRegistries.ITEMS.getKeys().stream()
                .filter(key -> key.getNamespace().equals(GOGAddon.MOD_ID))
                .filter(key -> !NO_TAB_KEY_LIST.contains(key.getPath()))
                .map(ForgeRegistries.ITEMS::getValue)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(i -> (i instanceof SpawnEggItem) ? 0 : 1))
                .forEach(event::accept);
        }
    }

}
