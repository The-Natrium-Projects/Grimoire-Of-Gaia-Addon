package net.sodiumzh.gogaddon.ai.goal;

import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.sodiumzh.gogaddon.entity.IMeleeAndRangedAttackMob;

public class SelectedRangedBowAttackGoal<T extends Monster & IMeleeAndRangedAttackMob> extends RangedBowAttackGoal<T> {

    protected T mob;

    public SelectedRangedBowAttackGoal(T pMob, double pSpeedModifier, int pAttackIntervalMin, float pAttackRadius) {
        super(pMob, pSpeedModifier, pAttackIntervalMin, pAttackRadius);
        this.mob = pMob;
    }

    public boolean canUse() {
        return super.canUse() && !mob.isMelee();
    }

    public boolean canContinueToUse() {
        return super.canContinueToUse() && !mob.isMelee();
    }


}
