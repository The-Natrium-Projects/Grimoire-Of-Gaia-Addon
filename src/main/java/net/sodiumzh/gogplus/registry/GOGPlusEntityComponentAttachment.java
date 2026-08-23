package net.sodiumzh.gogplus.registry;

import net.minecraft.world.entity.Mob;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.gogplus.entity.behavior.GOGPlusMobBehaviorMappings;
import net.sodiumzh.nfu.entity.component.EntityComponentFinalizeSetupEvent;
import net.sodiumzh.nfu.entity.component.EntityComponentSetupEvent;
import net.sodiumzh.nfu.object.HierarchyPath;

@Mod.EventBusSubscriber(modid = GOGPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GOGPlusEntityComponentAttachment {

    @SubscribeEvent
    public static void onSetupComponents(EntityComponentSetupEvent event) {
        event.addNode(HierarchyPath.byLiteral("/gogaddon"));
        if (event.getEntity() instanceof Mob mob && GOGPlusMobBehaviorMappings.contains(mob.getType())) {
            event.addComponent(HierarchyPath.byLiteral("/gogaddon/mob_behaviors"), GOGPlusEntityComponents.ADVANCED_MOB_BEHAVIORS.get());
        }
    }

    @SubscribeEvent
    public static void onFinishSetupComponents(EntityComponentFinalizeSetupEvent event) {
        event.getComponentManager().getSubComponentByPath(GOGPlusEntityComponents.ACCESSOR_ADVANCED_MOB_BEHAVIORS)
                .ifPresent(c -> c.setEnabled(GOGPlusConfigs.ValueCache.Gameplay.MOBS_USE_ADVANCED_BEHAVIORS));
    }




}
