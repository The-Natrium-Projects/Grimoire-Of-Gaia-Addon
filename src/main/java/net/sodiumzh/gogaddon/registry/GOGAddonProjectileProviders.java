package net.sodiumzh.gogaddon.registry;

import com.github.mechalopa.hmag.registry.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.sodiumzh.nfu.entity.NFUEffectZoneEntity;
import net.sodiumzh.nfu.entity.NFUItemProjectileEntity;
import net.sodiumzh.nfu.math.IInequalityPattern3D;
import net.sodiumzh.nfu.math.ThreadSafeRandomSource;
import net.sodiumzh.nfu.util.NFUMathStatics;

import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class GOGAddonProjectileProviders {

    private static final RandomSource RND = new ThreadSafeRandomSource();

    // Valkyrie projectiles on friending

    public static final Function<Mob, NFUItemProjectileEntity> VALKYRIE_THUNDER_PROJECTILE = owner ->
            NFUItemProjectileEntity.create(owner)
                    .setLifetime(10 * 20)
                    .setGravity(0.06f)
                    .setItem(Items.ENDER_PEARL.getDefaultInstance())
                    .particle(ParticleTypes.SMOKE, 10)
                    .setLiquidResistanceFactor(0.2f)
                    .setAirResistanceFactor(0.01f)
                    .setIdentifier(new ResourceLocation("gogaddon:valkyrie_thunder_projectile"))
                    .setOnHitBlockOrLiving((proj, h) -> {
                        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, proj.level());
                        lightningBolt.setPos(proj.position());
                        lightningBolt.setDamage((float)owner.getAttributeValue(Attributes.ATTACK_DAMAGE));
                        proj.level().addFreshEntity(lightningBolt);
                        proj.discard();
                    })
                    .setOnTick(proj -> {
                        if (proj.level().getBlockState(proj.blockPosition()).liquid())
                            proj.discard();
                    });

    public static final Function<Mob, NFUItemProjectileEntity> VALKYRIE_EXPLOSIVE_PROJECTILE = owner ->
            NFUItemProjectileEntity.create(owner)
                    .setLifetime(10 * 20)
                    .setGravity(0.06f)
                    .setItem(Items.FIRE_CHARGE.getDefaultInstance())
                    .particle(ParticleTypes.FLAME, 10)
                    .setLiquidResistanceFactor(0.2f)
                    .setAirResistanceFactor(0.01f)
                    .setIdentifier(new ResourceLocation("gogaddon:valkyrie_explosive_projectile"))
                    .setOnHitBlockOrLiving((proj, h) -> {
                        if (h instanceof EntityHitResult eh && !(eh.getEntity() instanceof LivingEntity)) return;
                        proj.level().explode(proj,
                                new DamageSource(owner.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.INDIRECT_MAGIC), proj, owner, proj.position()),
                                null,
                                proj.getBoundingBox().getCenter(),
                                (float)owner.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.05f + 1.5f,
                                false,
                                proj.level().getRandom().nextDouble() < 0.25d ? Level.ExplosionInteraction.TNT : Level.ExplosionInteraction.NONE);
                        proj.discard();
                    });

    public static final Function<Mob, NFUEffectZoneEntity> VALKYRIE_ICE_ZONE = owner ->
            NFUEffectZoneEntity.create(owner).setScale(6d, 6d)
                    .setLifetime(10 * 20)
                    .setGravity(0)
                    .particle(ParticleTypes.SNOWFLAKE, 200)
                    .particleAreaShape(IInequalityPattern3D.SPHERE.get().inequality())
                    .setBlockOverlapFilter((z, pos, bs) -> bs.is(Blocks.FIRE))
                    .setIdentifier(new ResourceLocation("gogaddon:valkyrie_ice_zone"))
                    .setOnServerLivingOverlap((z, e) -> {
                        if (!e.equals(owner)
                                && e.getBoundingBox().getCenter().distanceToSqr(z.getBoundingBox().getCenter()) <= 36d) {
                            e.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5 * 20, 2));
                            e.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5 * 20, 3));
                            if (e.tickCount % 10 == 0)
                                e.hurt(new DamageSource(e.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FREEZE),
                                                z, owner, e.position()),
                                        (float)owner.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.2f);
                        }
                    })
                    .setServerBlockOverlap((z, pos, bs) -> {
                        z.level().setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                        z.level().playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (z.level().random.nextFloat() - z.level().random.nextFloat()) * 0.8F);
                    });

    public static final Function<Mob, NFUItemProjectileEntity> VALKYRIE_ICE_PROJECTILE = owner ->
            NFUItemProjectileEntity.create(owner)
                    .setLifetime(10 * 20)
                    .setGravity(0.06f)
                    .setItem(Items.SNOWBALL.getDefaultInstance())
                    .particle(ParticleTypes.SNOWFLAKE, 10)
                    .setLiquidResistanceFactor(0.2f)
                    .setAirResistanceFactor(0.01f)
                    .setIdentifier(new ResourceLocation("gogaddon:valkyrie_ice_projectile"))
                    .setOnHitBlockOrLiving((proj, h) -> {
                        if (h instanceof EntityHitResult eh && !(eh.getEntity() instanceof LivingEntity)) return;
                        var iceZone = VALKYRIE_ICE_ZONE.apply(owner);
                        iceZone.alignCenterTo(proj.position());
                        proj.level().addFreshEntity(iceZone);
                        proj.discard();
                    });

    public static final Function<Mob, NFUItemProjectileEntity> VALKYRIE_COMMON_PROJECTILE_FRAGMENT = owner ->
            NFUItemProjectileEntity.create(owner)
                    .setLifetime(10 * 20)
                    .setGravity(0.02f)
                    .setItem(Items.NETHER_STAR.getDefaultInstance())
                    .particle(ParticleTypes.CRIT, 10)
                    .setLiquidResistanceFactor(0.2f)
                    .setAirResistanceFactor(0.01f)
                    .setHitIgnoresOwner(true)
                    .setIdentifier(new ResourceLocation("gogaddon:valkyrie_common_projectile_fragment"))
                    .setOnHitLiving((proj, h) -> {
                        h.getEntity().hurt(new DamageSource(
                                        proj.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.INDIRECT_MAGIC),
                                        proj, owner, h.getEntity().position()),
                                (float)owner.getAttributeValue(Attributes.ATTACK_DAMAGE));
                        proj.discard();
                    });
    
    public static final Function<Mob, NFUItemProjectileEntity> VALKYRIE_COMMON_PROJECTILE = owner ->
            NFUItemProjectileEntity.create(owner)
                    .setLifetime(10 * 20)
                    .setGravity(0.06f)
                    .setItem(Items.NETHER_STAR.getDefaultInstance())
                    .particle(ParticleTypes.CRIT, 10)
                    .setLiquidResistanceFactor(0.2f)
                    .setAirResistanceFactor(0.01f)
                    .setHitIgnoresOwner(true)
                    .setIdentifier(new ResourceLocation("gogaddon:valkyrie_common_projectile"))
                    .setOnHitLiving((proj, h) -> {
                        h.getEntity().hurt(new DamageSource(
                                        proj.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.INDIRECT_MAGIC),
                                        proj, owner, h.getEntity().position()),
                                (float)owner.getAttributeValue(Attributes.ATTACK_DAMAGE) / 2f);
                        proj.discard();
                    })
                    // Split to 5 fragments when hit a block
                    .setOnHitBlock((proj, bhr) -> {
                        Vec3 normal = new Vec3(bhr.getDirection().getNormal().getX(), bhr.getDirection().getNormal(). getY(), bhr.getDirection().getNormal().getZ());
                        Vec3 velocityNormal = normal.multiply(proj.getDeltaMovement());
                        double speedPlanar = proj.getDeltaMovement().subtract(velocityNormal).length();
                        Vec3 velPlanar0 = NFUMathStatics.rotateVector(bhr.getDirection().getNormal().getY() != 0 ? new Vec3(1, 0, 0) : new Vec3(0, 1, 0),
                                normal, RND.nextDouble() * 72d);     // A vector orthogonal to the normal vector
                        Vec3 pos = proj.position();
                        Level level = proj.level();
                        proj.discard();
                        DoubleStream.of(0d, 1d, 2d, 3d, 4d)
                                .map(i -> 72 * i)   // Rotation angles in degrees
                                .mapToObj(i -> NFUMathStatics.rotateVector(velPlanar0, normal, i).normalize())  // Velocity directions
                                .map(v -> v.add(normal.scale(0.57735d)/* 30 degrees */).normalize().scale(speedPlanar / 4d))     // Velocity vectors
                                .forEach(v -> {
                                    NFUItemProjectileEntity fragment = VALKYRIE_COMMON_PROJECTILE_FRAGMENT.apply((Mob)proj.getOwner());
                                    fragment.setPos(pos.add(v.normalize().scale(0.1d)).add(normal.scale(0.2d)));
                                    fragment.setDeltaMovement(v);
                                    level.addFreshEntity(fragment);
                                });
                    });

}
