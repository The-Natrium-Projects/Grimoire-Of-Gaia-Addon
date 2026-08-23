package net.sodiumzh.gogplus.registry;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.nfu.entity.EntityAttributeProvider;
import net.sodiumzh.nfu.registry.NFURegistries;
import net.sodiumzh.nfu.registry.NFURegistry;
import net.sodiumzh.nfu.registry.NFURegistryEntryCollection;

@Mod.EventBusSubscriber(modid = GOGPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GOGPlusEntityAttributes {

    public static final NFURegistryEntryCollection<EntityAttributeProvider> COLLECTION =
        NFURegistryEntryCollection.create(NFURegistries.ENTITY_ATTRIBUTE_PROVIDERS, GOGPlus.MOD_ID);

    public static final NFURegistry.Accessor<EntityAttributeProvider> DEFAULT_TIER_1 = COLLECTION.register("default_tier_1",
        () -> EntityAttributeProvider.monster()
            .add(Attributes.MAX_HEALTH, 40.0d)
            .add(Attributes.FOLLOW_RANGE, 40.0d)
            .add(Attributes.MOVEMENT_SPEED, 0.25d)
            .add(Attributes.ATTACK_DAMAGE, 4.0d)
            .add(Attributes.ARMOR, 4.0d)
            .add(Attributes.ATTACK_KNOCKBACK, 0.3d)
            .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0d));

    public static final NFURegistry.Accessor<EntityAttributeProvider> DEFAULT_TIER_2 = COLLECTION.register("default_tier_2",
        () -> EntityAttributeProvider.monster()
            .add(Attributes.MAX_HEALTH, 80.0d)
            .add(Attributes.FOLLOW_RANGE, 40.0d)
            .add(Attributes.MOVEMENT_SPEED, 0.275d)
            .add(Attributes.ATTACK_DAMAGE, 8.0d)
            .add(Attributes.ARMOR, 8.0d)
            .add(Attributes.ATTACK_KNOCKBACK, 0.25d)
            .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0d));


    public static final NFURegistry.Accessor<EntityAttributeProvider> DEFAULT_TIER_3 = COLLECTION.register("default_tier_3",
        () -> EntityAttributeProvider.monster()
            .add(Attributes.MAX_HEALTH, 160.0)
            .add(Attributes.FOLLOW_RANGE, 40.0)
            .add(Attributes.MOVEMENT_SPEED, 0.3)
            .add(Attributes.ATTACK_DAMAGE, 12.0)
            .add(Attributes.ARMOR, 12.0)
            .add(Attributes.ATTACK_KNOCKBACK, 0.2)
            .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0d));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(GOGPlusEntityTypes.VAMPIRE.getEntityType(), DEFAULT_TIER_3.get().get().build());
        event.put(GOGPlusEntityTypes.BAPHOMET.getEntityType(), DEFAULT_TIER_3.get().get().build());
        event.put(GOGPlusEntityTypes.DHAMPIR.getEntityType(), DEFAULT_TIER_2.get().get().build());
        event.put(GOGPlusEntityTypes.GORGON.getEntityType(), DEFAULT_TIER_3.get().get().build());
        event.put(GOGPlusEntityTypes.SELKIE.getEntityType(), DEFAULT_TIER_1.get().get().build());
        event.put(GOGPlusEntityTypes.KIKIMORA.getEntityType(), DEFAULT_TIER_1.get().get().build());
        event.put(GOGPlusEntityTypes.FUTAKUCHI_ONNA.getEntityType(), DEFAULT_TIER_1.get().get().build());
        event.put(GOGPlusEntityTypes.SAHUAGIN.getEntityType(), DEFAULT_TIER_1.get().get().build());
    }
}
