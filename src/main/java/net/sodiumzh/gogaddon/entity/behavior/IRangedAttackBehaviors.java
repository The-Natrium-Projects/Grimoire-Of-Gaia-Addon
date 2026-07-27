package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import org.jetbrains.annotations.ApiStatus;

public interface IRangedAttackBehaviors extends RangedAttackMob {

    public default IGOGAddonMobBehaviors asBehaviors() {
        return (IGOGAddonMobBehaviors) this;
    }

    @ApiStatus.NonExtendable
    public default AbstractGaiaEntity getMob() {
        return this.asBehaviors().getMob();
    }

}
