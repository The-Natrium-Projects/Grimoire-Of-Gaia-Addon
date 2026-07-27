package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.nbt.CompoundTag;

public abstract class GOGAddonMobBehaviors implements IGOGAddonMobBehaviors{

    private final AbstractGaiaEntity mob;
    private final GOGAddonMobBehaviorComponent component;

    public GOGAddonMobBehaviors(AbstractGaiaEntity mob, GOGAddonMobBehaviorComponent component) {
        this.mob = mob;
        this.component = component;
    }

    @Override
    public AbstractGaiaEntity getMob() {
        return mob;
    }

    @Override
    public GOGAddonMobBehaviorComponent getComponent() {
        return component;
    }

    public static class Placeholder extends GOGAddonMobBehaviors {

        public Placeholder(AbstractGaiaEntity mob, GOGAddonMobBehaviorComponent component) {
            super(mob, component);
        }

        @Override
        public void initialize() {

        }

        @Override
        public void joinLevel() {

        }

        @Override
        public void setupGoals(AbstractGaiaEntity mob) {

        }

        @Override
        public void startTick() {

        }

        @Override
        public void finishTick() {

        }

        @Override
        public void startAiStep() {
        }

        @Override
        public void finishAiStep() {

        }

        @Override
        public CompoundTag serializeNBT() {
            return null;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {

        }
    }
}
