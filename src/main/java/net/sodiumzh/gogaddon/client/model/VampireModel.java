package net.sodiumzh.gogaddon.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sodiumzh.gogaddon.GOGAddon;
import net.sodiumzh.gogaddon.entity.VampireEntity;

@OnlyIn(Dist.CLIENT)
public class VampireModel extends EntityModel<VampireEntity> implements HeadedModel, ArmedModel {

    // ── Animation state ───────────────────────────────────────────────────────
    /** Attack swing progress in [0, 1], equivalent to {@code swingProgress} in 1.12. */
    public float attackTime;

    // ── Part references ───────────────────────────────────────────────────────
    private final ModelPart anchor;

    // direct children of anchor
    private final ModelPart head;
    private final ModelPart headeyes;
    private final ModelPart headaccessory;
    private final ModelPart neck;
    private final ModelPart bodytop;
    private final ModelPart bodymiddle;
    private final ModelPart bodymiddlebutton;
    private final ModelPart bodybottom;
    private final ModelPart rightchest;
    private final ModelPart leftchest;
    private final ModelPart rightarm;   // child: rightshoulder
    private final ModelPart leftarm;    // child: leftshoulder
    private final ModelPart rightleg;
    private final ModelPart leftleg;
    private final ModelPart mantle;
    private final ModelPart cloak1;     // child chain: cloak2 → cloak3 → cloak4
    private final ModelPart waist;
    private final ModelPart waist1;
    private final ModelPart waist2;     // child chain: waist3 → waist4

    // ── Constructor ───────────────────────────────────────────────────────────
    public VampireModel(ModelPart root) {
        this.anchor         = root.getChild("anchor");
        this.head           = anchor.getChild("head");
        this.headeyes       = anchor.getChild("headeyes");
        this.headaccessory  = anchor.getChild("headaccessory");
        this.neck           = anchor.getChild("neck");
        this.bodytop        = anchor.getChild("bodytop");
        this.bodymiddle     = anchor.getChild("bodymiddle");
        this.bodymiddlebutton = anchor.getChild("bodymiddlebutton");
        this.bodybottom     = anchor.getChild("bodybottom");
        this.rightchest     = anchor.getChild("rightchest");
        this.leftchest      = anchor.getChild("leftchest");
        this.rightarm       = anchor.getChild("rightarm");
        this.leftarm        = anchor.getChild("leftarm");
        this.rightleg       = anchor.getChild("rightleg");
        this.leftleg        = anchor.getChild("leftleg");
        this.mantle         = anchor.getChild("mantle");
        this.cloak1         = anchor.getChild("cloak1");
        this.waist          = anchor.getChild("waist");
        this.waist1         = anchor.getChild("waist1");
        this.waist2         = anchor.getChild("waist2");
    }

    // ── Layer definitions ─────────────────────────────────────────────────────

    /** Standard (no inflation) layer definition. */
    public static LayerDefinition createBodyLayer() {
        return createBodyLayer(0.0F);
    }

    /** Inflated layer definition for the aura layer (inflation = 0.5F). */
    public static LayerDefinition createAuraLayer() {
        return createBodyLayer(0.5F);
    }

    /**
     * Shared geometry builder. {@code inflation} is added uniformly to every
     * cube so the aura variant sits slightly outside the base model.
     *
     * <p><b>Coordinate notes</b><br>
     * The original model used an {@code anchor} part with an absolute
     * rotationPoint of {@code (0, -12, 0)} in 1.12 world-space (where entity
     * Y=0 maps to MC's Y=24). All child rotation-points were also expressed in
     * that same absolute space, so every child offset below is stored
     * <em>relative to the anchor</em> after subtracting the anchor's own
     * origin (matching what {@code convertToChild} did at runtime).
     * The cloak / waist chains likewise store each segment relative to its
     * immediate parent.
     */
    private static LayerDefinition createBodyLayer(float inf) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // anchor – tiny invisible pivot that bobs up and down
        PartDefinition anchor = root.addOrReplaceChild("anchor",
            CubeListBuilder.create().texOffs(0, 0)
                .addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, new CubeDeformation(inf)),
            PartPose.offset(0F, 12F, 0F));   // entity origin Y=24 → anchor at Y=12 in model space

        // ── head cluster ────────────────────────────────────────────────────
        // All head parts share the same absolute point (0, -8, 0) → relative to anchor: (0, -20, 0)
        anchor.addOrReplaceChild("head",
            CubeListBuilder.create().texOffs(0, 0)
                .addBox(-3F, -6F, -3F, 6, 6, 6, new CubeDeformation(inf)),
            PartPose.offset(0F, -20F, 0F));

        anchor.addOrReplaceChild("headeyes",
            CubeListBuilder.create().texOffs(24, 0)
                .addBox(-3F, -6F, -3.1F, 6, 6, 0, new CubeDeformation(inf)),
            PartPose.offset(0F, -20F, 0F));

        anchor.addOrReplaceChild("headaccessory",
            CubeListBuilder.create().texOffs(36, 0)
                .addBox(-3.5F, -6.5F, -3.5F, 7, 7, 7, new CubeDeformation(inf)),
            PartPose.offset(0F, -20F, 0F));

        anchor.addOrReplaceChild("neck",
            CubeListBuilder.create().texOffs(0, 12)
                .addBox(-1F, -1F, -1F, 2, 2, 2, new CubeDeformation(inf)),
            PartPose.offset(0F, -20F, 0F));

        // ── body ────────────────────────────────────────────────────────────
        anchor.addOrReplaceChild("bodytop",
            CubeListBuilder.create().texOffs(0, 16)
                .addBox(-2.5F, 0F, -1.5F, 5, 6, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -20F, 0F, -0.0872665F, 0F, 0F));

        anchor.addOrReplaceChild("bodymiddle",
            CubeListBuilder.create().texOffs(0, 25)
                .addBox(-2F, 5.5F, -1.5F, 4, 3, 2, new CubeDeformation(inf)),
            PartPose.offset(0F, -20F, 0F));

        anchor.addOrReplaceChild("bodymiddlebutton",
            CubeListBuilder.create().texOffs(0, 25)
                .addBox(-0.5F, 6F, -1.6F, 1, 2, 0, new CubeDeformation(inf)),
            PartPose.offset(0F, -20F, 0F));

        anchor.addOrReplaceChild("bodybottom",
            CubeListBuilder.create().texOffs(0, 30)
                .addBox(-3F, 8F, -2.5F, 6, 3, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -20F, 0F, 0.0872665F, 0F, 0F));

        // ── chest details ───────────────────────────────────────────────────
        // rightchest absolute: (-1.3, -6, -1.5) → relative to anchor at (0,-12,0): (-1.3, -18, -1.5)
        anchor.addOrReplaceChild("rightchest",
            CubeListBuilder.create().texOffs(0, 36)
                .addBox(-1F, -1F, -1F, 2, 2, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(-1.3F, -18F, -1.5F, 0.7853982F, 0.1745329F, 0.0872665F));

        anchor.addOrReplaceChild("leftchest",
            CubeListBuilder.create().texOffs(0, 36).mirror()
                .addBox(-1F, -1F, -1F, 2, 2, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(1.3F, -18F, -1.5F, 0.7853982F, -0.1745329F, -0.0872665F));

        // ── arms (shoulders are children) ───────────────────────────────────
        // rightarm absolute: (-2.5, -6.5, 0) → relative to anchor: (-2.5, -18.5, 0)
        PartDefinition rightarm = anchor.addOrReplaceChild("rightarm",
            CubeListBuilder.create().texOffs(16, 12)
                .addBox(-2F, -1F, -1F, 2, 12, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(-2.5F, -18.5F, 0F, 0.0872665F, 0F, 0.1745329F));

        // rightshoulder in 1.12 had the same rotationPoint as rightarm but was
        // stored absolutely; after convertToChild its relative offset becomes (0,0,0)
        // and its rotation becomes (xR - parentXR, ...) = (0.0872665 - 0.0872665, 0, 0.0872665 - 0.1745329)
        rightarm.addOrReplaceChild("rightshoulder",
            CubeListBuilder.create().texOffs(80, 0)
                .addBox(-2.5F, -1F, -1.5F, 3, 4, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.0872664F));

        PartDefinition leftarm = anchor.addOrReplaceChild("leftarm",
            CubeListBuilder.create().texOffs(16, 12).mirror()
                .addBox(0F, -1F, -1F, 2, 12, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(2.5F, -18.5F, 0F, 0.0872665F, 0F, -0.1745329F));

        leftarm.addOrReplaceChild("leftshoulder",
            CubeListBuilder.create().texOffs(80, 0).mirror()
                .addBox(-0.5F, -1F, -1.5F, 3, 4, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.0872664F));

        // ── legs ────────────────────────────────────────────────────────────
        // rightleg absolute: (-2, 2, 0) → relative to anchor at (0,-12,0): (-2, 14, 0)
        anchor.addOrReplaceChild("rightleg",
            CubeListBuilder.create().texOffs(24, 12)
                .addBox(-1.5F, -1F, -1.5F, 3, 14, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(-2F, 14F, 0F, 0.0872665F, 0F, -0.0349066F));

        anchor.addOrReplaceChild("leftleg",
            CubeListBuilder.create().texOffs(24, 12)
                .addBox(-1.5F, -1F, -1.5F, 3, 14, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(2F, 14F, 0F, -0.0872665F, 0F, 0.0349066F));

        // ── mantle ──────────────────────────────────────────────────────────
        // mantle absolute: (0, -8, 0) → relative to anchor: (0, -20, 0)
        anchor.addOrReplaceChild("mantle",
            CubeListBuilder.create().texOffs(36, 14)
                .addBox(-5F, -6F, -2F, 10, 7, 6, new CubeDeformation(inf)),
            PartPose.offset(0F, -20F, 0F));

        // ── cloak chain ─────────────────────────────────────────────────────
        // cloak1 absolute: (0, -7, 1)  → relative to anchor at (0,-12,0): (0, -19, 1)
        PartDefinition cloak1 = anchor.addOrReplaceChild("cloak1",
            CubeListBuilder.create().texOffs(36, 27)
                .addBox(-6.5F, 0F, 0F, 13, 4, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, -19F, 1F));

        // cloak2 absolute: (0, -3, 4)  → relative to cloak1 at (0,-7,1): (0, 4, 3)
        PartDefinition cloak2 = cloak1.addOrReplaceChild("cloak2",
            CubeListBuilder.create().texOffs(36, 34)
                .addBox(-7F, 0F, -4F, 14, 5, 4, new CubeDeformation(inf)),
            PartPose.offset(0F, 4F, 3F));

        // cloak3 absolute: (0, 2, 4)   → relative to cloak2 at (0,-3,4): (0, 5, 0)
        PartDefinition cloak3 = cloak2.addOrReplaceChild("cloak3",
            CubeListBuilder.create().texOffs(36, 43)
                .addBox(-7.5F, 0F, -5F, 15, 5, 5, new CubeDeformation(inf)),
            PartPose.offset(0F, 5F, 0F));

        // cloak4 absolute: (0, 7, 4)   → relative to cloak3 at (0,2,4): (0, 5, 0)
        cloak3.addOrReplaceChild("cloak4",
            CubeListBuilder.create().texOffs(36, 53)
                .addBox(-8F, 0F, -6F, 16, 6, 6, new CubeDeformation(inf)),
            PartPose.offset(0F, 5F, 0F));

        // ── waist chain ─────────────────────────────────────────────────────
        // waist absolute: (0, -8, 0)   → relative to anchor: (0, -20, 0)
        anchor.addOrReplaceChild("waist",
            CubeListBuilder.create().texOffs(80, 7)
                .addBox(-3F, 5F, -2.5F, 6, 2, 5, new CubeDeformation(inf)),
            PartPose.offset(0F, -20F, 0F));

        // waist1 absolute: (0, -8, 0)  → relative to anchor: (0, -20, 0)
        anchor.addOrReplaceChild("waist1",
            CubeListBuilder.create().texOffs(80, 14)
                .addBox(-3.5F, 7.5F, -3F, 7, 4, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -20F, 0F, 0.0872665F, 0F, 0F));

        // waist2 absolute: (0, -1, -2.5) → relative to anchor: (0, -13, -2.5)
        PartDefinition waist2 = anchor.addOrReplaceChild("waist2",
            CubeListBuilder.create().texOffs(80, 22)
                .addBox(-3.5F, 0F, -0.5F, 7, 4, 6, new CubeDeformation(inf)),
            PartPose.offset(0F, -13F, -2.5F));

        // waist3 absolute: (0, 3, -3)  → relative to waist2 at (0,-1,-2.5): (0, 4, -0.5)
        PartDefinition waist3 = waist2.addOrReplaceChild("waist3",
            CubeListBuilder.create().texOffs(80, 32)
                .addBox(-4F, 0F, -0.5666667F, 8, 4, 7, new CubeDeformation(inf)),
            PartPose.offset(0F, 4F, -0.5F));

        // waist4 absolute: (0, 7, -3.5) → relative to waist3 at (0,3,-3): (0, 4, -0.5)
        waist3.addOrReplaceChild("waist4",
            CubeListBuilder.create().texOffs(80, 43)
                .addBox(-4.5F, 0F, -1F, 9, 6, 8, new CubeDeformation(inf)),
            PartPose.offset(0F, 4F, -0.5F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    // ── HeadedModel ───────────────────────────────────────────────────────────
    @Override
    public ModelPart getHead() {
        return head;
    }

    // ── ArmedModel ────────────────────────────────────────────────────────────
    @Override
    public void translateToHand(HumanoidArm side, PoseStack poseStack) {
        // Translate the pose stack so held items appear at the correct hand position.
        // The arm offset relative to the entity root: X = ±2.5, Y = -18.5 (anchor) - 1 (arm box top), Z = 0.
        ModelPart arm = side == HumanoidArm.RIGHT ? rightarm : leftarm;
        arm.translateAndRotate(poseStack);
    }

    // ── Rendering ─────────────────────────────────────────────────────────────
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer,
                               int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        anchor.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    // ── Animation ─────────────────────────────────────────────────────────────
    @Override
    public void setupAnim(VampireEntity entity,
                          float limbSwing, float limbSwingAmount,
                          float ageInTicks,
                          float netHeadYaw, float headPitch) {

        // LivingEntityRenderer passes ageInTicks = entity.tickCount + partialTick,
        // so subtracting tickCount recovers the partial tick for smooth interpolation.
        this.attackTime = entity.getAttackAnim(ageInTicks - entity.tickCount);

        // anchor bob
        anchor.y = 12F - 2.0F + Mth.cos((1.5F + ageInTicks) * 0.5F) * 0.5F;

        // head
        head.yRot           = netHeadYaw  / 57.295776F;
        head.xRot           = headPitch   / 57.295776F;
        headeyes.yRot       = head.yRot;
        headeyes.xRot       = head.xRot;
        headaccessory.yRot  = head.yRot;
        headaccessory.xRot  = head.xRot;

        headeyes.visible = (entity.tickCount % 60 == 0) && limbSwingAmount <= 0.1F;

        // arms – reset before applying swing
        rightarm.zRot = 0.0F;
        leftarm.zRot  = 0.0F;
        rightarm.xRot = 0.0F;
        leftarm.xRot  = 0.0F;

        if (attackTime > -9990.0F) {
            holdingMelee();
        }

        rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + 0.1745329F;
        leftarm.zRot  -= (Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + 0.1745329F;
        rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        leftarm.xRot  -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

        // cloak chain
        ModelPart cloak2 = cloak1.getChild("cloak2");
        ModelPart cloak3 = cloak2.getChild("cloak3");
        ModelPart cloak4 = cloak3.getChild("cloak4");

        float cloakSway = Mth.cos(degToRad((float) entity.tickCount * 7));
        cloak1.xRot = degToRad(5);  cloak1.zRot = cloakSway * degToRad(1);
        cloak2.xRot = degToRad(5);  cloak2.zRot = cloakSway * degToRad(2);
        cloak3.xRot = degToRad(5);  cloak3.zRot = cloakSway * degToRad(3);
        cloak4.xRot = degToRad(5);  cloak4.zRot = cloakSway * degToRad(4);

        // waist chain
        ModelPart waist3 = waist2.getChild("waist3");
        ModelPart waist4 = waist3.getChild("waist4");

        float waistSway = Mth.cos(degToRad((float) entity.tickCount * 7));
        waist1.zRot = waistSway * degToRad(1);
        waist2.xRot = degToRad(5);  waist2.zRot = waist1.zRot;
        waist3.xRot = degToRad(5);  waist3.zRot = waistSway * degToRad(2);
        waist4.xRot = degToRad(5);  waist4.zRot = waist3.zRot;
    }

    // ── Melee swing ───────────────────────────────────────────────────────────
    private void holdingMelee() {
        float f6 = 1.0F - attackTime;
        f6 *= f6;
        f6 *= f6;
        f6 = 1.0F - f6;
        float f7 = Mth.sin(f6 * (float) Math.PI);
        float f8 = Mth.sin(attackTime * (float) Math.PI) * -(head.xRot - 0.7F) * 0.75F;

        rightarm.xRot -= (f7 * 1.2F + f8);
        rightarm.xRot += (bodytop.yRot * 2.0F);
        rightarm.zRot  = Mth.sin(attackTime * (float) Math.PI) * -0.4F;
    }

    // ── Helper ────────────────────────────────────────────────────────────────
    private static float degToRad(float deg) {
        return deg * (float) Math.PI / 180.0F;
    }

    /** Copies animation state from {@code source} into this model (used by aura layer).
     * {@code riding} and {@code young} are inherited from {@link net.minecraft.client.model.EntityModel}. */
    public void copyPropertiesFrom(VampireModel source) {
        this.attackTime = source.attackTime;
        this.riding     = source.riding;
        this.young      = source.young;
    }
}