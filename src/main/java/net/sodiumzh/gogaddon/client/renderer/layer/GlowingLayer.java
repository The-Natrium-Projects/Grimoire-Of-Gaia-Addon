package net.sodiumzh.gogaddon.client.renderer.layer;


import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowingLayer<T extends LivingEntity, M extends EntityModel<T>>
    extends RenderLayer<T, M> {

    private static final RenderStateShard.TransparencyStateShard ADDITIVE_TRANSPARENCY =
        new RenderStateShard.TransparencyStateShard("additive_transparency",
            () -> {
                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE,
                    GlStateManager.DestFactor.ONE);
            },
            () -> {
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
            });

    private final ResourceLocation glowingTexture;

    public GlowingLayer(RenderLayerParent<T, M> renderer, ResourceLocation texture) {
        super(renderer);
        this.glowingTexture = texture;
    }

    @Override
    public void render(PoseStack poseStack,
                       MultiBufferSource bufferSource,
                       int packedLight,
                       T entity,
                       float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks,
                       float netHeadYaw, float headPitch) {

        if (entity.isInvisible()) return;

        // Full-bright render using the eyes render type (additive blend, max lightmap)
        VertexConsumer vertexConsumer = bufferSource.getBuffer(
            RenderType.eyes(glowingTexture));

        getParentModel().renderToBuffer(poseStack, vertexConsumer,
            LightTexture.FULL_BRIGHT,
            OverlayTexture.NO_OVERLAY,
            1.0F, 1.0F, 1.0F, 1.0F);
    }
}