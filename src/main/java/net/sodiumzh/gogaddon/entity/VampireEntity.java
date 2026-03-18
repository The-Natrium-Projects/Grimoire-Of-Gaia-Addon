package net.sodiumzh.gogaddon.entity;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class VampireEntity extends AbstractGaiaEntity {
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

}
