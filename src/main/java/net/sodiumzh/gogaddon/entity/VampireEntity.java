package net.sodiumzh.gogaddon.entity;

import gaia.entity.AbstractGaiaEntity;
import gaia.util.SharedEntityData;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class VampireEntity extends AbstractGaiaEntity implements PowerableMob {

    public static final EntityDataAccessor<Boolean> POWERED = SynchedEntityData.defineId(VampireEntity.class,
        EntityDataSerializers.BOOLEAN);

    public VampireEntity(EntityType<? extends VampireEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(POWERED, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0d));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0d, true));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(2, this.targetPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public float getBaseDefense() {
        return SharedEntityData.getBaseDefense3();
    }

    @Override
    public boolean isPowered() {
        return this.entityData.get(POWERED);
    }

    public void setPowered(boolean val) {
        this.entityData.set(POWERED, val);
    }

    protected void updatePowered() {
        this.setPowered(this.getHealth() <= this.getMaxHealth() / 2d);
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide()) {
            this.updatePowered();
        }
    }
}
