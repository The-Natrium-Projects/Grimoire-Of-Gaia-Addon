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

    public void startAiStep();

    public void finishAiStep();

}
