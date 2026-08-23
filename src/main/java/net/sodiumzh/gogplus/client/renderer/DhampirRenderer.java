package net.sodiumzh.gogplus.client.renderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.sodiumzh.gogplus.GOGAddon;
import net.sodiumzh.gogplus.client.model.DhampirModel;
import net.sodiumzh.gogplus.client.renderer.layer.GOGAddonEyesLayer;
import net.sodiumzh.gogplus.entity.mob.DhampirEntity;

public class DhampirRenderer extends MobRenderer<DhampirEntity, DhampirModel> {

    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(new ResourceLocation(GOGAddon.MOD_ID, "dhampir"), "main");

    private static final ResourceLocation EYES_TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/dhampir/dhampir_eyes.png");
    private static final ResourceLocation TEXTURE =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/dhampir/dhampir.png");
    private static final RenderType EYES_LAYER = RenderType.eyes(EYES_TEXTURE);

    public DhampirRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DhampirModel(pContext.bakeLayer(LAYER_LOCATION)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, pContext.getModelSet(), pContext.getItemInHandRenderer()));
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
        this.addLayer(new GOGAddonEyesLayer<>(this, EYES_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(DhampirEntity pEntity) {
        return TEXTURE;
    }
}
