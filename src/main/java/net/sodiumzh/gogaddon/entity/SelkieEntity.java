package net.sodiumzh.gogaddon.entity;

import gaia.entity.AbstractGaiaEntity;
import gaia.entity.goal.MobAttackGoal;
import gaia.util.RangedUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SelkieEntity extends AbstractGaiaEntity implements RangedAttackMob {

    public SelkieEntity(EntityType<? extends SelkieEntity> entityType, Level level) {
        super(entityType, level);
    }
    private boolean isRangedAttackMode = false;

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RangedBowAttackGoal<SelkieEntity>(this, 1.25, 20, 15.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && isRangedAttackMode();
            }
        });
        this.goalSelector.addGoal(1, new MobAttackGoal(this, 1.25, true){
            @Override
            public boolean canUse() {
                return super.canUse() && !isRangedAttackMode();
            }
        });
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(2, this.targetPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public float getBaseDefense() {
        return 0;
    }

    public boolean isRangedAttackMode() {
        return isRangedAttackMode;
    }

    public SelkieEntity setRangedAttackMode(boolean rangedAttackMode) {
        isRangedAttackMode = rangedAttackMode;
        return this;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        if (pTarget.isAlive()) {
            RangedUtil.rangedAttack(pTarget, this, pVelocity);
        }
    }
}
