package net.sodiumzh.gogaddon.util;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.sodiumzh.nfu.util.NFUEntityStatics;

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
}
