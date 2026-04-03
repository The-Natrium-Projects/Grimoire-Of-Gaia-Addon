package net.sodiumzh.gogaddon.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.client.model.BaphometModel;
import net.sodiumzh.gogaddon.client.model.KikimoraModel;
import net.sodiumzh.gogaddon.entity.BaphometEntity;
import net.sodiumzh.gogaddon.entity.KikimoraEntity;

public class KikimoraRenderer extends MobRenderer<KikimoraEntity, KikimoraModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/kikimora/kikimora.png");
    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(new ResourceLocation(GOGAddon.MOD_ID, "kikimora"), "main");

    public KikimoraRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KikimoraModel(pContext.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(KikimoraEntity pEntity) {
        return TEXTURE;
    }
}
