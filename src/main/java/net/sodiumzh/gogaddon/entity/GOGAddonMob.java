package net.sodiumzh.gogaddon.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public interface GOGAddonMob {

    /**
     * Update the mob's state on aiStep. Should be invoked in aiStep() on both sides.
     */
    void updateOnAiStep();

    /**
     * Actions on attacking a target. Returns false if the attack should be cancelled.
     */
    void onAttack(LivingEntity target);

    /**
     * Actions on taking damage. Returns false if the damage should be cancelled.
     */
    void onHurt(float amount, DamageSource damageSource);

    /**
     * Check if the mob can be damaged.
     */
    boolean canHurt(float amount, DamageSource damageSource);

    List<MobEffect> immuneToEffects();
}
