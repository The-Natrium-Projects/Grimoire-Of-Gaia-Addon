package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityComponents;
import net.sodiumzh.nfu.entity.component.EntityComponentAPI;
import net.sodiumzh.nfu.entity.component.EntityComponentBase;
import net.sodiumzh.nfu.mixin.event.entity.*;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import java.util.Optional;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;

public class GOGAddonMobBehaviorComponent extends EntityComponentBase<AbstractGaiaEntity> {

    @Nonnull
    private IGOGAddonMobBehaviors behaviors;
    private boolean enabled;

    public GOGAddonMobBehaviorComponent(AbstractGaiaEntity entity) {
        super(entity);
        this.behaviors = new IGOGAddonMobBehaviors.Placeholder(this.getEntity(), this);
    }

    @Nonnull
    public IGOGAddonMobBehaviors getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(@Nonnull IGOGAddonMobBehaviors behaviors) {
        this.behaviors = behaviors;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static Optional<GOGAddonMobBehaviorComponent> get(Entity e) {
        return EntityComponentAPI.getComponentByPath(e, GOGAddonEntityComponents.ACCESSOR_GOGADDON_MOB_BEHAVIOR);
    }


    public static Optional<GOGAddonMobBehaviors> getBehaviorsIfEnabled(Entity e) {
        return get(e).filter(GOGAddonMobBehaviorComponent::isEnabled)
            .map(c -> (GOGAddonMobBehaviors) c.getBehaviors());
    }

    @Override
    public void tick() {

    }

    @Override
    public @Nullable CompoundTag serializeNBT() {
        return behaviors.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.behaviors.deserializeNBT(nbt);
    }

    @Mod.EventBusSubscriber(modid = GOGAddon.MOD_ID, bus = FORGE)
    public static class EventListeners {

        @SubscribeEvent
        public static void onInitialize(EntityFinishConstructionEvent event) {
            GOGAddonMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IGOGAddonMobBehaviors::finishAiStep);
        }

        @SubscribeEvent
        public static void onStartAiStep(LivingFinishAiStepEvent event) {
            GOGAddonMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IGOGAddonMobBehaviors::startAiStep);
        }

        @SubscribeEvent
        public static void onFinishAiStep(LivingFinishAiStepEvent event) {
            GOGAddonMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IGOGAddonMobBehaviors::finishAiStep);
        }

        @SubscribeEvent
        public static void onStartTick(EntityStartTickEvent event) {
            GOGAddonMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IGOGAddonMobBehaviors::startTick);
        }

        @SubscribeEvent
        public static void onFinishTick(EntityFinishTickEvent event) {
            GOGAddonMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IGOGAddonMobBehaviors::finishTick);
        }

        @SubscribeEvent
        public static void onSetupGoals(MobRegisterGoalsEvent event) {
            GOGAddonMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(bh -> {
                    bh.getMob().goalSelector.removeAllGoals(g -> true);
                    bh.getMob().targetSelector.removeAllGoals(g -> true);
                    bh.setupGoals(bh.getComponent().getEntity());
                });
        }

        @SubscribeEvent
        public static void onJoinLevel(EntityJoinLevelEvent event) {
            GOGAddonMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IGOGAddonMobBehaviors::joinLevel);
        }

    }





}
