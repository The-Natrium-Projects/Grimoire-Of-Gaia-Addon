package net.sodiumzh.gogaddon.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.nfu.item.NFUItem;

public class GOGAddonItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GOGAddon.MOD_ID);

    public static final RegistryObject<Item> ANT_CHITIN = ITEMS.register("ant_chitin",
        () -> new NFUItem(new Item.Properties()));
    public static final RegistryObject<Item> ANT_CHITIN_FRAGMENT = ITEMS.register("ant_chitin_fragment",
        () -> new NFUItem(new Item.Properties()));

}
