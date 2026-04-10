package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IGOGAddonMobBehaviors<E extends AbstractGaiaEntity> extends INBTSerializable<CompoundTag> {

    public void resetGoals(E mob);

}
