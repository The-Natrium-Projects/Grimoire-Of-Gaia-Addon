package net.sodiumzh.gogaddon.entity.mob;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogaddon.util.GOGAddonStatics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FutakuchiOnnaEntity extends AbstractGaiaEntity implements IGOGAddonMob {

    public static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.defineId(FutakuchiOnnaEntity.class,
        EntityDataSerializers.BOOLEAN);

    public FutakuchiOnnaEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_TARGET, false);
    }

    @Override
    public float getBaseDefense() {
        return 0;
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

    // COPY-PASTE TO ALL MOBS //

    public void tick() {
        super.tick();
        // Target update is hard-coded here so that overriding updateState() will not affect the validity of hasTarget()
        if (!this.level().isClientSide())
            this.entityData.set(HAS_TARGET, this.getTarget() != null);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.updateState();
        if (!this.level().isClientSide)
            this.updateInventory();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!this.canHurt(pAmount, pSource)) return false;
        boolean res = super.hurt(pSource, pAmount);
        if (res)
            this.onHurt(pAmount, pSource);
        return res;
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        boolean res = super.doHurtTarget(pEntity);
        if (res && pEntity instanceof LivingEntity le) {
            this.onAttack(le);
        }
        return res;
    }

    @Override
    public boolean canBeAffected(MobEffectInstance pEffectInstance) {
        return super.canBeAffected(pEffectInstance) && !immuneToEffects().contains(pEffectInstance.getEffect());
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        SpawnGroupData res = super.finalizeSpawn(levelAccessor, difficultyInstance, spawnType, groupData, tag);
        this.populateDefaultEquipmentSlots(this.getRandom(), difficultyInstance);
        return res;
    }

    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);
        if (this.isDeadOrDying()) {
            this.onDeath(pDamageSource);
        }
    }

    // COPY-PASTE END //
}
