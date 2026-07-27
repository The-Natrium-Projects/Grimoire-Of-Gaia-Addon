package net.sodiumzh.gogaddon.entity.behavior;

import com.mojang.datafixers.types.Func;
import gaia.entity.AbstractGaiaEntity;
import net.minecraft.world.entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class GOGAddonMobBehaviorMappings {

    private static final Map<EntityType<? extends AbstractGaiaEntity>, Function<AbstractGaiaEntity, IGOGAddonMobBehaviors>>
        TABLE = new HashMap<>();

    public static void add(EntityType<? extends AbstractGaiaEntity> type, Function<AbstractGaiaEntity, IGOGAddonMobBehaviors> behaviorProvider) {
        TABLE.put(type, behaviorProvider);
    }

    public static Optional<Function<AbstractGaiaEntity, IGOGAddonMobBehaviors>> get(EntityType<?> type) {
        return Optional.ofNullable(TABLE.get(type));
    }

}
