package net.sodiumzh.gogaddon.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.client.model.SelkieModel;
import net.sodiumzh.gogaddon.entity.mob.SelkieEntity;

public class SelkieRenderer extends MobRenderer<SelkieEntity, SelkieModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/selkie/selkie.png");
    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(new ResourceLocation(GOGAddon.MOD_ID, "selkie"), "main");

    public SelkieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SelkieModel(pContext.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(SelkieEntity pEntity) {
        return TEXTURE;
    }
}
