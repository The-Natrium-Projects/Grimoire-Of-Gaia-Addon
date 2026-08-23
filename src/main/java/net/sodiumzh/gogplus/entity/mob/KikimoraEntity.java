package net.sodiumzh.gogplus.entity.mob;

import gaia.config.GaiaConfig;
import gaia.entity.type.IDayMob;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogplus.util.GOGAddonStatics;

public class KikimoraEntity extends GOGAddonNeutralMob implements IDayMob {

    public KikimoraEntity(EntityType<? extends KikimoraEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0d, true));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true);
        if (GaiaConfig.COMMON.allPassiveMobsHostile.get()) {
            this.targetSelector.addGoal(2, this.targetPlayerGoal);
        }
    }

    public static boolean checkSpawnRules(EntityType<? extends KikimoraEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return GOGAddonStatics.MobStatics.dayGroundMobSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }

    @Override
    public void updateState() {

    }

    @Override
    public void updateInventory() {

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
}
