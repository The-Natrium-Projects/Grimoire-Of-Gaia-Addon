package net.sodiumzh.gogaddon.registry;

import gaia.entity.AbstractGaiaEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.entity.behavior.AdvancedMobBehaviorComponent;
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

    public static final NFURegistry.Accessor<EntityComponentType<AbstractGaiaEntity, AdvancedMobBehaviorComponent>>
            ADVANCED_MOB_BEHAVIORS = COLLECTION.register("advanced_mob_behaviors", () ->
        new EntityComponentType<>(AbstractGaiaEntity.class, AdvancedMobBehaviorComponent.class, AdvancedMobBehaviorComponent::new));

    public static final HierarchyPath PATH_ADVANCED_MOB_BEHAVIORS = HierarchyPath.byLiteral("/gogaddon/mob_behaviors");
    public static final SubComponentAccessor<AbstractGaiaEntity, AdvancedMobBehaviorComponent>
            ACCESSOR_ADVANCED_MOB_BEHAVIORS = new SubComponentAccessor<>(PATH_ADVANCED_MOB_BEHAVIORS, ADVANCED_MOB_BEHAVIORS);

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = NFULibrary.MOD_ID)
    public static class Attachment {

        @SubscribeEvent
        public static void onInitComponents(EntityComponentSetupEvent event) {
            event.addComponent(PATH_ADVANCED_MOB_BEHAVIORS, GOGAddonEntityComponents.ADVANCED_MOB_BEHAVIORS.get());
        }
    }

}
