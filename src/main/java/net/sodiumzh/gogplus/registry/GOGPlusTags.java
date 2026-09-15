package net.sodiumzh.gogplus.registry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.nfu.util.NFUTagStatics;

public class GOGPlusTags {

    public static final TagKey<EntityType<?>> ANTS =
        NFUTagStatics.createEntityTypeTag(GOGPlus.MOD_ID, "ants");

}
