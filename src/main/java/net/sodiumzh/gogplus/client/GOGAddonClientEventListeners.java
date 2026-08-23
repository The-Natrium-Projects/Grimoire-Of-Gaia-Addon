package net.sodiumzh.gogplus.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.gogplus.client.model.*;
import net.sodiumzh.gogplus.client.renderer.*;
import net.sodiumzh.gogplus.registry.GOGPlusEntityTypes;

@Mod.EventBusSubscriber(modid = GOGPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GOGAddonClientEventListeners {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GOGPlusEntityTypes.VAMPIRE.getEntityType(), VampireRenderer::new);
        event.registerEntityRenderer(GOGPlusEntityTypes.BAPHOMET.getEntityType(), BaphometRenderer::new);
        event.registerEntityRenderer(GOGPlusEntityTypes.GORGON.getEntityType(), GorgonRenderer::new);
        event.registerEntityRenderer(GOGPlusEntityTypes.DHAMPIR.getEntityType(), DhampirRenderer::new);
        event.registerEntityRenderer(GOGPlusEntityTypes.KIKIMORA.getEntityType(), KikimoraRenderer::new);
        event.registerEntityRenderer(GOGPlusEntityTypes.SELKIE.getEntityType(), SelkieRenderer::new);
        event.registerEntityRenderer(GOGPlusEntityTypes.FUTAKUCHI_ONNA.getEntityType(), FutakuchiOnnaRenderer::new);
        event.registerEntityRenderer(GOGPlusEntityTypes.SAHUAGIN.getEntityType(), SahuaginRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VampireRenderer.LAYER_LOCATION, VampireModel::createBodyLayer);
        event.registerLayerDefinition(BaphometRenderer.LAYER_LOCATION, BaphometModel::createBodyLayer);
        event.registerLayerDefinition(GorgonRenderer.LAYER_LOCATION, GorgonModel::createBodyLayer);
        event.registerLayerDefinition(DhampirRenderer.LAYER_LOCATION, DhampirModel::createBodyLayer);
        event.registerLayerDefinition(KikimoraRenderer.LAYER_LOCATION, KikimoraModel::createBodyLayer);
        event.registerLayerDefinition(SelkieRenderer.LAYER_LOCATION, SelkieModel::createBodyLayer);
        event.registerLayerDefinition(FutakuchiOnnaRenderer.LAYER_LOCATION, FutakuchiOnnaModel::createBodyLayer);
        event.registerLayerDefinition(SahuaginRenderer.LAYER_LOCATION, SahuaginModel::createBodyLayer);
    }

}
