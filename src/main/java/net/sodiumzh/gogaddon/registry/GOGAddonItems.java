package net.sodiumzh.gogaddon.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.nfu.item.NFUBlockItem;
import net.sodiumzh.nfu.item.NFUItem;

public class GOGAddonItems {

    public static final DeferredRegister<Item> REG = DeferredRegister.create(ForgeRegistries.ITEMS, GOGAddon.MOD_ID);

    public static final RegistryObject<NFUItem> ANT_CHITIN = REG.register("ant_chitin",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<NFUItem> ANT_CHITIN_FRAGMENT = REG.register("ant_chitin_fragment",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<NFUItem> GHOST_FIRE = REG.register("ghost_fire",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<NFUItem> MYSTERIOUS_BRANCH = REG.register("mysterious_branch",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<NFUItem> SHINY_SCALE = REG.register("shiny_scale",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<NFUItem> ENCHANTED_FEATHER = REG.register("enchanted_feather",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<NFUItem> EXPLOSIVE_CORE = REG.register("explosive_core",
        () -> new NFUItem(new Item.Properties()));

    public static final RegistryObject<NFUBlockItem> ENCHANTED_COBBLESTONE = REG.register("enchanted_cobblestone",
        () -> new NFUBlockItem(GOGAddonBlocks.ENCHANTED_COBBLESTONE.get(), new Item.Properties()));
}
