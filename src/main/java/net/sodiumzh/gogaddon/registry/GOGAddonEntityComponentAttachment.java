package net.sodiumzh.gogaddon.registry;

import net.minecraft.world.entity.Mob;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.entity.behavior.GOGAddonMobBehaviorMappings;
import net.sodiumzh.nfu.entity.component.EntityComponentFinalizeSetupEvent;
import net.sodiumzh.nfu.entity.component.EntityComponentSetupEvent;
import net.sodiumzh.nfu.object.HierarchyPath;

@Mod.EventBusSubscriber(modid = GOGAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GOGAddonEntityComponentAttachment {

    @SubscribeEvent
    public static void onSetupComponents(EntityComponentSetupEvent event) {
        event.addNode(HierarchyPath.byLiteral("/gogaddon"));
        if (event.getEntity() instanceof Mob mob && GOGAddonMobBehaviorMappings.contains(mob.getType())) {
            event.addComponent(HierarchyPath.byLiteral("/gogaddon/mob_behaviors"), GOGAddonEntityComponents.ADVANCED_MOB_BEHAVIORS.get());
        }
    }

    @SubscribeEvent
    public static void onFinishSetupComponents(EntityComponentFinalizeSetupEvent event) {
        event.getComponentManager().getSubComponentByPath(GOGAddonEntityComponents.ACCESSOR_ADVANCED_MOB_BEHAVIORS)
                .ifPresent(c -> c.setEnabled(GOGAddonConfigs.ValueCache.Gameplay.MOBS_USE_ADVANCED_BEHAVIORS));
    }




}
