package net.sodiumzh.gogaddon.entity.mob;

import gaia.config.GaiaConfig;
import gaia.entity.AbstractAssistGaiaEntity;
import gaia.entity.type.IDayMob;
import gaia.registry.GaiaTags;
import gaia.util.SharedEntityData;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class KikimoraEntity extends AbstractAssistGaiaEntity implements IDayMob {

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

    @Override
    public float getBaseDefense() {
        return SharedEntityData.getBaseDefense1();
    }

    public static boolean checkSpawnRules(EntityType<? extends KikimoraEntity> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkDaysPassed(levelAccessor)
            && checkDaytime(levelAccessor)
            && checkTagBlocks(levelAccessor, pos, GaiaTags.GAIA_SPAWABLE_ON)
            && checkAboveSeaLevel(levelAccessor, pos)
            && checkGaiaDaySpawnRules(entityType, levelAccessor, spawnType, pos, random);
    }

}
