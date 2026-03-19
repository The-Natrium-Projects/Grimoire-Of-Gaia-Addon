package net.sodiumzh.gogaddon.entity;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class VampireEntity extends AbstractGaiaEntity implements PowerableMob {
    public VampireEntity(EntityType<? extends VampireEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public float getBaseDefense() {
        return 0;
    }

    public boolean isArmored() {
        return true;
    }

    @Override
    public boolean isPowered() {
        return false;
    }
}
