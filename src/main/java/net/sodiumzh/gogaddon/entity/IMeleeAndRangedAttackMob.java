package net.sodiumzh.gogaddon.entity;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.sodiumzh.nfu.entity.IMobSpecific;

public interface IMeleeAndRangedAttackMob extends RangedAttackMob, IMobSpecific<PathfinderMob> {

    /**
     * If the mob is melee attack. Only valid on server.
     */
    public boolean isMelee();

}
