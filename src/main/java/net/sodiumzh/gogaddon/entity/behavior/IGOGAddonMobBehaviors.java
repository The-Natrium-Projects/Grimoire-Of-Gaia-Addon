package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Handler of advanced behaviors for Gaia mobs.
 */
public interface IGOGAddonMobBehaviors extends INBTSerializable<CompoundTag> {

    public AbstractGaiaEntity getMob();

    public GOGAddonMobBehaviorComponent getComponent();

    public void initialize();

    public void joinLevel();

    public void setupGoals(AbstractGaiaEntity mob);

    public void startTick();

    public void finishTick();

    public boolean startAiStep();

    public void finishAiStep();

    public static class Placeholder implements IGOGAddonMobBehaviors {

        private final AbstractGaiaEntity mob;
        private final GOGAddonMobBehaviorComponent component;

        public Placeholder(AbstractGaiaEntity mob, GOGAddonMobBehaviorComponent component) {
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
        public boolean startAiStep() {
            return false;
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
