package net.sodiumzh.gogplus.entity.mob;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogplus.entity.IMeleeAndRangedAttackMob;
import net.sodiumzh.gogplus.util.GOGAddonStatics;

import java.util.List;

public class SahuaginEntity extends GOGPlusMob implements IMeleeAndRangedAttackMob {

    public SahuaginEntity(EntityType<? extends SahuaginEntity> entityType, Level level) {
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
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

    @Override
    public boolean isMelee() {
        return false;
    }

    // GOGAddonMob interface //

    @Override
    public void updateState() {
        // Do something
    }

    @Override
    public void updateInventory() {
        // Do something
    }

    @Override
    public void onAttack(LivingEntity target) {
        // Do something
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
    public static boolean checkSpawnRules(EntityType<? extends SahuaginEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return GOGAddonStatics.MobStatics.nightGroundMobSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }

}
