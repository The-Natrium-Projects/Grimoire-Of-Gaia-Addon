package net.sodiumzh.gogaddon.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sodiumzh.gogaddon.entity.DhampirEntity;

@OnlyIn(Dist.CLIENT)
public class DhampirModel extends EntityModel<DhampirEntity> implements HeadedModel, ArmedModel {

    // ── Animation state ───────────────────────────────────────────────────────
    public float attackTime;

    // ── Part references ───────────────────────────────────────────────────────
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart headeyes;
    private final ModelPart headaccessory;
    private final ModelPart bodytop;
    private final ModelPart rightarm;
    private final ModelPart leftarm;
    private final ModelPart rightleg;
    private final ModelPart leftleg;
    private final ModelPart hair;
    private final ModelPart mantle;

    // ── Constructor ───────────────────────────────────────────────────────────
    public DhampirModel(ModelPart root) {
        this.root          = root.getChild("dhampir");
        this.head          = this.root.getChild("head");
        this.headeyes      = this.root.getChild("headeyes");
        this.headaccessory = this.root.getChild("headaccessory");
        this.bodytop       = this.root.getChild("bodytop");
        this.rightarm      = this.root.getChild("rightarm");
        this.leftarm       = this.root.getChild("leftarm");
        this.rightleg      = this.root.getChild("rightleg");
        this.leftleg       = this.root.getChild("leftleg");
        this.hair          = this.root.getChild("hair");
        this.mantle        = this.root.getChild("mantle");
    }

    // ── Layer definitions ─────────────────────────────────────────────────────

    public static LayerDefinition createBodyLayer() {
        return createBodyLayer(0.0F);
    }

    public static LayerDefinition createAuraLayer() {
        return createBodyLayer(0.5F);
    }

    /**
     * Shared geometry builder.
     *
     * <p><b>Coordinate conversion from 1.12.2:</b> the root "dhampir" part sits at
     * Y=24 (entity feet). A 1.12.2 absolute rotationPoint {@code (x, y, z)} becomes
     * {@code (x, y-24, z)} relative to this root. Parts that were converted via
     * {@code convertToChild(parent, child)} store their offset as
     * {@code child_abs - parent_abs}.
     */
    private static LayerDefinition createBodyLayer(float inf) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition meshRoot = mesh.getRoot();

        // Root pivot at entity feet (Y=24 in model space)
        PartDefinition root = meshRoot.addOrReplaceChild("dhampir",
            CubeListBuilder.create(),
            PartPose.offset(0F, 24F, 0F));

        // ── head cluster (1.12 abs Y=1 → rel Y = 1-24 = -23) ────────────────
        PartDefinition head = root.addOrReplaceChild("head",
            CubeListBuilder.create().texOffs(0, 0)
                .addBox(-3F, -6F, -3F, 6, 6, 6, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        root.addOrReplaceChild("headeyes",
            CubeListBuilder.create().texOffs(24, 0)
                .addBox(-3F, -6F, -3.1F, 6, 6, 0, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        root.addOrReplaceChild("headaccessory",
            CubeListBuilder.create().texOffs(36, 0)
                .addBox(-3.5F, -6.5F, -3.5F, 7, 7, 7, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        root.addOrReplaceChild("neck",
            CubeListBuilder.create().texOffs(0, 12)
                .addBox(-1F, -1F, -1F, 2, 2, 2, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        // head children: all had same abs (0,1,0) as head → relative (0,0,0)
        head.addOrReplaceChild("righthair",
            CubeListBuilder.create().texOffs(36, 24)
                .addBox(-4F, -6F, -1F, 0, 4, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.5235988F, 0F));

        head.addOrReplaceChild("lefthair",
            CubeListBuilder.create().texOffs(36, 24)
                .addBox(4F, -6F, -1F, 0, 4, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0.5235988F, 0F));

        head.addOrReplaceChild("hat1",
            CubeListBuilder.create().texOffs(36, 20)
                .addBox(-6F, -7F, -6F, 12, 2, 12, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0.0349066F, 0.9599311F, 0.0349066F));

        head.addOrReplaceChild("hat2",
            CubeListBuilder.create().texOffs(36, 34)
                .addBox(-3F, -10F, -3F, 6, 3, 6, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0.0349066F, 0.7853982F, 0.0349066F));

        head.addOrReplaceChild("hat3",
            CubeListBuilder.create().texOffs(36, 43)
                .addBox(-4F, -11F, 0F, 4, 3, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0.0349066F, 0.9599311F, 0.0349066F));

        head.addOrReplaceChild("hatflower",
            CubeListBuilder.create().texOffs(36, 50)
                .addBox(-3.5F, -11F, -3.5F, 7, 4, 7, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0.0349066F, 0.7853982F, 0.0349066F));

        // ── body ────────────────────────────────────────────────────────────
        root.addOrReplaceChild("bodytop",
            CubeListBuilder.create().texOffs(0, 16)
                .addBox(-2.5F, 0F, -1.5F, 5, 6, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, -0.0872665F, 0F, 0F));

        root.addOrReplaceChild("bodymiddle",
            CubeListBuilder.create().texOffs(0, 25)
                .addBox(-2F, 5.5F, -1.5F, 4, 3, 2, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        root.addOrReplaceChild("bodymiddlebutton",
            CubeListBuilder.create().texOffs(0, 25)
                .addBox(-0.5F, 6F, -1.6F, 1, 2, 0, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        root.addOrReplaceChild("bodybottom",
            CubeListBuilder.create().texOffs(0, 30)
                .addBox(-3F, 8F, -2.5F, 6, 3, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, 0.0872665F, 0F, 0F));

        // ── chest details ────────────────────────────────────────────────────
        // abs (-1.3, 3, -1.5) → rel: (-1.3, 3-24, -1.5) = (-1.3, -21, -1.5)
        root.addOrReplaceChild("rightchest",
            CubeListBuilder.create().texOffs(0, 36)
                .addBox(-1F, -1F, -1F, 2, 2, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(-1.3F, -21F, -1.5F, 0.7853982F, 0.1745329F, 0.0872665F));

        root.addOrReplaceChild("leftchest",
            CubeListBuilder.create().texOffs(0, 36).mirror()
                .addBox(-1F, -1F, -1F, 2, 2, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(1.3F, -21F, -1.5F, 0.7853982F, -0.1570796F, -0.0872665F));

        // ── arms ─────────────────────────────────────────────────────────────
        // abs (-2.5, 2.5, 0) → rel: (-2.5, 2.5-24, 0) = (-2.5, -21.5, 0)
        PartDefinition rightarm = root.addOrReplaceChild("rightarm",
            CubeListBuilder.create().texOffs(16, 12)
                .addBox(-2F, -1F, -1F, 2, 12, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(-2.5F, -21.5F, 0F, 0F, 0F, 0.1745329F));

        // rightpauldron abs (-2.5, 2, 0) – rightarm abs (-2.5, 2.5, 0) → rel (0, -0.5, 0)
        rightarm.addOrReplaceChild("rightpauldron",
            CubeListBuilder.create().texOffs(84, 8)
                .addBox(-2.5F, -1F, -1.5F, 3, 3, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, -0.5F, 0F));

        PartDefinition leftarm = root.addOrReplaceChild("leftarm",
            CubeListBuilder.create().texOffs(16, 12)
                .addBox(0F, -1F, -1F, 2, 12, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(2.5F, -21.5F, 0F, 0F, 0F, -0.1745329F));

        // leftpauldron abs (2.5, 2.5, 0) – leftarm abs (2.5, 2.5, 0) → rel (0, 0, 0)
        leftarm.addOrReplaceChild("leftpauldron",
            CubeListBuilder.create().texOffs(96, 8)
                .addBox(-0.5F, -1.5F, -1.5F, 3, 3, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 0F));

        // ── legs ─────────────────────────────────────────────────────────────
        // abs (-2, 11, 0) → rel: (-2, 11-24, 0) = (-2, -13, 0)
        PartDefinition rightleg = root.addOrReplaceChild("rightleg",
            CubeListBuilder.create().texOffs(24, 12)
                .addBox(-1.5F, -1F, -1.5F, 3, 14, 3, new CubeDeformation(inf)),
            PartPose.offset(-2F, -13F, 0F));

        // rightboot abs (-2, 11, 0) – rightleg abs (-2, 11, 0) → rel (0, 0, 0)
        rightleg.addOrReplaceChild("rightboot",
            CubeListBuilder.create().texOffs(84, 42)
                .addBox(-2F, 4F, -2F, 4, 7, 4, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 0F));

        PartDefinition leftleg = root.addOrReplaceChild("leftleg",
            CubeListBuilder.create().texOffs(24, 12)
                .addBox(-1.5F, -1F, -1.5F, 3, 14, 3, new CubeDeformation(inf)),
            PartPose.offset(2F, -13F, 0F));

        // leftboot abs (2, 11, 0) – leftleg abs (2, 11, 0) → rel (0, 0, 0)
        leftleg.addOrReplaceChild("leftboot",
            CubeListBuilder.create().texOffs(84, 42)
                .addBox(-2F, 4F, -2F, 4, 7, 4, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 0F));

        // ── accessories ──────────────────────────────────────────────────────
        root.addOrReplaceChild("hair",
            CubeListBuilder.create().texOffs(36, 14)
                .addBox(-1F, -2.5F, 2.5F, 2, 12, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, 0.3490659F, 0F, 0F));

        root.addOrReplaceChild("mantle",
            CubeListBuilder.create().texOffs(84, 0)
                .addBox(-4F, -3F, -2F, 8, 4, 4, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        root.addOrReplaceChild("cape1",
            CubeListBuilder.create().texOffs(84, 14)
                .addBox(-5F, 1F, -1F, 10, 6, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, 0.3490659F, 0F, 0F));

        root.addOrReplaceChild("cape2",
            CubeListBuilder.create().texOffs(84, 23)
                .addBox(-5.5F, 6F, 0.5F, 11, 6, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, 0.1745329F, 0F, 0F));

        root.addOrReplaceChild("waist",
            CubeListBuilder.create().texOffs(84, 32)
                .addBox(-4F, 7.5F, -3F, 8, 6, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, 0.0872665F, 0F, 0F));

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
        ModelPart arm = side == HumanoidArm.RIGHT ? rightarm : leftarm;
        arm.translateAndRotate(poseStack);
    }

    // ── Rendering ─────────────────────────────────────────────────────────────
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer,
                               int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        root.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    // ── Animation ─────────────────────────────────────────────────────────────
    @Override
    public void setupAnim(DhampirEntity entity,
                          float limbSwing, float limbSwingAmount,
                          float ageInTicks,
                          float netHeadYaw, float headPitch) {

        this.attackTime = entity.getAttackAnim(ageInTicks - entity.tickCount);

        // head
        head.yRot          = netHeadYaw / 57.295776F;
        head.xRot          = headPitch  / 57.295776F;
        headeyes.yRot      = head.yRot;
        headeyes.xRot      = head.xRot;
        headaccessory.yRot = head.yRot;
        headaccessory.xRot = head.xRot;
        hair.yRot          = head.yRot;

        headeyes.visible = (entity.tickCount % 60 == 0) && limbSwingAmount <= 0.1F;

        // arms: limb swing only when empty-handed
        if (entity.getMainHandItem().isEmpty()) {
            rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
            leftarm.xRot  = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;
            rightarm.zRot = 0.0F;
            leftarm.zRot  = 0.0F;

            if (attackTime > -9990.0F) {
                holdingMelee();
            }

            rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
            rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.025F;
            leftarm.zRot  -= (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
            leftarm.xRot  -= Mth.sin(ageInTicks * 0.067F) * 0.025F;
        }

        // staff buff pose when holding stick
        if (entity.getMainHandItem().is(Items.STICK)) {
            animationBuff();
        }

        // body
        mantle.yRot = head.yRot;

        // legs
        rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
        leftleg.xRot  = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount;
        rightleg.yRot = 0.0F;
        leftleg.yRot  = 0.0F;
        rightleg.zRot = 0.0F;
        leftleg.zRot  = 0.0F;

        if (entity.isPassenger()) {
            rightarm.xRot += -((float) Math.PI / 5F);
            leftarm.xRot  += -((float) Math.PI / 5F);
            rightleg.xRot  = -1.4137167F;
            rightleg.yRot  = ((float) Math.PI / 10F);
            rightleg.zRot  = 0.07853982F;
            leftleg.xRot   = -1.4137167F;
            leftleg.yRot   = -((float) Math.PI / 10F);
            leftleg.zRot   = -0.07853982F;
        }
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

    // ── Staff buff animation ──────────────────────────────────────────────────
    private void animationBuff() {
        rightarm.xRot = 0.0F;
        leftarm.xRot  = 0.0F;
        rightarm.zRot = +0.785398F;
        leftarm.zRot  = -0.785398F;
    }

    /** Copies animation state from {@code source} into this model (used by aura layer). */
    public void copyPropertiesFrom(DhampirModel source) {
        this.attackTime = source.attackTime;
        this.riding     = source.riding;
        this.young      = source.young;
    }
}
