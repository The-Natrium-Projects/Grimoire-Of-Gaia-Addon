package net.sodiumzh.gogaddon.registry;

import gaia.entity.AbstractGaiaEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.entity.behavior.GOGAddonMobBehaviorComponent;
import net.sodiumzh.nfu.NFULibrary;
import net.sodiumzh.nfu.entity.component.EntityComponentSetupEvent;
import net.sodiumzh.nfu.entity.component.EntityComponentType;
import net.sodiumzh.nfu.entity.component.SubComponentAccessor;
import net.sodiumzh.nfu.object.HierarchyPath;
import net.sodiumzh.nfu.registry.NFURegistries;
import net.sodiumzh.nfu.registry.NFURegistry;
import net.sodiumzh.nfu.registry.NFURegistryEntryCollection;

import java.awt.event.HierarchyEvent;

public class GOGAddonEntityComponents {

    public static final NFURegistryEntryCollection<EntityComponentType<?, ?>> COLLECTION =
        NFURegistryEntryCollection.create(NFURegistries.ENTITY_COMPONENT_TYPES, GOGAddon.MOD_ID);

    public static final NFURegistry.Accessor<EntityComponentType<AbstractGaiaEntity, GOGAddonMobBehaviorComponent>>
        GOGADDON_MOB_BEHAVIOR = COLLECTION.register("gogaddon_mob_behavior", () ->
        new EntityComponentType<>(AbstractGaiaEntity.class, GOGAddonMobBehaviorComponent.class, GOGAddonMobBehaviorComponent::new));

    public static final HierarchyPath PATH_MOB_BEHAVIOR = HierarchyPath.byLiteral("gogaddon_mob_behavior");
    public static final SubComponentAccessor<AbstractGaiaEntity, GOGAddonMobBehaviorComponent> ACCESSOR_MOB_BEHAVIOR =
        new SubComponentAccessor<>(PATH_MOB_BEHAVIOR, GOGADDON_MOB_BEHAVIOR);

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = NFULibrary.MOD_ID)
    public static class Attachment {

        @SubscribeEvent
        public static void onInitComponents(EntityComponentSetupEvent event) {
            event.addComponent("/gogaddon_mob_behavior", GOGAddonEntityComponents.GOGADDON_MOB_BEHAVIOR.get());
        }

    }

}
