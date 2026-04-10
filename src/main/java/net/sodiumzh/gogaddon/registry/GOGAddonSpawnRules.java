package net.sodiumzh.gogaddon.registry;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.sodiumzh.gogaddon.entity.mob.BaphometEntity;
import net.sodiumzh.gogaddon.entity.mob.KikimoraEntity;
import net.sodiumzh.gogaddon.entity.mob.SelkieEntity;

public abstract class GOGAddonSpawnRules {

    public static void init() {}

    @SubscribeEvent
    public static void regSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(
            GOGAddonEntityTypes.KIKIMORA.getEntityType(),
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            KikimoraEntity::checkSpawnRules,
            SpawnPlacementRegisterEvent.Operation.OR);
        event.register(
            GOGAddonEntityTypes.SELKIE.getEntityType(),
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            SelkieEntity::checkSpawnRules,
            SpawnPlacementRegisterEvent.Operation.OR);
        event.register(
            GOGAddonEntityTypes.BAPHOMET.getEntityType(),
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            BaphometEntity::checkSpawnRules,
            SpawnPlacementRegisterEvent.Operation.OR);
    }
}
