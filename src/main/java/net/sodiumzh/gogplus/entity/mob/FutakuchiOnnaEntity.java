package net.sodiumzh.gogplus.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogplus.util.GOGAddonStatics;

import java.util.List;

public class FutakuchiOnnaEntity extends GOGAddonMob {

    public static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.defineId(FutakuchiOnnaEntity.class,
        EntityDataSerializers.BOOLEAN);

    public FutakuchiOnnaEntity(EntityType<? extends FutakuchiOnnaEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_TARGET, false);
    }

    public boolean hasTarget() {
        if (this.level().isClientSide())
            return this.entityData.get(HAS_TARGET);
        else return this.getTarget() != null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0d, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, this.targetPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    // GOGAddonMob interface //


    @Override
    public void updateState() {

    }

    @Override
    public void updateInventory() {
        // Do something
    }

    @Override
    public void onAttack(LivingEntity target) {
        GOGAddonStatics.addEffectByDifficulty(target, MobEffects.HUNGER, 0, 10 * 20, 20 * 20);
    }

    @Override
    public void onHurt(float amount, DamageSource damageSource) {
        // Do something
    }

    @Override
    public boolean canHurt(float amount, DamageSource damageSource) {
        return true;
    }

    @Override
    public List<MobEffect> immuneToEffects() {
        return List.of();
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        // Do something
    }

    @Override
    public void onDealDamage(LivingEntity target, float amount, DamageSource damageSource) {
        // Do something
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        // Add default equipment
    }

    // GOGAddonMob interface end //

    // Spawn rules for registration
    public static boolean checkSpawnRules(EntityType<? extends FutakuchiOnnaEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return GOGAddonStatics.MobStatics.nightGroundMobSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }

    public void tick() {
        super.tick();
        // Target update is hard-coded here so that overriding updateState() will not affect the validity of hasTarget()
        if (!this.level().isClientSide())
            this.entityData.set(HAS_TARGET, this.getTarget() != null);
    }

}
