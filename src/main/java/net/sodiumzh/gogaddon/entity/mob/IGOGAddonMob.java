package net.sodiumzh.gogaddon.entity.mob;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.List;

public interface IGOGAddonMob {

    /**
     * Update the mob's state on aiStep. Should be invoked in aiStep() on both sides.
     */
    void updateState();

    /**
     * Update the mob's inventory. Should be invoked in aiStep() on server side.
     */
    void updateInventory();

    /**
     * Actions on attacking a target. Returns false if the attack should be cancelled.
     */
    void onAttack(LivingEntity target);

    /**
     * Actions on taking damage. Returns false if the damage should be cancelled.
     */
    void onHurt(float amount, DamageSource damageSource);

    /**
     * Actions on dealing damage to a target. Handled in event listener and doesn't need to manually
     * handle in entity classes.
     */
    void onDealDamage(LivingEntity target, float amount, DamageSource damageSource);

    /**
     * Check if the mob can be damaged.
     */
    default boolean canHurt(float amount, DamageSource damageSource) {
        return true;
    };

    @Nonnull
    default List<MobEffect> immuneToEffects() {
        return List.of();
    }

    void onDeath(DamageSource damageSource);
}
