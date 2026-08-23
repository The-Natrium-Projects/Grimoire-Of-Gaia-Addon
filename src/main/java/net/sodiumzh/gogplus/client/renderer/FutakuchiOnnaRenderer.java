package net.sodiumzh.gogplus.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.sodiumzh.gogplus.GOGAddon;
import net.sodiumzh.gogplus.client.model.FutakuchiOnnaModel;
import net.sodiumzh.gogplus.entity.mob.FutakuchiOnnaEntity;

public class FutakuchiOnnaRenderer extends MobRenderer<FutakuchiOnnaEntity, FutakuchiOnnaModel> {

    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(new ResourceLocation(GOGAddon.MOD_ID, "futakuchi_onna"), "main");
    private static final ResourceLocation TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/futakuchi_onna/futakuchi_onna.png");

    public FutakuchiOnnaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FutakuchiOnnaModel(pContext.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(FutakuchiOnnaEntity pEntity) {
        return TEXTURE;
    }

}
