package net.sodiumzh.gogaddon.ai.goal;

import gaia.entity.goal.MobAttackGoal;
import net.sodiumzh.gogaddon.entity.IMeleeAndRangedAttackMob;

public class SelectedMeleeAttackGoal extends MobAttackGoal {

    protected final IMeleeAndRangedAttackMob meleeAndRangedAttackMob;

    public SelectedMeleeAttackGoal(IMeleeAndRangedAttackMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob.asMob(), speedModifier, followingTargetEvenIfNotSeen);
        this.meleeAndRangedAttackMob = mob;
    }

    public IMeleeAndRangedAttackMob getMeleeAndRanged() {
        return meleeAndRangedAttackMob;
    }

    public boolean canUse() {
        return super.canUse() && meleeAndRangedAttackMob.isMelee();
    }

    public boolean canContinueToUse() {
        return super.canContinueToUse() && meleeAndRangedAttackMob.isMelee();
    }

}
