package net.sodiumzh.gogaddon.entity;

import gaia.entity.AbstractAssistGaiaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class KikimoraEntity extends AbstractAssistGaiaEntity {
    public KikimoraEntity(EntityType<? extends KikimoraEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public float getBaseDefense() {
        return 0;
    }
}
