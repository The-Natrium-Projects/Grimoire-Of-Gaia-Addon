package net.sodiumzh.gogaddon.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.sodiumzh.nfu.entity.component.EntityComponentAPI;
import net.sodiumzh.nfu.network.NFUDataSerializers;

public class ValkyrieBehaviors extends GOGAddonMobBehaviors implements IRangedAttackBehaviors{

    public ValkyrieBehaviors(AbstractGaiaEntity mob) {
        super(mob);
    }

    @Override
    public void initialize() {
        EntityComponentAPI.getDefaultSyncher(this.getMob())
            .createSynchedData("flying", NFUDataSerializers.BOOLEAN, false, false);
    }

    // Accessible on both sides, synched
    public boolean isFlying() {
        return EntityComponentAPI.getDefaultSyncher(this.getMob())
            .getSynchedData("flying", Boolean.class)
            .orElse(false);
    }

    // Only on server
    public void setFlying(boolean value) {
        EntityComponentAPI.getDefaultSyncher(this.getMob())
            .setSynchedData("flying", Boolean.class, Boolean.valueOf(value));
    }


    @Override
    public void joinLevel() {

    }

    @Override
    public void setupGoals(AbstractGaiaEntity mob) {

    }

    @Override
    public void startTick() {

    }

    @Override
    public void finishTick() {

    }

    @Override
    public void startAiStep() {
    }

    @Override
    public void finishAiStep() {

    }

    @Override
    public CompoundTag serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }

    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {

    }
}
