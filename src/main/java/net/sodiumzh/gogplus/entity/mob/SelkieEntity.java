package net.sodiumzh.gogplus.entity.mob;

import gaia.entity.type.IDayMob;
import gaia.registry.GaiaRegistry;
import gaia.util.RangedUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
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
import net.sodiumzh.gogplus.ai.goal.SelectedMeleeAttackGoal;
import net.sodiumzh.gogplus.ai.goal.SelectedRangedBowAttackGoal;
import net.sodiumzh.gogplus.entity.IMeleeAndRangedAttackMob;
import net.sodiumzh.gogplus.util.GOGAddonStatics;

public class SelkieEntity extends GOGPlusMob implements IMeleeAndRangedAttackMob, IDayMob {

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

}
