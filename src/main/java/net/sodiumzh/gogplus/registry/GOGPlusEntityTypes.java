package net.sodiumzh.gogplus.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.gogplus.entity.GOGPlusMobType;
import net.sodiumzh.gogplus.entity.mob.*;

public class GOGPlusEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GOGPlus.MOD_ID);

    public static final GOGPlusMobType<VampireEntity> VAMPIRE = new GOGPlusMobType.Builder<>("vampire",
        () -> EntityType.Builder.of(VampireEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0XFF0000, 0x101010).withDefaultSounds().build();

    public static final GOGPlusMobType<BaphometEntity> BAPHOMET = new GOGPlusMobType.Builder<>("baphomet",
        () -> EntityType.Builder.of(BaphometEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0xc9b161, 0xd54242).withDefaultSounds().build();

    public static final GOGPlusMobType<GorgonEntity> GORGON = new GOGPlusMobType.Builder<>("gorgon",
        () -> EntityType.Builder.of(GorgonEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0x228B22, 0xEFEF00).withDefaultSounds().build();

    public static final GOGPlusMobType<DhampirEntity> DHAMPIR = new GOGPlusMobType.Builder<>("dhampir",
        () -> EntityType.Builder.of(DhampirEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0XDC143C, 0X101010).withDefaultSounds().build();

    public static final GOGPlusMobType<KikimoraEntity> KIKIMORA = new GOGPlusMobType.Builder<>("kikimora",
        () -> EntityType.Builder.of(KikimoraEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0xF5DEB3, 0xF5F5DC).withDefaultSounds().build();

    public static final GOGPlusMobType<SelkieEntity> SELKIE = new GOGPlusMobType.Builder<>("selkie",
        () -> EntityType.Builder.of(SelkieEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0x87CEFA, 0xFAFAD2).withDefaultSounds().build();

    public static final GOGPlusMobType<FutakuchiOnnaEntity> FUTAKUCHI_ONNA = new GOGPlusMobType.Builder<>("futakuchi_onna",
        () -> EntityType.Builder.of(FutakuchiOnnaEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0x000000, 0x8B0000).withDefaultSounds().build();

    public static final GOGPlusMobType<SahuaginEntity> SAHUAGIN = new GOGPlusMobType.Builder<>("sahuagin",
        () -> EntityType.Builder.of(SahuaginEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0x556B2F, 0x4169E1).withDefaultSounds().build();
}
