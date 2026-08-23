package net.sodiumzh.gogplus.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Handler of advanced behaviors for Gaia mobs.
 */
public interface IAdvancedMobBehaviors<T extends AbstractGaiaEntity> extends INBTSerializable<CompoundTag> {

    /**
     * Get the attached mob.
     */
    public T getMob();

    /**
     * Get the attached behavior component.
     */
    public AdvancedMobBehaviorComponent getComponent();

    /**
     * Invoked on mob construction.
     */
    public void initialize();

    /**
     * Invoked on mob join level.
     */
    public void joinLevel();

    /**
     * Invoked after the mob setting up goals to modify the AI.
     * <p>Note: the mob's original goals are NOT removed. Manually remove them if necessary.
     */
    public void setupGoals();

    /**
     * Invoked before the mob's {@link Entity#tick()} invocation.
     */
    public void startTick();

    /**
     * Invoked after the mob's {@link Entity#tick()} invocation.
     */
    public void finishTick();

    /**
     * Invoked before the mob's {@link LivingEntity#aiStep()} invocation.
     */
    public void startAiStep();

    /**
     * Invoked after the mob's {@link LivingEntity#aiStep()} invocation.
     */
    public void finishAiStep();

    /**
     * Invoked before {@link LivingEntity#aiStep()} after any extensions in subclasses before {@code super.aiStep()}.
     */
    public void startBaseAiStep();

    /**
     * Invoked after {@link LivingEntity#aiStep()} before any extensions in subclasses after {@code super.aiStep()}.
     */
    public void finishBaseAiStep();

    // Only works as a placeholder when behaviors are not present.
    /*static final IGOGAddonMobBehaviors PLACEHOLDER = new IGOGAddonMobBehaviors() {

        @Override
        public AbstractGaiaEntity getMob() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public GOGAddonMobBehaviorComponent getComponent() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public void initialize() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public void joinLevel() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public void setupGoals(AbstractGaiaEntity mob) {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public void startTick() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public void finishTick() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public void startAiStep() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public void finishAiStep() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public CompoundTag serializeNBT() {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }

        @Override
        public void deserializeNBT(CompoundTag compoundTag) {
            throw new IllegalCallerException("Missing mob behaviors. THe placeholder behavior instance should never be accessed.");
        }
    };*/

}
