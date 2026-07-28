package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import org.jetbrains.annotations.ApiStatus;

public interface IRangedAttackBehaviors extends RangedAttackMob {

    public default IAdvancedMobBehaviors asBehaviors() {
        return (IAdvancedMobBehaviors) this;
    }

    @ApiStatus.NonExtendable
    public default AbstractGaiaEntity getMob() {
        return this.asBehaviors().getMob();
    }

}
