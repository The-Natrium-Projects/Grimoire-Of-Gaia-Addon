package net.sodiumzh.gogplus.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGAddon;
import net.sodiumzh.gogplus.entity.mob.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GOGAddon.MOD_ID)
public abstract class GOGAddonSpawnRules {

    public static void init() {}

    @SubscribeEvent
    public static void regSpawnPlacements(SpawnPlacementRegisterEvent event) {
        regDefaultSpawnPlacement(event, GOGAddonEntityTypes.BAPHOMET.getEntityType(), BaphometEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGAddonEntityTypes.DHAMPIR.getEntityType(), DhampirEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGAddonEntityTypes.FUTAKUCHI_ONNA.getEntityType(), FutakuchiOnnaEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGAddonEntityTypes.GORGON.getEntityType(), GorgonEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGAddonEntityTypes.KIKIMORA.getEntityType(), KikimoraEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGAddonEntityTypes.SAHUAGIN.getEntityType(), SahuaginEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGAddonEntityTypes.SELKIE.getEntityType(), SelkieEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGAddonEntityTypes.VAMPIRE.getEntityType(), VampireEntity::checkSpawnRules);
    }

    private static <T extends Entity> void regDefaultSpawnPlacement(
        SpawnPlacementRegisterEvent event,
        EntityType<T> type,
        SpawnPlacements.SpawnPredicate<T> rules)
    {
        event.register(
            type,
            SpawnPlacements.Type.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            rules,
            SpawnPlacementRegisterEvent.Operation.OR);
    }
}
