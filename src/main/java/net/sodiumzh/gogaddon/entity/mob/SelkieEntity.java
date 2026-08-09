package net.sodiumzh.gogaddon.entity.mob;

import gaia.entity.AbstractGaiaEntity;
import gaia.entity.type.IDayMob;
import gaia.registry.GaiaRegistry;
import gaia.registry.GaiaTags;
import gaia.util.RangedUtil;
import gaia.util.SharedEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogaddon.ai.goal.SelectedMeleeAttackGoal;
import net.sodiumzh.gogaddon.ai.goal.SelectedRangedAttackGoal;
import net.sodiumzh.gogaddon.ai.goal.SelectedRangedBowAttackGoal;
import net.sodiumzh.gogaddon.entity.IMeleeAndRangedAttackMob;
import net.sodiumzh.gogaddon.util.GOGAddonStatics;
import org.jetbrains.annotations.Nullable;

public class SelkieEntity extends AbstractGaiaEntity implements IGOGAddonMob, IMeleeAndRangedAttackMob, IDayMob {

    public SelkieEntity(EntityType<? extends SelkieEntity> entityType, Level level) {
        super(entityType, level);
    }
    private boolean isMelee = false;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SelectedRangedBowAttackGoal<>(this, 1.25, 20, 15.0F));
        this.goalSelector.addGoal(1, new SelectedMeleeAttackGoal(this, 1.25, true));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(2, this.targetPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public float getBaseDefense() {
        return SharedEntityData.getBaseDefense1();
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        if (pTarget.isAlive()) {
            RangedUtil.rangedAttack(pTarget, this, pVelocity);
        }
    }

    @Override
    public boolean isMelee() {
        return this.isMelee;
    }

    public static boolean checkSpawnRules(EntityType<? extends SelkieEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return GOGAddonStatics.MobStatics.dayGroundMobSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }

    @Override
    public void updateState() {
        if (this.getTarget() != null)
            this.isMelee = this.distanceToSqr(this.getTarget()) < 16d;
    }

    @Override
    public void updateInventory() {
        if (this.isMelee())
            this.setItemInHand(InteractionHand.MAIN_HAND, GaiaRegistry.METAL_DAGGER.get().getDefaultInstance());
        else
            this.setItemInHand(InteractionHand.MAIN_HAND, Items.BOW.getDefaultInstance());
    }

    @Override
    public void onAttack(LivingEntity target) {

    }

    @Override
    public void onHurt(float amount, DamageSource damageSource) {

    }

    @Override
    public void onDealDamage(LivingEntity target, float amount, DamageSource damageSource) {

    }

    @Override
    public void onDeath(DamageSource damageSource) {

    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        // Add default equipment
    }

    // COPY-PASTE TO ALL MOBS //

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
