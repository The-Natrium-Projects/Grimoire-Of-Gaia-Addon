package net.sodiumzh.gogaddon.entity;

import gaia.entity.AbstractGaiaEntity;
import gaia.util.SharedEntityData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.registry.GOGAddonConfigs;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityTypes;
import net.sodiumzh.nfu.util.NFUEntityStatics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DhampirEntity extends AbstractGaiaEntity implements GOGAddonMob {

    protected double totalDamageDealt = 0d;

    public DhampirEntity(EntityType<? extends DhampirEntity> entityType, Level level) {
        super(entityType, level);
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

    @Override
    public float getBaseDefense() {
        return SharedEntityData.getBaseDefense2();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putDouble("totalDamageDealt", this.totalDamageDealt);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.totalDamageDealt = tag.getDouble("totalDamageDealt");
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public void updateOnAiStep() {
        if (this.getHealth() <= this.getMaxHealth() * 0.25d) {
            NFUEntityStatics.addEffectSafe(this, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 20));
            NFUEntityStatics.addEffectSafe(this, new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 20));
        }
    }

    @Override
    public void onAttack(LivingEntity target) {
        switch (this.level().getDifficulty()) {
            case NORMAL: {
                NFUEntityStatics.addEffectSafe(target, MobEffects.MOVEMENT_SLOWDOWN, 10 * 20, 0);
                break;
            }
            case HARD: {
                NFUEntityStatics.addEffectSafe(target, MobEffects.MOVEMENT_SLOWDOWN, 20 * 20, 0);
                break;
            }
        }
    }

    @Override
    public void onHurt(float amount, DamageSource damageSource) {

    }

    @Override
    public boolean canHurt(float amount, DamageSource damageSource) {
        return true;
    }

    @Override
    public List<MobEffect> immuneToEffects() {
        return List.of();
    }

    @Nullable
    public VampireEntity convertToVampire() {
        return this.convertTo(GOGAddonEntityTypes.VAMPIRE.getEntityType(), true);
    }

    @Override
    public void onDealDamage(LivingEntity target, float amount, DamageSource damageSource) {
        this.heal(amount);
        double convertDmg = GOGAddonConfigs.ValueCache.Gameplay.DHAMPIR_CONVERSION_DAMAGE;
        double convertChance = GOGAddonConfigs.ValueCache.Gameplay.DHAMPIR_CONVERSION_CHANCE;
        if (convertDmg >= 1d && convertChance > 0d) {
            int convertAmount = (int)Math.round(Math.floor((this.totalDamageDealt + amount) / convertDmg) - Math.floor(this.totalDamageDealt / convertDmg));
            for (int i = 0; i < convertAmount; ++i) {
                if (this.getRandom().nextDouble() <= convertChance) {
                    this.convertToVampire();
                    return;
                }
            }
        }
        this.totalDamageDealt += amount;
    }

    // COPY-PASTE TO ALL MOBS //

    @Override
    public void aiStep() {
        super.aiStep();
        this.updateOnAiStep();
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

    // COPY-PASTE END //
}
