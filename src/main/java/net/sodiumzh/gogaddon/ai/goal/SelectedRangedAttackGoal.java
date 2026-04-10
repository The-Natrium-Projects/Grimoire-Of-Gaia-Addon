package net.sodiumzh.gogaddon.ai.goal;

import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.sodiumzh.gogaddon.entity.IMeleeAndRangedAttackMob;

public class SelectedRangedAttackGoal extends RangedAttackGoal {

    protected final IMeleeAndRangedAttackMob meleeAndRangedAttackMob;

    public SelectedRangedAttackGoal(IMeleeAndRangedAttackMob mob, double pSpeedModifier, int pAttackInterval, float pAttackRadius) {
        super(mob, pSpeedModifier, pAttackInterval, pAttackRadius);
        this.meleeAndRangedAttackMob = mob;
    }

    public SelectedRangedAttackGoal(IMeleeAndRangedAttackMob mob, double pSpeedModifier, int pAttackIntervalMin, int pAttackIntervalMax, float pAttackRadius) {
        super(mob, pSpeedModifier, pAttackIntervalMin, pAttackIntervalMax, pAttackRadius);
        this.meleeAndRangedAttackMob = mob;
    }

    public boolean canUse() {
        return super.canUse() && !meleeAndRangedAttackMob.isMelee();
    }

    public boolean canContinueToUse() {
        return super.canContinueToUse() && !meleeAndRangedAttackMob.isMelee();
    }
}
