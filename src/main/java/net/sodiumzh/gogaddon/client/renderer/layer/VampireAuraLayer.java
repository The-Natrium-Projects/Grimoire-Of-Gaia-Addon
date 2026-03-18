package net.sodiumzh.gogaddon.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.client.model.VampireModel;
import net.sodiumzh.gogaddon.entity.VampireEntity;

@OnlyIn(Dist.CLIENT)
public class VampireAuraLayer extends RenderLayer<VampireEntity, VampireModel> {
    private static final ResourceLocation ARMOR =
        new ResourceLocation(GOGAddon.MOD_ID, "textures/entity/layer/aura_immune_ranged.png");

    // A secondary model instance for the aura (inflated by 0.5F).
    // In 1.20.1, layer definitions are baked; pass the inflated baked layer.
    private final VampireModel auraModel;

    public VampireAuraLayer(RenderLayerParent<VampireEntity, VampireModel> renderer,
                            EntityModelSet modelSet) {
        super(renderer);
        // Register a separate ModelLayerLocation for the inflated (0.5F) aura variant:
        // ModelLayerLocation AURA_LAYER_LOCATION = new ModelLayerLocation(
        //     new ResourceLocation(GaiaReference.MOD_ID, "vampire"), "aura");
        // and create its LayerDefinition with the 0.5F inflation applied in createBodyLayer(float).
        this.auraModel = new VampireModel(modelSet.bakeLayer(VampireModel.AURA_LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack poseStack,
                       MultiBufferSource bufferSource,
                       int packedLight,
                       VampireEntity entity,
                       float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {

        if (!entity.isArmored()) return;

        // Copy animation state from the parent model
        this.auraModel.copyPropertiesFrom(this.getParentModel());
        this.auraModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        this.auraModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(
            RenderType.energySwirl(ARMOR,
                Mth.cos((entity.tickCount + partialTicks) * 0.02F) * 3.0F,
                (entity.tickCount + partialTicks) * 0.01F));

        this.auraModel.renderToBuffer(poseStack, vertexConsumer, packedLight,
            OverlayTexture.NO_OVERLAY,
            0.5F, 0.5F, 0.5F, 1.0F);
    }
}