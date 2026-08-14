package net.sodiumzh.gogaddon.entity.mob;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Only as a template for GOGAddon mobs. Copy-paste the content when creating a new mob.
 */
@ApiStatus.NonExtendable
public abstract class GOGAddonTemplateMob extends AbstractGaiaEntity implements IGOGAddonMob {

    public GOGAddonTemplateMob(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public float getBaseDefense() {
        return 0;
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
    public static boolean checkSpawnRules(EntityType<? extends VampireEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkDaysPassed(levelAccessor)
            && checkAboveSeaLevel(levelAccessor, pos)
            && checkMonsterSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }

}
