package net.sodiumzh.gogplus.entity.behavior;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.sodiumzh.gogplus.GOGAddon;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = GOGAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GOGAddonMobBehaviorMappings {

    private static final Map<EntityType<? extends AbstractGaiaEntity>, Function<AdvancedMobBehaviorComponent, IAdvancedMobBehaviors<? extends AbstractGaiaEntity>>>
        TABLE = new HashMap<>();

    public static <T extends AbstractGaiaEntity> void add(EntityType<T> type, Function<AdvancedMobBehaviorComponent, IAdvancedMobBehaviors<? super T>> behaviorProvider) {
        TABLE.put(type, behaviorProvider::apply);
    }

    public static Optional<Function<AdvancedMobBehaviorComponent, IAdvancedMobBehaviors<? extends AbstractGaiaEntity>>> get(EntityType<?> type) {
        return Optional.ofNullable(TABLE.get(type));
    }

    public static boolean contains(EntityType<?> type) {
        return TABLE.containsKey(type);
    }

    @SubscribeEvent
    public static void register(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
           // add(GaiaRegistry.VALKYRIE.getEntityType(), ValkyrieBehaviors::new);
        });
    }
}
