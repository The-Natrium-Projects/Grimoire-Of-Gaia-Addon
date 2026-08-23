package net.sodiumzh.gogaddon.entity.mob;

import gaia.entity.AbstractGaiaEntity;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogaddon.ai.goal.SelectedMeleeAttackGoal;
import net.sodiumzh.gogaddon.ai.goal.SelectedRangedAttackGoal;
import net.sodiumzh.gogaddon.entity.IMeleeAndRangedAttackMob;
import net.sodiumzh.gogaddon.util.GOGAddonStatics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GorgonEntity extends GOGAddonMob implements PowerableMob, IMeleeAndRangedAttackMob {

    public static final EntityDataAccessor<Boolean> POWERED = SynchedEntityData.defineId(GorgonEntity.class,
        EntityDataSerializers.BOOLEAN);
    protected boolean isMelee = false;


    public GorgonEntity(EntityType<? extends GorgonEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SelectedRangedAttackGoal(this, 1.25, 20, 15.0F));
        this.goalSelector.addGoal(1, new SelectedMeleeAttackGoal(this, 1.0d, true));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(2, this.targetPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean isMelee() {
        return isMelee;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(POWERED, false);
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

    // GOGAddon mob interface start //

    protected int inWaterTimer = 0;

    @Override
    public void updateState() {
        if (!this.level().isClientSide) {
            this.updatePowered();
            if (this.getTarget() != null)
                this.isMelee = this.getTarget().distanceToSqr(this) < 16d;
            if (this.isInWaterRainOrBubble()) {
                if (this.inWaterTimer >= 100 && this.inWaterTimer % 100 == 0)
                    this.heal(this.getMaxHealth() / 10f);
                this.inWaterTimer++;
            } else this.inWaterTimer = 0;
        }
    }

    @Override
    public void updateInventory() {
        if (this.isMelee() && this.getItemInHand(InteractionHand.MAIN_HAND).is(Items.BOW)) {
            ItemStack sword = new ItemStack(Items.IRON_SWORD);
            sword.enchant(Enchantments.SHARPNESS, 4);
            this.setItemInHand(InteractionHand.MAIN_HAND, sword);
        }
        else if (!this.isMelee() && this.getItemInHand(InteractionHand.MAIN_HAND).is(Items.IRON_SWORD)) {
            ItemStack bow = new ItemStack(Items.BOW);
            bow.enchant(Enchantments.POWER_ARROWS, 4);
            this.setItemInHand(InteractionHand.MAIN_HAND, bow);
        }

    }

    @Override
    public void onAttack(LivingEntity target) {
        GOGAddonStatics.addEffectByDifficulty(target, MobEffects.MOVEMENT_SLOWDOWN, 0, 10 * 20, 20 * 20);
        GOGAddonStatics.addEffectByDifficulty(target, MobEffects.WEAKNESS, 0, 10 * 20, 20 * 20, 1);
    }

    @Override
    public boolean canHurt(float amount, DamageSource damageSource) {
        if (this.isPowered() && damageSource.isIndirect()) return false;
        return true;
    }

    @Override
    public void onHurt(float amount, DamageSource damageSource) {

    }

    @Override
    public void onDealDamage(LivingEntity target, float amount, DamageSource damageSource) {
    }

    @Override
    public List<MobEffect> immuneToEffects() {
        return List.of();
    }

    @Override
    public void onDeath(DamageSource damageSource) {

    }

    // GOGAddon mob interface end //

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        if (pTarget.isAlive()) {
            RangedUtil.rangedAttack(pTarget, this, pVelocity);
        }
    }

    public static boolean checkSpawnRules(EntityType<? extends GorgonEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return GOGAddonStatics.MobStatics.nightGroundMobSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }
}
