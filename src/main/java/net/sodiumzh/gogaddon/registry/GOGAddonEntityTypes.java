package net.sodiumzh.gogaddon.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.entity.GOGAddonMobReg;
import net.sodiumzh.gogaddon.entity.mob.*;

public class GOGAddonEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GOGAddon.MOD_ID);

    public static final GOGAddonMobReg<VampireEntity> VAMPIRE = new GOGAddonMobReg.Builder<>("vampire",
        () -> EntityType.Builder.of(VampireEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0XFF0000, 0x101010).withDefaultSounds().build();

    public static final GOGAddonMobReg<BaphometEntity> BAPHOMET = new GOGAddonMobReg.Builder<>("baphomet",
        () -> EntityType.Builder.of(BaphometEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0xc9b161, 0xd54242).withDefaultSounds().build();

    public static final GOGAddonMobReg<GorgonEntity> GORGON = new GOGAddonMobReg.Builder<>("gorgon",
        () -> EntityType.Builder.of(GorgonEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0x228B22, 0xEFEF00).withDefaultSounds().build();

    public static final GOGAddonMobReg<DhampirEntity> DHAMPIR = new GOGAddonMobReg.Builder<>("dhampir",
        () -> EntityType.Builder.of(DhampirEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0XDC143C, 0X101010).withDefaultSounds().build();

    public static final GOGAddonMobReg<KikimoraEntity> KIKIMORA = new GOGAddonMobReg.Builder<>("kikimora",
        () -> EntityType.Builder.of(KikimoraEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0xF5DEB3, 0xF5F5DC).withDefaultSounds().build();

    public static final GOGAddonMobReg<SelkieEntity> SELKIE = new GOGAddonMobReg.Builder<>("selkie",
        () -> EntityType.Builder.of(SelkieEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0x87CEFA, 0xFAFAD2).withDefaultSounds().build();

    public static final GOGAddonMobReg<FutakuchiOnnaEntity> FUTAKUCHI_ONNA = new GOGAddonMobReg.Builder<>("futakuchi_onna",
        () -> EntityType.Builder.of(FutakuchiOnnaEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0x000000, 0x8B0000).withDefaultSounds().build();

    public static final GOGAddonMobReg<SahuaginEntity> SAHUAGIN = new GOGAddonMobReg.Builder<>("sahuagin",
        () -> EntityType.Builder.of(SahuaginEntity::new, MobCategory.MONSTER).clientTrackingRange(8))
        .withEggColors(0x556B2F, 0x4169E1).withDefaultSounds().build();
}
