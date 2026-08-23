package net.sodiumzh.gogaddon.entity.mob;

import gaia.entity.AbstractGaiaEntity;
import gaia.util.SharedEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.sodiumzh.gogaddon.util.GOGAddonStatics;
import net.sodiumzh.nfu.util.NFUParticleStatics;

import java.util.List;

public class VampireEntity extends GOGAddonMob implements PowerableMob {

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
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public int getGaiaLevel() {
        return 3;
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

    @Override
    public void updateState() {
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0D)
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));

        if (!this.level().isClientSide()) {
            this.updatePowered();
            if (this.isSunBurnTick()) {
                this.hurt(damageSources().onFire(), this.getMaxHealth() / 8f);
                NFUParticleStatics.sendSmokeParticlesToEntityDefault(this, 0f, 20);
            }
        }
    }

    @Override
    public void updateInventory() {

    }

    @Override
    public boolean canHurt(float amount, DamageSource damageSource) {
        if (this.isPowered() && damageSource.isIndirect()) return false;
        return true;
    }

    @Override
    public void onDeath(DamageSource pDamageSource) {
        Bat bat = EntityType.BAT.create(this.level());
        if (bat != null) {
            bat.setPos(this.position().add(0, 0.5d, 0));
            this.level().addFreshEntity(bat);
        }
    }

    @Override
    public void onAttack(LivingEntity target) {

    }

    @Override
    public void onHurt(float amount, DamageSource damageSource) {

    }

    @Override
    public void onDealDamage(LivingEntity target, float amount, DamageSource damageSource) {
        this.heal(amount * 2f);
        int heartAmount = Math.round(amount);
        if (heartAmount > 0)
            NFUParticleStatics.sendHeartParticlesToEntityDefault(this, 0.0f, heartAmount);
    }

    @Override
    public List<MobEffect> immuneToEffects() {
        return List.of();
    }

    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);
        if (this.isDeadOrDying()) {
            this.onDeath(pDamageSource);
        }
    }

    // COPY-PASTE END //

    public static boolean checkSpawnRules(EntityType<? extends VampireEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return GOGAddonStatics.MobStatics.nightGroundMobSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }
}
