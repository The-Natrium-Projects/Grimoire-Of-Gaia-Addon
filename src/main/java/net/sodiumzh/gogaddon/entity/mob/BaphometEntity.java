package net.sodiumzh.gogaddon.entity.mob;

import gaia.entity.AbstractGaiaEntity;
import gaia.registry.GaiaRegistry;
import gaia.util.RangedUtil;
import gaia.util.SharedEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogaddon.ai.goal.SelectedMeleeAttackGoal;
import net.sodiumzh.gogaddon.ai.goal.SelectedRangedAttackGoal;
import net.sodiumzh.gogaddon.entity.IMeleeAndRangedAttackMob;
import net.sodiumzh.gogaddon.util.GOGAddonStatics;
import net.sodiumzh.nfu.util.NFUEntityStatics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaphometEntity extends GOGAddonMob implements IMeleeAndRangedAttackMob, PowerableMob {

    public static final EntityDataAccessor<Boolean> POWERED = SynchedEntityData.defineId(BaphometEntity.class,
        EntityDataSerializers.BOOLEAN);

    public BaphometEntity(EntityType<? extends BaphometEntity> entityType, Level level) {
        super(entityType, level);
    }

    protected boolean isMelee = false;

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(POWERED, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SelectedRangedAttackGoal(this, 1.275, 20, 60, 15.0F));
        this.goalSelector.addGoal(1, new SelectedMeleeAttackGoal(this, 1.25, true));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, this.targetPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true));
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
    public boolean isMelee() {
        return isMelee;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        if (pTarget.isAlive()) {
            RangedUtil.fireball(pTarget, this, pVelocity);
            this.swing(InteractionHand.MAIN_HAND);
        }
    }

    @Override
    public int getGaiaLevel() {
        return 3;
    }

    // GOGAddonMob interface //

    @Override
    public void updateState() {
        if (!this.level().isClientSide()) {
            this.updatePowered();
            if (this.getTarget() != null) {
                this.isMelee = this.getTarget().distanceToSqr(this) < 16d;
                if (this.isMelee)
                    NFUEntityStatics.addEffectSafe(this, new MobEffectInstance(MobEffects.DAMAGE_BOOST, 19, 1));
            }
        }
    }

    @Override
    public void updateInventory() {
    }

    @Override
    public void onAttack(LivingEntity target) {
        GOGAddonStatics.addEffectByDifficulty(target, MobEffects.MOVEMENT_SLOWDOWN, 0, 10 * 20, 20 * 20);
        GOGAddonStatics.addEffectByDifficulty(target, MobEffects.WEAKNESS, 0, 10 * 20, 20 * 20);
    }

    @Override
    public void onHurt(float amount, DamageSource damageSource) {

    }

    @Override
    public boolean canHurt(float amount, DamageSource damageSource) {
        if (this.isPowered() && damageSource.isIndirect()) return false;
        return !damageSource.type().effects().equals(DamageEffects.BURNING);
    }

    @Override
    public List<MobEffect> immuneToEffects() {
        return List.of(MobEffects.WITHER, MobEffects.WEAKNESS);
    }

    @Override
    public void onDeath(DamageSource damageSource) {

    }

    @Override
    public void onDealDamage(LivingEntity target, float amount, DamageSource damageSource) {

    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        if (pRandom.nextFloat() < 0.5f)
            this.setItemInHand(InteractionHand.MAIN_HAND, GaiaRegistry.BROOM.get().getDefaultInstance());
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    // GOGAddonMob interface end //

    public static boolean checkSpawnRules(EntityType<? extends BaphometEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return GOGAddonStatics.MobStatics.nightGroundMobSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }
}
