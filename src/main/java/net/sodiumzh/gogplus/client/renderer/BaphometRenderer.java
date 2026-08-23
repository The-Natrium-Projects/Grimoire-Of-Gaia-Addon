package net.sodiumzh.gogplus.client.renderer;

import gaia.client.renderer.layer.AuraLayer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.sodiumzh.gogplus.GOGPlus;
import net.sodiumzh.gogplus.client.model.BaphometModel;
import net.sodiumzh.gogplus.entity.mob.BaphometEntity;

public class BaphometRenderer extends MobRenderer<BaphometEntity, BaphometModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(GOGPlus.MOD_ID, "textures/entity/baphomet/baphomet.png");
    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(new ResourceLocation(GOGPlus.MOD_ID, "baphomet"), "main");

    public BaphometRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BaphometModel(pContext.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
        this.addLayer(new AuraLayer<>(this, () -> {
            return new BaphometModel(pContext.bakeLayer(LAYER_LOCATION));
        }));
    }

    @Override
    public ResourceLocation getTextureLocation(BaphometEntity pEntity) {
        return TEXTURE;
    }
}
