package net.sodiumzh.gogaddon.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.client.model.VampireModel;
import net.sodiumzh.gogaddon.client.renderer.VampireRenderer;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityTypes;

@Mod.EventBusSubscriber(modid = GOGAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GOGAddonClientEventListeners {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GOGAddonEntityTypes.VAMPIRE.getEntityType(), VampireRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VampireRenderer.LAYER_LOCATION, VampireModel::createBodyLayer);
    }

}
