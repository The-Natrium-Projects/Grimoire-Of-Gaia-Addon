package net.sodiumzh.gogplus.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGAddon;
import net.sodiumzh.gogplus.registry.GOGAddonEntityComponents;
import net.sodiumzh.nfu.entity.component.EntityComponentAPI;
import net.sodiumzh.nfu.entity.component.EntityComponentBase;
import net.sodiumzh.nfu.exception.MissingRegistryEntryException;
import net.sodiumzh.nfu.mixin.event.entity.*;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import java.util.Optional;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE;

public class AdvancedMobBehaviorComponent extends EntityComponentBase<AbstractGaiaEntity> {

    @Nonnull
    private IAdvancedMobBehaviors<? extends AbstractGaiaEntity> behaviors;
    private boolean enabled;

    public AdvancedMobBehaviorComponent(AbstractGaiaEntity entity) {
        super(entity);
        this.behaviors = GOGAddonMobBehaviorMappings.get(entity.getType()).map(f -> f.apply(this))
                .orElseThrow(() -> new MissingRegistryEntryException("Missing GOG-Addon mob behavior info. Must be registered in GOGAddonMobBehaviorMappings."));
    }

    @Nonnull
    public IAdvancedMobBehaviors<? extends AbstractGaiaEntity> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(@Nonnull IAdvancedMobBehaviors<? extends AbstractGaiaEntity> behaviors) {
        this.behaviors = behaviors;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static Optional<AdvancedMobBehaviorComponent> get(Entity e) {
        return EntityComponentAPI.getComponentByPath(e, GOGAddonEntityComponents.ACCESSOR_ADVANCED_MOB_BEHAVIORS);
    }

    public static Optional<IAdvancedMobBehaviors<? extends AbstractGaiaEntity>> getBehaviorsIfEnabled(Entity e) {
        return get(e).filter(AdvancedMobBehaviorComponent::isEnabled)
            .map(AdvancedMobBehaviorComponent::getBehaviors);
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
            AdvancedMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IAdvancedMobBehaviors::finishAiStep);
        }

        @SubscribeEvent
        public static void onStartAiStep(LivingFinishAiStepEvent event) {
            AdvancedMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IAdvancedMobBehaviors::startAiStep);
        }

        @SubscribeEvent
        public static void onFinishAiStep(LivingFinishAiStepEvent event) {
            AdvancedMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IAdvancedMobBehaviors::finishAiStep);
        }

        @SubscribeEvent
        public static void onStartTick(EntityStartTickEvent event) {
            AdvancedMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IAdvancedMobBehaviors::startTick);
        }

        @SubscribeEvent
        public static void onFinishTick(EntityFinishTickEvent event) {
            AdvancedMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IAdvancedMobBehaviors::finishTick);
        }

        @SubscribeEvent
        public static void onSetupGoals(MobRegisterGoalsEvent event) {
            AdvancedMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IAdvancedMobBehaviors::setupGoals);
        }

        @SubscribeEvent
        public static void onJoinLevel(EntityJoinLevelEvent event) {
            AdvancedMobBehaviorComponent.getBehaviorsIfEnabled(event.getEntity())
                .ifPresent(IAdvancedMobBehaviors::joinLevel);
        }

    }





}
