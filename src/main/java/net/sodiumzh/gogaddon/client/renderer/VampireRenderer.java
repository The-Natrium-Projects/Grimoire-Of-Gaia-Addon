package net.sodiumzh.gogaddon.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.client.model.VampireModel;
import net.sodiumzh.gogaddon.client.renderer.layer.GlowingLayer;
import net.sodiumzh.gogaddon.client.renderer.layer.VampireAuraLayer;
import net.sodiumzh.gogaddon.entity.VampireEntity;

public class VampireRenderer extends MobRenderer<VampireEntity, VampireModel> {

    private static final ResourceLocation EYE_TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/layer/eyes_vampire.png");
    private static final ResourceLocation TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/vampire.png");

    public VampireRenderer(EntityRendererProvider.Context context) {
        super(context, new VampireModel(context.bakeLayer(VampireModel.LAYER_LOCATION)), 0.5F);
        addLayer(new GlowingLayer<>(this, EYE_TEXTURE));
        addLayer(new VampireAuraLayer(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(VampireEntity entity) {
        return TEXTURE;
    }
}
