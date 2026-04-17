package net.sodiumzh.gogaddon.entity.mob;

import gaia.entity.AbstractGaiaEntity;
import gaia.util.SharedEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.gogaddon.registry.GOGAddonConfigs;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityTypes;
import net.sodiumzh.gogaddon.util.GOGAddonStatics;
import net.sodiumzh.nfu.util.NFUEntityStatics;
import net.sodiumzh.nfu.util.NFUParticleStatics;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DhampirEntity extends AbstractGaiaEntity implements IGOGAddonMob {

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
    public int getGaiaLevel() {
        return 2;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemInHand(InteractionHand.MAIN_HAND, Items.STONE_SWORD.getDefaultInstance());
    }

    @Override
    public void updateState() {
        if (this.getHealth() <= this.getMaxHealth() * 0.25d) {
            NFUEntityStatics.addEffectSafe(this, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 20));
            NFUEntityStatics.addEffectSafe(this, new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 20));
        }
    }

    @Override
    public void updateInventory() {

    }

    @Override
    public void onAttack(LivingEntity target) {
        GOGAddonStatics.addEffectByDifficulty(target, MobEffects.MOVEMENT_SLOWDOWN, 0, 10 * 20, 20 * 20);
    }

    @Override
    public void onHurt(float amount, DamageSource damageSource) {

    }

    @Override
    public List<MobEffect> immuneToEffects() {
        return List.of();
    }

    @Override
    public void onDealDamage(LivingEntity target, float amount, DamageSource damageSource) {
        this.heal(amount);
        if (amount > 1) {
            NFUParticleStatics.sendHeartParticlesToEntityDefault(this, 0.0f, 2);
        }
        double convertDmg = GOGAddonConfigs.ValueCache.Gameplay.DHAMPIR_CONVERSION_DAMAGE;
        double convertChance = GOGAddonConfigs.ValueCache.Gameplay.DHAMPIR_CONVERSION_CHANCE;
        if (convertDmg >= 1d && convertChance > 0d) {
            int convertAmount = (int)Math.round(Math.floor((this.totalDamageDealt + amount) / convertDmg) - Math.floor(this.totalDamageDealt / convertDmg));
            for (int i = 0; i < convertAmount; ++i) {
                if (this.getRandom().nextDouble() <= convertChance) {
                    VampireEntity vampireEntity = this.convertTo(GOGAddonEntityTypes.VAMPIRE.getEntityType(), true);
                    if (vampireEntity != null) {
                        vampireEntity.playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 1.5f, 1.0f);
                        NFUParticleStatics.sendParticlesToEntity(vampireEntity, ParticleTypes.EXPLOSION, 0d, 1.5d, 5, 1.0d);
                        NFUParticleStatics.sendGlintParticlesToEntityDefault(vampireEntity, 0f, 20);
                        vampireEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 5 * 20));
                        vampireEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5 * 20, 2));
                        vampireEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5 * 20, 2));
                    }
                    return;
                }
            }
        }
        this.totalDamageDealt += amount;
    }

    @Override
    public void onDeath(DamageSource damageSource) {

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

    public static boolean checkSpawnRules(EntityType<? extends DhampirEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return GOGAddonStatics.MobStatics.nightGroundMobSpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }

}
