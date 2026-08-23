package net.sodiumzh.gogplus.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.gogplus.entity.mob.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GOGPlus.MOD_ID)
public abstract class GOGPlusSpawnRules {

    public static void init() {}

    @SubscribeEvent
    public static void regSpawnPlacements(SpawnPlacementRegisterEvent event) {
        regDefaultSpawnPlacement(event, GOGPlusEntityTypes.BAPHOMET.getEntityType(), BaphometEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGPlusEntityTypes.DHAMPIR.getEntityType(), DhampirEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGPlusEntityTypes.FUTAKUCHI_ONNA.getEntityType(), FutakuchiOnnaEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGPlusEntityTypes.GORGON.getEntityType(), GorgonEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGPlusEntityTypes.KIKIMORA.getEntityType(), KikimoraEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGPlusEntityTypes.SAHUAGIN.getEntityType(), SahuaginEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGPlusEntityTypes.SELKIE.getEntityType(), SelkieEntity::checkSpawnRules);
        regDefaultSpawnPlacement(event, GOGPlusEntityTypes.VAMPIRE.getEntityType(), VampireEntity::checkSpawnRules);
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
