package net.sodiumzh.gogaddon.entity;

import gaia.entity.AbstractGaiaEntity;
import gaia.util.SharedEntityData;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.level.Level;

public class VampireEntity extends AbstractGaiaEntity implements PowerableMob {

    public static final EntityDataAccessor<Boolean> POWERED = SynchedEntityData.defineId(VampireEntity.class,
        EntityDataSerializers.BOOLEAN);

    public VampireEntity(EntityType<? extends VampireEntity> entityType, Level level) {
        super(entityType, level);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(POWERED, false);
    }

    @Override
    public float getBaseDefense() {
        return SharedEntityData.getBaseDefense3();
    }

    @Override
    public boolean isPowered() {
        return this.entityData.get(POWERED);
    }
}
