package net.sodiumzh.gogaddon.client;

import gaia.client.model.prop.AntHillModel;
import jdk.jfr.Enabled;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.client.model.*;
import net.sodiumzh.gogaddon.client.renderer.*;
import net.sodiumzh.gogaddon.registry.GOGAddonEntityTypes;

@Mod.EventBusSubscriber(modid = GOGAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GOGAddonClientEventListeners {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GOGAddonEntityTypes.VAMPIRE.getEntityType(), VampireRenderer::new);
        event.registerEntityRenderer(GOGAddonEntityTypes.BAPHOMET.getEntityType(), BaphometRenderer::new);
        event.registerEntityRenderer(GOGAddonEntityTypes.GORGON.getEntityType(), GorgonRenderer::new);
        event.registerEntityRenderer(GOGAddonEntityTypes.DHAMPIR.getEntityType(), DhampirRenderer::new);
        event.registerEntityRenderer(GOGAddonEntityTypes.KIKIMORA.getEntityType(), KikimoraRenderer::new);
        event.registerEntityRenderer(GOGAddonEntityTypes.SELKIE.getEntityType(), SelkieRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VampireRenderer.LAYER_LOCATION, VampireModel::createBodyLayer);
        event.registerLayerDefinition(BaphometRenderer.LAYER_LOCATION, BaphometModel::createBodyLayer);
        event.registerLayerDefinition(GorgonRenderer.LAYER_LOCATION, GorgonModel::createBodyLayer);
        event.registerLayerDefinition(DhampirRenderer.LAYER_LOCATION, DhampirModel::createBodyLayer);
        event.registerLayerDefinition(KikimoraRenderer.LAYER_LOCATION, KikimoraModel::createBodyLayer);
        event.registerLayerDefinition(SelkieRenderer.LAYER_LOCATION, SelkieModel::createBodyLayer);
    }

}
