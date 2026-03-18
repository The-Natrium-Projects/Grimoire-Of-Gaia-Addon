package net.sodiumzh.gogaddon.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.entity.GOGAddonMobReg;
import net.sodiumzh.gogaddon.entity.SelkieEntity;

public class GOGAddonEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GOGAddon.MOD_ID);

    public static final GOGAddonMobReg<SelkieEntity> SELKIE = new GOGAddonMobReg.Builder("selkie",
        () -> EntityType.Builder.of(SelkieEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0xc9b161, 0xd54242).withDefaultSounds().build();



}
