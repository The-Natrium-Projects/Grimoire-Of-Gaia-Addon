package net.sodiumzh.gogplus.entity.mob;

import gaia.entity.AbstractGaiaEntity;
import gaia.util.SharedEntityData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public abstract class GOGPlusMob extends AbstractGaiaEntity implements IGOGAddonMob {

    public GOGPlusMob(EntityType<? extends AbstractGaiaEntity> entityType, Level level) {
        super(entityType, level);
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
    public float getBaseDefense() {
        return switch (this.getGaiaLevel()) {
            case 1 -> SharedEntityData.getBaseDefense1();
            case 2 -> SharedEntityData.getBaseDefense2();
            case 3 -> SharedEntityData.getBaseDefense3();
            default -> throw new RuntimeException("Illegal Gaia mob tier. Should be 1, 2 or 3. Found " + this.getGaiaLevel() + ".");
        };
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

}
