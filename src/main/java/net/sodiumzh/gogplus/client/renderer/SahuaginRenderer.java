package net.sodiumzh.gogplus.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.sodiumzh.gogplus.GOGAddon;
import net.sodiumzh.gogplus.client.model.SahuaginModel;
import net.sodiumzh.gogplus.entity.mob.SahuaginEntity;

public class SahuaginRenderer extends MobRenderer<SahuaginEntity, SahuaginModel> {

    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(new ResourceLocation(GOGAddon.MOD_ID, "sahuagin"), "main");
    private static final ResourceLocation TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/sahuagin/sahuagin.png");

    public SahuaginRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SahuaginModel(pContext.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(SahuaginEntity pEntity) {
        return TEXTURE;
    }


}
