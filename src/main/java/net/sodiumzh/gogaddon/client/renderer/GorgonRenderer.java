package net.sodiumzh.gogaddon.client.renderer;

import gaia.client.renderer.layer.AuraLayer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.client.model.GorgonModel;
import net.sodiumzh.gogaddon.client.model.VampireModel;
import net.sodiumzh.gogaddon.client.renderer.layer.GOGAddonEyesLayer;
import net.sodiumzh.gogaddon.entity.GorgonEntity;
import net.sodiumzh.gogaddon.entity.VampireEntity;

public class GorgonRenderer extends MobRenderer<GorgonEntity, GorgonModel> {

    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(new ResourceLocation(GOGAddon.MOD_ID, "gorgon"), "main");

    private static final ResourceLocation EYES_TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/gorgon/gorgon_eyes.png");
    private static final ResourceLocation TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/gorgon/gorgon.png");
    private static final RenderType EYES_LAYER = RenderType.eyes(EYES_TEXTURE);

    public GorgonRenderer(EntityRendererProvider.Context context) {
        super(context, new GorgonModel(context.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new GOGAddonEyesLayer<>(this, EYES_LAYER));
        this.addLayer(new AuraLayer<>(this, () -> {
            return new GorgonModel(context.bakeLayer(LAYER_LOCATION));
        }));
    }

    @Override
    public ResourceLocation getTextureLocation(GorgonEntity entity) {
        return TEXTURE;
    }
}
