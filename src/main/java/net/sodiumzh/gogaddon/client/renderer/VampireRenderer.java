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
import net.sodiumzh.gogaddon.client.model.VampireModel;
import net.sodiumzh.gogaddon.client.renderer.layer.GOGAddonEyesLayer;
import net.sodiumzh.gogaddon.entity.VampireEntity;

public class VampireRenderer extends MobRenderer<VampireEntity, VampireModel> {

    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(new ResourceLocation(GOGAddon.MOD_ID, "vampire"), "main");

    private static final ResourceLocation EYES_TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/vampire/vampire_eyes.png");
    private static final ResourceLocation TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/vampire/vampire.png");
    private static final RenderType EYES_LAYER = RenderType.eyes(EYES_TEXTURE);

    public VampireRenderer(EntityRendererProvider.Context context) {
        super(context, new VampireModel(context.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new CustomHeadLayer(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new ItemInHandLayer(this, context.getItemInHandRenderer()));
        this.addLayer(new GOGAddonEyesLayer<>(this, EYES_LAYER));
        this.addLayer(new AuraLayer(this, () -> {
            return new VampireModel(context.bakeLayer(LAYER_LOCATION));
        }));
    }

    @Override
    public ResourceLocation getTextureLocation(VampireEntity entity) {
        return TEXTURE;
    }
}
