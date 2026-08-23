package net.sodiumzh.gogplus.entity.behavior;

import gaia.entity.Valkyrie;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.sodiumzh.gogplus.entity.ai.GOGPlusRangedAttackGoal;
import net.sodiumzh.gogplus.registry.GOGPlusProjectileProviders;
import net.sodiumzh.nfu.entity.NFUItemProjectileEntity;
import net.sodiumzh.nfu.entity.component.EntityComponentAPI;
import net.sodiumzh.nfu.exception.ReflectionFailedException;
import net.sodiumzh.nfu.math.RandomSelection;
import net.sodiumzh.nfu.network.NFUDataSerializers;
import net.sodiumzh.nfu.reflection.CachedFieldSearchers;
import net.sodiumzh.nfu.util.NFUMathStatics;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ValkyrieBehaviors extends AdvancedMobBehaviors<Valkyrie> implements IRangedAttackBehaviors{

    private static final RandomSelection<Function<Mob, NFUItemProjectileEntity>> PROJECTILE_SELECTOR =
            new RandomSelection<>(GOGPlusProjectileProviders.VALKYRIE_COMMON_PROJECTILE)
                    .add(GOGPlusProjectileProviders.VALKYRIE_EXPLOSIVE_PROJECTILE, 0.2d)
                    .add(GOGPlusProjectileProviders.VALKYRIE_THUNDER_PROJECTILE, 0.2d)
                    .add(GOGPlusProjectileProviders.VALKYRIE_ICE_PROJECTILE, 0.2d);

    private MeleeAttackGoal meleeAttackGoal = null;

    public static Projectile addProjectile(Level level, Vec3 pos, Vec3 velocity, Supplier<? extends Projectile> factory) {
        Projectile proj = factory.get();
        proj.setPos(pos);
        proj.setDeltaMovement(velocity);
        level.addFreshEntity(proj);
        return proj;
    }

    public ValkyrieBehaviors(AdvancedMobBehaviorComponent component) {
        super(component);
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
        this.meleeAttackGoal = (MeleeAttackGoal) CachedFieldSearchers.getFieldValue(this.getMob(), Valkyrie.class, "meleeAttackGoal").orElseThrow(ReflectionFailedException::new);
    }

    @Override
    public void setupGoals() {
        this.getMob().goalSelector.addGoal(1, new GOGPlusRangedAttackGoal(this, 1.0, 3*20, 16f) {
            @Override
            public boolean canUse() {
                return super.canUse() && this.getMob().getTarget() != null && this.getMob().getTarget().distanceToSqr(this.getMob()) >= 16.0d;
            }
        });
        this.getMob().goalSelector.addGoal(1, new MeleeAttackGoal(this.getMob(), 1.3, true) {
            @Override
            public boolean canUse() {
                return super.canUse() && getMob().getTarget() != null && getMob().getTarget().distanceToSqr(getMob()) < 16.0d;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && getMob().getTarget() != null && getMob().getTarget().distanceToSqr(getMob()) < 16.0d;
            }
        });
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
    public void startBaseAiStep() {
        // Remove the original melee attack goal. We've added a same goal in this behavior class
        if (this.meleeAttackGoal != null)
            this.getMob().goalSelector.removeGoal(this.meleeAttackGoal);
    }

    @Override
    public void finishBaseAiStep() {

    }

    @Override
    public CompoundTag serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }

    private static Vec3 calculateTrajectory(Vec3 targetPos, Vec3 startingPos, double velocity, double gravity, double inaccuracy) {
        Vec3 inaccVec = NFUMathStatics.randomUnitVector().scale(inaccuracy);
        Vec3 dir = targetPos.subtract(startingPos).add(inaccVec);
        Vec3 vel = NFUMathStatics.parabolicTrajectoryFixedSpeed(dir, velocity, gravity).orElse(null);
        if (vel == null) {
            vel = new Vec3(dir.x, 0d, dir.z).normalize().add(0d, 1d, 0d).normalize().scale(0.5d);
        }
        return vel;
    }



    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        List<Vec3> positionOffsets;
        int type = 1;
        if (this.getMob().getHealth() > this.getMob().getMaxHealth() / 2d) {
            positionOffsets = List.of(new Vec3(0d, 1.5d, 0d));
            type = 1;
        } else if (this.getMob().getHealth() > this.getMob().getMaxHealth() / 4d) {
            positionOffsets = List.of(new Vec3(0d, 1.5d, 0d), new Vec3(1d, 0.5d, 0d), new Vec3(-1d, 0.5d, 0d));
            type = 2;
        } else {
            positionOffsets = List.of(new Vec3(0d, 2.5d, 0d), new Vec3(1d, 1.5d, 0d), new Vec3(-1d, 1.5d, 0d),
                    new Vec3(1d, 0.5d, 0d), new Vec3(-1d, 0.5d, 0d));
            type = 3;
        }

        List<Vec3> positions = positionOffsets.stream().map(v -> v.multiply(this.getMob().getForward().normalize()).add(this.getMob().getEyePosition()))
                .toList();
        for (int i = 0; i < positions.size(); ++i) {
            Vec3 vel = calculateTrajectory(target.getBoundingBox().getCenter().add(0d, target.getBoundingBox().getYsize() / 4d, 0d), positions.get(i), 1.5d, 0.06d, i + 0.5d);
            addProjectile(this.getMob().level(), positions.get(i), vel, () -> PROJECTILE_SELECTOR.select(this.getMob().getRandom()).apply(this.getMob()));
        }
        this.getMob().swing(InteractionHand.MAIN_HAND);
    }


}
