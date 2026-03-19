package net.sodiumzh.gogaddon.registry;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.nfu.entity.EntityAttributeProvider;
import net.sodiumzh.nfu.registry.NFURegistries;
import net.sodiumzh.nfu.registry.NFURegistry;
import net.sodiumzh.nfu.registry.NFURegistryEntryCollection;

@Mod.EventBusSubscriber(modid = GOGAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GOGAddonEntityAttributes {

    public static final NFURegistryEntryCollection<EntityAttributeProvider> COLLETION =
        NFURegistryEntryCollection.create(NFURegistries.ENTITY_ATTRIBUTE_PROVIDERS, GOGAddon.MOD_ID);

    public static final NFURegistry.Accessor<EntityAttributeProvider> VAMPIRE = COLLETION.register("vampire",
        () -> EntityAttributeProvider.monster()
            .add(Attributes.MAX_HEALTH, 160.0)
            .add(Attributes.FOLLOW_RANGE, 40.0)
            .add(Attributes.MOVEMENT_SPEED, 0.3)
            .add(Attributes.ATTACK_DAMAGE, 12.0)
            .add(Attributes.ARMOR, 12.0)
            .add(Attributes.ATTACK_KNOCKBACK, 0.2)
            .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.0));

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(GOGAddonEntityTypes.VAMPIRE.getEntityType(), VAMPIRE.get().get().build());
    }
}
