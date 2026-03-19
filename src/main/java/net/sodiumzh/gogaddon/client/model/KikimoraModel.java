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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sodiumzh.gogaddon.entity.KikimoraEntity;

@OnlyIn(Dist.CLIENT)
public class KikimoraModel extends EntityModel<KikimoraEntity> implements HeadedModel, ArmedModel {

    // ── Animation state ───────────────────────────────────────────────────────
    public float attackTime;

    // ── Part references ───────────────────────────────────────────────────────
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart headeyes;
    private final ModelPart headaccessory;
    private final ModelPart bodytop;
    private final ModelPart rightear;
    private final ModelPart leftear;
    private final ModelPart rightarm;
    private final ModelPart leftarm;
    private final ModelPart rightarmlower;
    private final ModelPart leftarmlower;
    private final ModelPart rightleg;
    private final ModelPart leftleg;
    private final ModelPart tail02;
    private final ModelPart tail03;
    private final ModelPart tail04;
    private final ModelPart tail05;

    // ── Constructor ───────────────────────────────────────────────────────────
    public KikimoraModel(ModelPart root) {
        this.root          = root.getChild("kikimora");
        this.head          = this.root.getChild("head");
        this.headeyes      = this.root.getChild("headeyes");
        this.headaccessory = this.root.getChild("headaccessory");
        this.bodytop       = this.root.getChild("bodytop");
        this.rightear      = this.head.getChild("rightear");
        this.leftear       = this.head.getChild("leftear");
        this.rightarm      = this.root.getChild("rightarm");
        this.leftarm       = this.root.getChild("leftarm");
        this.rightarmlower = this.rightarm.getChild("rightarmlower");
        this.leftarmlower  = this.leftarm.getChild("leftarmlower");
        this.rightleg      = this.root.getChild("rightleg");
        this.leftleg       = this.root.getChild("leftleg");
        ModelPart tail01   = this.root.getChild("tail01");
        this.tail02        = tail01.getChild("tail02");
        this.tail03        = this.tail02.getChild("tail03");
        this.tail04        = this.tail03.getChild("tail04");
        this.tail05        = this.tail04.getChild("tail05");
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
     * <p><b>Coordinate conversion from 1.12.2:</b> the root "kikimora" part sits at
     * Y=24 (entity feet). A 1.12.2 absolute rotationPoint {@code (x, y, z)} becomes
     * {@code (x, y-24, z)} relative to this root. Parts converted via
     * {@code convertToChild(parent, child)} store their offset as
     * {@code child_abs - parent_abs}.
     */
    private static LayerDefinition createBodyLayer(float inf) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition meshRoot = mesh.getRoot();

        // Root pivot at entity feet (Y=24 in model space)
        PartDefinition root = meshRoot.addOrReplaceChild("kikimora",
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

        // head children:
        // hat abs (0,-2,0) – head abs (0,1,0) → rel (0,-3,0)
        head.addOrReplaceChild("hat",
            CubeListBuilder.create().texOffs(36, 14)
                .addBox(-4F, -5F, -4F, 8, 4, 8, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -3F, 0F, -0.7853982F, 0F, 0F));

        // rightear abs (-3.5,-4,0) – head abs (0,1,0) → rel (-3.5,-5,0)
        head.addOrReplaceChild("rightear",
            CubeListBuilder.create().texOffs(36, 26)
                .addBox(0F, 0F, -1.5F, 3, 4, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(-3.5F, -5F, 0F, 0F, 0F, 0.5235988F));

        // leftear abs (3.5,-4,0) – head abs (0,1,0) → rel (3.5,-5,0)
        head.addOrReplaceChild("leftear",
            CubeListBuilder.create().texOffs(36, 26).mirror()
                .addBox(-3F, 0F, -1.5F, 3, 4, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(3.5F, -5F, 0F, 0F, 0F, -0.5235988F));

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
            PartPose.offsetAndRotation(1.3F, -21F, -1.5F, 0.7853982F, -0.1745329F, -0.0872665F));

        // ── arms ─────────────────────────────────────────────────────────────
        // abs (-2.5, 2.5, 0) → rel: (-2.5, 2.5-24, 0) = (-2.5, -21.5, 0)
        PartDefinition rightarm = root.addOrReplaceChild("rightarm",
            CubeListBuilder.create().texOffs(16, 12)
                .addBox(-2F, -1F, -1F, 2, 6, 2, new CubeDeformation(inf)),
            PartPose.offset(-2.5F, -21.5F, 0F));

        // rightpauldron abs (-2.5, 2.5, 0) – rightarm abs (-2.5, 2.5, 0) → rel (0, 0, 0)
        PartDefinition rightpauldron = rightarm.addOrReplaceChild("rightpauldron",
            CubeListBuilder.create().texOffs(36, 41)
                .addBox(-2.5F, -1F, -1.5F, 3, 3, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 0F));

        // rightpauldronoverlay abs (-2.5, 2.5, 0) – rightpauldron abs (-2.5, 2.5, 0) → rel (0, 0, 0)
        rightpauldron.addOrReplaceChild("rightpauldronoverlay",
            CubeListBuilder.create().texOffs(36, 33)
                .addBox(-2.5F, -1.5F, -2F, 4, 4, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.2617994F));

        // rightarmlower abs (-3.5, 7.5, 1) – rightarm abs (-2.5, 2.5, 0) → rel (-1, 5, 1)
        PartDefinition rightarmlower = rightarm.addOrReplaceChild("rightarmlower",
            CubeListBuilder.create().texOffs(16, 20)
                .addBox(-1F, 0F, -2F, 2, 6, 2, new CubeDeformation(inf)),
            PartPose.offset(-1F, 5F, 1F));

        // rightcufflink abs (-3.5, 13.5, 0) – rightarmlower abs (-3.5, 7.5, 1) → rel (0, 6, -1)
        rightarmlower.addOrReplaceChild("rightcufflink",
            CubeListBuilder.create().texOffs(36, 47)
                .addBox(-1F, -5F, -1F, 2, 4, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 6F, -1F, -0.1745329F, 0F, -0.1745329F));

        PartDefinition leftarm = root.addOrReplaceChild("leftarm",
            CubeListBuilder.create().texOffs(16, 12).mirror()
                .addBox(0F, -1F, -1F, 2, 6, 2, new CubeDeformation(inf)),
            PartPose.offset(2.5F, -21.5F, 0F));

        // leftpauldron abs (2.5, 2.5, 0) – leftarm abs (2.5, 2.5, 0) → rel (0, 0, 0)
        PartDefinition leftpauldron = leftarm.addOrReplaceChild("leftpauldron",
            CubeListBuilder.create().texOffs(36, 41).mirror()
                .addBox(-0.5F, -1F, -1.5F, 3, 3, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 0F));

        // leftpauldronoverlay abs (2.5, 2.5, 0) – leftpauldron abs (2.5, 2.5, 0) → rel (0, 0, 0)
        leftpauldron.addOrReplaceChild("leftpauldronoverlay",
            CubeListBuilder.create().texOffs(36, 33).mirror()
                .addBox(-1.5F, -1.5F, -2F, 4, 4, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.2617994F));

        // leftarmlower abs (3.5, 7.5, 1) – leftarm abs (2.5, 2.5, 0) → rel (1, 5, 1)
        PartDefinition leftarmlower = leftarm.addOrReplaceChild("leftarmlower",
            CubeListBuilder.create().texOffs(16, 20).mirror()
                .addBox(-1F, 0F, -2F, 2, 6, 2, new CubeDeformation(inf)),
            PartPose.offset(1F, 5F, 1F));

        // leftcufflink abs (3.5, 13.5, 0) – leftarmlower abs (3.5, 7.5, 1) → rel (0, 6, -1)
        leftarmlower.addOrReplaceChild("leftcufflink",
            CubeListBuilder.create().texOffs(36, 47).mirror()
                .addBox(-1F, -5F, -1F, 2, 4, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 6F, -1F, -0.1745329F, 0F, 0.1745329F));

        // ── legs ─────────────────────────────────────────────────────────────
        // abs (-2, 11, 0) → rel: (-2, 11-24, 0) = (-2, -13, 0)
        PartDefinition rightleg = root.addOrReplaceChild("rightleg",
            CubeListBuilder.create().texOffs(24, 12)
                .addBox(-1.5F, -1F, -1.5F, 3, 14, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(-2F, -13F, 0F, 0F, -0.0872665F, 0F));

        // rightskirt02 abs (0, 11, 0) – rightleg abs (-2, 11, 0) → rel (2, 0, 0)
        PartDefinition rightskirt02 = rightleg.addOrReplaceChild("rightskirt02",
            CubeListBuilder.create().texOffs(84, 17)
                .addBox(-4.5F, 1.5F, -4F, 5, 4, 8, new CubeDeformation(inf)),
            PartPose.offset(2F, 0F, 0F));

        // rightskirt03 abs (0, 11, 0) – rightskirt02 abs (0, 11, 0) → rel (0, 0, 0)
        rightskirt02.addOrReplaceChild("rightskirt03",
            CubeListBuilder.create().texOffs(84, 29)
                .addBox(-5F, 5.5F, -4.5F, 6, 5, 9, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 0F));

        PartDefinition leftleg = root.addOrReplaceChild("leftleg",
            CubeListBuilder.create().texOffs(24, 12).mirror()
                .addBox(-1.5F, -1F, -1.5F, 3, 14, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(2F, -13F, 0F, 0F, 0.0872665F, 0F));

        // leftskirt02 abs (0, 11, 0) – leftleg abs (2, 11, 0) → rel (-2, 0, 0)
        PartDefinition leftskirt02 = leftleg.addOrReplaceChild("leftskirt02",
            CubeListBuilder.create().texOffs(84, 17).mirror()
                .addBox(-0.5F, 1.5F, -4F, 5, 4, 8, new CubeDeformation(inf)),
            PartPose.offset(-2F, 0F, 0F));

        // leftskirt03 abs (0, 11, 0) – leftskirt02 abs (0, 11, 0) → rel (0, 0, 0)
        leftskirt02.addOrReplaceChild("leftskirt03",
            CubeListBuilder.create().texOffs(84, 29).mirror()
                .addBox(-1F, 5.5F, -4.5F, 6, 5, 9, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 0F));

        // ── skirt ribbon and independent skirt panels (direct root children) ─
        // skirtribbon abs (0, 1, 0) → rel (0, -23, 0)
        root.addOrReplaceChild("skirtribbon",
            CubeListBuilder.create().texOffs(84, 0)
                .addBox(-3.5F, 1F, 7.5F, 7, 2, 5, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, -1.047198F, 0F, 0F));

        // rightskirt01 abs (0, 11, 0) → rel (0, 11-24, 0) = (0, -13, 0)
        root.addOrReplaceChild("rightskirt01",
            CubeListBuilder.create().texOffs(84, 7)
                .addBox(-4F, -1.5F, -3.5F, 4, 3, 7, new CubeDeformation(inf)),
            PartPose.offset(0F, -13F, 0F));

        root.addOrReplaceChild("leftskirt01",
            CubeListBuilder.create().texOffs(84, 7).mirror()
                .addBox(0F, -1.5F, -3.5F, 4, 3, 7, new CubeDeformation(inf)),
            PartPose.offset(0F, -13F, 0F));

        // ── tail chain ───────────────────────────────────────────────────────
        // tail01 abs (0, 9, 1) → rel (0, 9-24, 1) = (0, -15, 1)
        PartDefinition tail01 = root.addOrReplaceChild("tail01",
            CubeListBuilder.create().texOffs(68, 0)
                .addBox(-1.5F, -1.5F, 0F, 3, 3, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, -15F, 1F));

        // tail02 abs (0,9,4) – tail01 abs (0,9,1) → rel (0,0,3)
        PartDefinition tail02 = tail01.addOrReplaceChild("tail02",
            CubeListBuilder.create().texOffs(68, 6)
                .addBox(-2F, -2F, 0F, 4, 4, 4, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 3F));

        // tail03 abs (0,9,8) – tail02 abs (0,9,4) → rel (0,0,4)
        PartDefinition tail03 = tail02.addOrReplaceChild("tail03",
            CubeListBuilder.create().texOffs(68, 14)
                .addBox(-2F, -2F, 0F, 4, 4, 4, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 4F));

        // tail04 abs (0,9,12) – tail03 abs (0,9,8) → rel (0,0,4)
        PartDefinition tail04 = tail03.addOrReplaceChild("tail04",
            CubeListBuilder.create().texOffs(68, 22)
                .addBox(-1.5F, -1.5F, 0F, 3, 3, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 4F));

        // tail05 abs (0,9,15) – tail04 abs (0,9,12) → rel (0,0,3)
        tail04.addOrReplaceChild("tail05",
            CubeListBuilder.create().texOffs(68, 28)
                .addBox(-1.5F, -1.5F, 0F, 3, 3, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, 0F, 3F));

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
    public void setupAnim(KikimoraEntity entity,
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

        headeyes.visible = (entity.tickCount % 60 == 0) && limbSwingAmount <= 0.1F;

        // ears
        float earDefaultAngleZ = 0.5235988F;
        float earSway = Mth.cos(degToRad((float) entity.tickCount * 7));
        rightear.zRot  = earSway * degToRad(4);
        rightear.zRot += earDefaultAngleZ;
        leftear.zRot   = earSway * -degToRad(4);
        leftear.zRot  += -earDefaultAngleZ;

        // arms
        rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
        leftarm.xRot  = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;
        rightarm.zRot = 0.0F;
        leftarm.zRot  = 0.0F;

        if (attackTime > -9990.0F) {
            holdingMelee();
        }

        float armDefaultAngleY = 0.349066F;
        float armDefaultAngleZ = 0.174533F;

        rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        rightarm.yRot  = +armDefaultAngleY;
        rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + armDefaultAngleZ;
        leftarm.xRot  -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
        leftarm.yRot   = -armDefaultAngleY;
        leftarm.zRot  -= (Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + armDefaultAngleZ;

        rightarmlower.xRot = -armDefaultAngleY;
        leftarmlower.xRot  = -armDefaultAngleY;

        // tail
        tail02.xRot = -0.3926991F;
        tail03.xRot = -0.785398F;
        tail04.xRot = +0.3926991F;
        tail05.xRot = +0.785398F;

        float tailSway = Mth.cos(degToRad((float) entity.tickCount * 7));
        tail02.yRot = tailSway * degToRad(1);
        tail03.yRot = tailSway * degToRad(5);
        tail04.yRot = tailSway * degToRad(10);
        tail05.yRot = tailSway * degToRad(15);

        // legs
        rightleg.xRot = (Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount) * 0.5F;
        leftleg.xRot  = (Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount) * 0.5F;
        rightleg.yRot = -0.0872665F;
        leftleg.yRot  = 0.0872665F;
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

    // ── Helper ────────────────────────────────────────────────────────────────
    private static float degToRad(float deg) {
        return deg * (float) Math.PI / 180.0F;
    }

    /** Copies animation state from {@code source} into this model (used by aura layer). */
    public void copyPropertiesFrom(KikimoraModel source) {
        this.attackTime = source.attackTime;
        this.riding     = source.riding;
        this.young      = source.young;
    }
}
