package net.sodiumzh.gogplus.entity;

import gaia.item.MerchantSpawnItem;
import gaia.registry.helper.GaiaMobType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.sodiumzh.gogplus.GOGAddon;
import net.sodiumzh.gogplus.registry.GOGAddonEntityTypes;
import net.sodiumzh.gogplus.registry.GOGAddonItems;
import net.sodiumzh.gogplus.registry.GOGAddonSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class GOGAddonMobReg<T extends Mob> {
    protected final String name;
    protected final RegistryObject<EntityType<T>> entityType;
    protected final GaiaMobType gaiaMobType;
    protected RegistryObject<Item> spawnEgg;
    protected RegistryObject<SoundEvent> SAY;
    protected RegistryObject<SoundEvent> HURT;
    protected RegistryObject<SoundEvent> DEATH;
    protected RegistryObject<SoundEvent> STEP;
    protected RegistryObject<SoundEvent> ATTACK;
    protected RegistryObject<SoundEvent> SAY_MALE;
    protected RegistryObject<SoundEvent> HURT_MALE;
    protected RegistryObject<SoundEvent> DEATH_MALE;
    protected RegistryObject<SoundEvent> STEP_MALE;
    protected RegistryObject<SoundEvent> ATTACK_MALE;
    protected boolean hasGenders;

    public @NotNull String getName() {
        return this.name;
    }

    public EntityType<T> getEntityType() {
        return this.entityType.get();
    }

    public GaiaMobType getGaiaSoundType() {
        return this.gaiaMobType;
    }

    public RegistryObject<Item> getSpawnEgg() {
        return this.spawnEgg;
    }

    public SoundEvent getSay() {
        return this.SAY == null ? null : (SoundEvent)this.SAY.get();
    }

    public SoundEvent getHurt() {
        return this.HURT == null ? null : (SoundEvent)this.HURT.get();
    }

    public SoundEvent getDeath() {
        return this.DEATH == null ? null : (SoundEvent)this.DEATH.get();
    }

    public SoundEvent getStep() {
        return this.STEP == null ? null : (SoundEvent)this.STEP.get();
    }

    public SoundEvent getAttack() {
        return this.ATTACK == null ? null : (SoundEvent)this.ATTACK.get();
    }

    public boolean hasGender() {
        return this.hasGenders;
    }

    public @Nullable SoundEvent getMaleSay() {
        return this.SAY_MALE == null ? null : (SoundEvent)this.SAY_MALE.get();
    }

    public @Nullable SoundEvent getMaleHurt() {
        return this.HURT_MALE == null ? null : (SoundEvent)this.HURT_MALE.get();
    }

    public @Nullable SoundEvent getMaleDeath() {
        return this.DEATH_MALE == null ? null : (SoundEvent)this.DEATH_MALE.get();
    }

    public @Nullable SoundEvent getMaleStep() {
        return this.STEP_MALE == null ? null : (SoundEvent)this.STEP_MALE.get();
    }

    public @Nullable SoundEvent getMaleAttack() {
        return this.ATTACK_MALE == null ? null : (SoundEvent)this.ATTACK_MALE.get();
    }

    public GOGAddonMobReg(String name, Supplier<EntityType.Builder<T>> builder, GaiaMobType mobType, int backgroundColor, int highlightColor, boolean say, boolean hurt, boolean death, boolean step, boolean attack, boolean hasGenders, boolean noSpawnEgg, boolean traderEgg) {
        this.name = name;
        this.entityType = GOGAddonEntityTypes.ENTITY_TYPES.register(name, () -> {
            return builder.get().build(name);
        });
        this.gaiaMobType = mobType;
        if (!noSpawnEgg) {
            if (traderEgg) {
                this.spawnEgg = GOGAddonItems.REG.register("spawn_" + name, () -> {
                    return new MerchantSpawnItem(this.entityType, new Item.Properties());
                });
            } else {
                this.spawnEgg = GOGAddonItems.REG.register(name + "_spawn_egg", () -> {
                    return new ForgeSpawnEggItem(this.entityType, backgroundColor, highlightColor, new Item.Properties());
                });
            }
        }

        this.SAY = say ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_say", () -> {
            return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_say"));
        }) : null;
        this.HURT = hurt ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_hurt", () -> {
            return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_hurt"));
        }) : null;
        this.DEATH = death ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_death", () -> {
            return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_death"));
        }) : null;
        this.STEP = step ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_step", () -> {
            return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_step"));
        }) : null;
        this.ATTACK = attack ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_attack", () -> {
            return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_attack"));
        }) : null;
        if (hasGenders) {
            this.SAY_MALE = say ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_male_say", () -> {
                return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_male_say"));
            }) : null;
            this.HURT_MALE = hurt ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_male_hurt", () -> {
                return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_male_hurt"));
            }) : null;
            this.DEATH_MALE = death ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_male_death", () -> {
                return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_male_death"));
            }) : null;
            this.STEP_MALE = step ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_male_step", () -> {
                return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_male_step"));
            }) : null;
            this.ATTACK_MALE = attack ? GOGAddonSoundEvents.SOUND_EVENTS.register(name + "_male_attack", () -> {
                return SoundEvent.createVariableRangeEvent(new ResourceLocation(GOGAddon.MOD_ID, name + "_male_attack"));
            }) : null;
        }

        this.hasGenders = hasGenders;
    }

    public static class Builder<T extends Mob> {
        private final String name;
        private final Supplier<EntityType.Builder<T>> builder;
        private final GaiaMobType gaiaMobType;
        private int backgroundColor;
        private int highlightColor;
        private boolean say;
        private boolean hurt;
        private boolean death;
        private boolean step;
        private boolean attack;
        private boolean hasGenders;
        private boolean noSpawnEgg;
        private boolean traderEgg;

        public Builder(String name, Supplier<EntityType.Builder<T>> builder, int backgroundColor, int highlightColor) {
            this.name = name;
            this.builder = builder;
            this.gaiaMobType = GaiaMobType.AGGRESSIVE;
            this.backgroundColor = backgroundColor;
            this.highlightColor = highlightColor;
        }

        public Builder(String name, Supplier<EntityType.Builder<T>> builder) {
            this.name = name;
            this.builder = builder;
            this.gaiaMobType = GaiaMobType.AGGRESSIVE;
            this.backgroundColor = 0;
            this.highlightColor = 0;
        }

        public Builder(String name, GaiaMobType mobType, Supplier<EntityType.Builder<T>> builder, int backgroundColor, int highlightColor) {
            this.name = name;
            this.builder = builder;
            this.gaiaMobType = mobType;
            this.backgroundColor = backgroundColor;
            this.highlightColor = highlightColor;
        }

        public Builder(String name, GaiaMobType mobType, Supplier<EntityType.Builder<T>> builder) {
            this.name = name;
            this.builder = builder;
            this.gaiaMobType = mobType;
            this.backgroundColor = 0;
            this.highlightColor = 0;
        }

        public Builder<T> withDefaultSounds() {
            this.say = true;
            this.hurt = true;
            this.death = true;
            return this;
        }

        public Builder<T> noSpawnEgg() {
            this.noSpawnEgg = true;
            return this;
        }

        public Builder<T> traderEgg() {
            this.traderEgg = true;
            return this;
        }

        public Builder<T> withSay() {
            this.say = true;
            return this;
        }

        public Builder<T> withHurt() {
            this.hurt = true;
            return this;
        }

        public Builder<T> withDeath() {
            this.death = true;
            return this;
        }

        public Builder<T> withGender() {
            this.hasGenders = true;
            return this;
        }

        public Builder<T> withStep() {
            this.step = true;
            return this;
        }

        public Builder<T> withAttack() {
            this.attack = true;
            return this;
        }

        public Builder<T> withEggColors(int backgroundColor, int highlightColor) {
            this.backgroundColor = backgroundColor;
            this.highlightColor = highlightColor;
            return this;
        }

        public GOGAddonMobReg<T> build() {
            return new GOGAddonMobReg<>(this.name, this.builder, this.gaiaMobType, this.backgroundColor, this.highlightColor, this.say, this.hurt, this.death, this.step, this.attack, this.hasGenders, this.noSpawnEgg, this.traderEgg);
        }
    }
}