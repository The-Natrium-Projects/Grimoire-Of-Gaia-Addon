package net.sodiumzh.gogaddon.util;

import gaia.entity.AbstractGaiaEntity;
import gaia.registry.GaiaTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.sodiumzh.nfu.util.NFUEntityStatics;
import org.jetbrains.annotations.ApiStatus;

public class GOGAddonStatics {

    public static void addEffectByDifficulty(LivingEntity target, MobEffect effect, int durationEasy,
                                             int durationNormal, int durationHard, int amplifier) {
        int duration = switch (target.level().getDifficulty()) {
            case EASY -> durationEasy;
            case NORMAL -> durationNormal;
            case HARD -> durationHard;
            default -> 0;
        };
        if (duration > 0)
            NFUEntityStatics.addEffectSafe(target, effect, duration, amplifier);
    }

    public static void addEffectByDifficulty(LivingEntity target, MobEffect effect, int durationEasy,
                                             int durationNormal, int durationHard) {
        addEffectByDifficulty(target, effect, durationEasy, durationNormal, durationHard, 0);
    }

    @ApiStatus.NonExtendable
    public static abstract class MobStatics extends AbstractGaiaEntity {

        public MobStatics(EntityType<? extends Monster> entityType, Level level) {
            super(entityType, level);
            throw new RuntimeException();
        }

        @Override
        public float getBaseDefense() {
            throw new RuntimeException();
        }

        /**
         * General spawn rules for mobs spawned in day on the ground.
         */
        public static boolean dayGroundMobSpawnRules(EntityType<? extends Monster> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
            return checkDaysPassed(levelAccessor)
                && checkDaytime(levelAccessor)
                && checkTagBlocks(levelAccessor, pos, GaiaTags.GAIA_SPAWABLE_ON)
                && checkAboveSeaLevel(levelAccessor, pos)
                && checkGaiaDaySpawnRules(entityType, levelAccessor, spawnType, pos, random);
        }

        /**
         * General spawn rules for mobs spawned at night on the ground.
         */
        public static boolean nightGroundMobSpawnRules(EntityType<? extends Monster> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
            return AbstractGaiaEntity.checkDaysPassed(levelAccessor)
                && checkAboveSeaLevel(levelAccessor, pos)
                && checkMonsterSpawnRules(entityType, levelAccessor, spawnType, pos, random);
        }

        /**
         * General spawn rules for mobs spawned underground.
         */
        public static boolean undergroundMobSpawnRules(EntityType<? extends Monster> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
            return checkDaysPassed(levelAccessor)
                && checkBelowSeaLevel(levelAccessor, pos)
                && checkMonsterSpawnRules(entityType, levelAccessor, spawnType, pos, random);
        }

        /**
         * General spawn rules for mobs spawned in water at night.
         */
        public static boolean aqueousNightMobSpawnRules(EntityType<? extends Monster> entityType, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
            return checkDaysPassed(levelAccessor)
                && !checkDaytime(levelAccessor)
                && checkInWater(levelAccessor, pos, 5)
                && isDarkEnoughToSpawn(levelAccessor, pos, random)
                && checkNotPeaceful(levelAccessor)
                && random.nextInt(15) == 0;
        }
    }
}
