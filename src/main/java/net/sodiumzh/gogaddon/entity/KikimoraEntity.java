package net.sodiumzh.gogaddon.entity;

import gaia.config.GaiaConfig;
import gaia.entity.AbstractAssistGaiaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class KikimoraEntity extends AbstractAssistGaiaEntity {

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
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true);
        if (GaiaConfig.COMMON.allPassiveMobsHostile.get()) {
            this.targetSelector.addGoal(2, this.targetPlayerGoal);
        }
    }

    @Override
    public float getBaseDefense() {
        return 0;
    }

}
