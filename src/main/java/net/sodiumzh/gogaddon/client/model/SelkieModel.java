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
import net.sodiumzh.gogaddon.entity.SelkieEntity;

@OnlyIn(Dist.CLIENT)
public class SelkieModel extends EntityModel<SelkieEntity> implements HeadedModel, ArmedModel {

    // ── Animation state ───────────────────────────────────────────────────────
    public float attackTime;

    // ── Undulation cycle (degrees) for fin animation ──────────────────────────
    private static final double CYCLES_PER_BLOCK = 0.1;
    private static final float[][] undulationCycle = {
        {-5F, -10F, -15F, -20F, -25F, -30F},
        {-5F,  -7F,  -9F, -11F, -13F, -15F},
        { 0F,   0F,   0F,   0F,   0F,   0F},
        { 5F,  10F,  15F,  20F,  25F,  30F},
        { 5F,   7F,   9F,  11F,  13F,  15F},
        { 0F,   0F,   0F,   0F,   0F,   0F},
    };

    // ── Part references ───────────────────────────────────────────────────────
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart headeyes;
    private final ModelPart headaccessory;
    private final ModelPart bodytop;
    private final ModelPart righthatear;
    private final ModelPart lefthatear;
    private final ModelPart hair1;
    private final ModelPart hair2;
    private final ModelPart rightarm;
    private final ModelPart leftarm;
    private final ModelPart waist;
    private final ModelPart fin1;
    private final ModelPart fin2;
    private final ModelPart fin3;
    private final ModelPart fin4;
    private final ModelPart fintail;

    // ── Constructor ───────────────────────────────────────────────────────────
    public SelkieModel(ModelPart root) {
        this.root          = root.getChild("selkie");
        this.head          = this.root.getChild("head");
        this.headeyes      = this.root.getChild("headeyes");
        this.headaccessory = this.root.getChild("headaccessory");
        this.bodytop       = this.root.getChild("bodytop");
        this.righthatear   = this.root.getChild("righthatear");
        this.lefthatear    = this.root.getChild("lefthatear");
        this.hair1         = this.root.getChild("hair1");
        this.hair2         = this.root.getChild("hair2");
        this.rightarm      = this.root.getChild("rightarm");
        this.leftarm       = this.root.getChild("leftarm");
        this.waist         = this.root.getChild("waist");
        this.fin1          = this.waist.getChild("fin1");
        this.fin2          = this.waist.getChild("fin2");
        this.fin3          = this.waist.getChild("fin3");
        this.fin4          = this.waist.getChild("fin4");
        this.fintail       = this.waist.getChild("fintail");
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
     * <p><b>Coordinate conversion from 1.12.2:</b> the root "selkie" part sits at
     * Y=24 (entity feet). A 1.12.2 absolute rotationPoint {@code (x, y, z)} becomes
     * {@code (x, y-24, z)} relative to this root. Parts converted via
     * {@code convertToChild(parent, child)} store their offset as
     * {@code child_abs - parent_abs}.
     */
    private static LayerDefinition createBodyLayer(float inf) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition meshRoot = mesh.getRoot();

        // Root pivot at entity feet (Y=24 in model space)
        PartDefinition root = meshRoot.addOrReplaceChild("selkie",
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

        // head children: hat1, hat2 both had same abs (0,1,0) as head → rel (0,0,0)
        head.addOrReplaceChild("hat1",
            CubeListBuilder.create().texOffs(64, 0)
                .addBox(-4F, -7.5F, -5F, 8, 3, 8, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, -0.1745329F, 0F, 0F));

        head.addOrReplaceChild("hat2",
            CubeListBuilder.create().texOffs(64, 11)
                .addBox(-3F, -8.5F, -4F, 6, 1, 6, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 0F, 0F, -0.1745329F, 0F, 0F));

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
        root.addOrReplaceChild("rightarm",
            CubeListBuilder.create().texOffs(16, 12)
                .addBox(-2F, -1F, -1F, 2, 12, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(-2.5F, -21.5F, 0F, 0F, 0F, 0.1745329F));

        root.addOrReplaceChild("leftarm",
            CubeListBuilder.create().texOffs(16, 12)
                .addBox(0F, -1F, -1F, 2, 12, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(2.5F, -21.5F, 0F, 0F, 0F, -0.1745329F));

        // ── hat ears and hair (abs Y=1 → rel Y=-23) ─────────────────────────
        root.addOrReplaceChild("righthatear",
            CubeListBuilder.create().texOffs(64, 10)
                .addBox(-5F, -6F, -4F, 0, 18, 8, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, 0F, 0F, 0.1745329F));

        root.addOrReplaceChild("lefthatear",
            CubeListBuilder.create().texOffs(64, 10)
                .addBox(5F, -6F, -4F, 0, 18, 8, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, 0F, 0F, -0.1745329F));

        root.addOrReplaceChild("hair1",
            CubeListBuilder.create().texOffs(36, 14)
                .addBox(-4F, -6F, 1F, 8, 8, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        root.addOrReplaceChild("hair2",
            CubeListBuilder.create().texOffs(36, 25)
                .addBox(-4.5F, -1F, 1.5F, 9, 9, 3, new CubeDeformation(inf)),
            PartPose.offset(0F, -23F, 0F));

        root.addOrReplaceChild("chestpiece",
            CubeListBuilder.create().texOffs(64, 36)
                .addBox(-4F, -2F, -1F, 8, 6, 2, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, -0.7853982F, 0F, 0F));

        // ── waist and fin chain ──────────────────────────────────────────────
        // waist abs (0, 1, 0) → rel (0, -23, 0)
        PartDefinition waist = root.addOrReplaceChild("waist",
            CubeListBuilder.create().texOffs(96, 0)
                .addBox(-4F, 7.5F, -3F, 8, 3, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, -23F, 0F, 0.0872665F, 0F, 0F));

        // All fin parts abs (0, 11, 0) – waist abs (0, 1, 0) → rel (0, 10, 0)
        // Y rotation is overridden by animation every frame; only X rotation is static.
        waist.addOrReplaceChild("zip",
            CubeListBuilder.create().texOffs(96, 7)
                .addBox(-1F, 0F, -3.5F, 2, 3, 1, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 10F, 0F, -0.6108652F, 0F, 0F));

        waist.addOrReplaceChild("fin1",
            CubeListBuilder.create().texOffs(96, 11)
                .addBox(-3.5F, -1F, -3F, 7, 6, 6, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 10F, 0F, -0.2617994F, 0F, 0F));

        waist.addOrReplaceChild("fin2",
            CubeListBuilder.create().texOffs(96, 23)
                .addBox(-3F, 4F, -3.5F, 6, 5, 5, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 10F, 0F, -0.0872665F, 0F, 0F));

        waist.addOrReplaceChild("fin3",
            CubeListBuilder.create().texOffs(96, 33)
                .addBox(-2.5F, 7F, -6F, 5, 4, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 10F, 0F, 0.2617994F, 0F, 0F));

        waist.addOrReplaceChild("fin4",
            CubeListBuilder.create().texOffs(96, 41)
                .addBox(-2F, 8F, -9F, 4, 3, 3, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 10F, 0F, 0.6108652F, 0F, 0F));

        waist.addOrReplaceChild("fintail",
            CubeListBuilder.create().texOffs(96, 47)
                .addBox(-4F, 12F, 1F, 8, 1, 4, new CubeDeformation(inf)),
            PartPose.offsetAndRotation(0F, 10F, 0F, -0.0872665F, 0F, 0F));

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
    public void setupAnim(SelkieEntity entity,
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
        righthatear.yRot   = head.yRot;
        lefthatear.yRot    = head.yRot;
        hair1.yRot         = head.yRot;
        hair2.yRot         = head.yRot * 0.75F;

        headeyes.visible = (entity.tickCount % 60 == 0) && limbSwingAmount <= 0.1F;

        // arms
        rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
        leftarm.xRot  = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;
        rightarm.zRot = 0.0F;
        leftarm.zRot  = 0.0F;

        // isAggressive() is the 1.20.1 equivalent of 1.12's isSwingingArms()
        if (entity.isAggressive() && entity.getMainHandItem().is(Items.BOW)) {
            holdingBow(ageInTicks);
        } else if (attackTime > -9990.0F) {
            holdingMelee();
        }

        rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
        rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.025F;
        leftarm.zRot  -= (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
        leftarm.xRot  -= Mth.sin(ageInTicks * 0.067F) * 0.025F;

        // fin undulation cycle driven by total walk distance
        int cycleIndex = (int) (entity.walkDist * CYCLES_PER_BLOCK % undulationCycle.length);
        waist.yRot  = degToRad(undulationCycle[cycleIndex][0]);
        fin1.yRot   = degToRad(undulationCycle[cycleIndex][1]);
        fin2.yRot   = degToRad(undulationCycle[cycleIndex][2]);
        fin3.yRot   = degToRad(undulationCycle[cycleIndex][3]);
        fin4.yRot   = degToRad(undulationCycle[cycleIndex][4]);
        fintail.yRot = degToRad(undulationCycle[cycleIndex][5]);
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

    // ── Bow aim animation ─────────────────────────────────────────────────────
    private void holdingBow(float ageInTicks) {
        float f  = Mth.sin(attackTime * (float) Math.PI);
        float f1 = Mth.sin((1.0F - (1.0F - attackTime) * (1.0F - attackTime)) * (float) Math.PI);

        rightarm.zRot = -0.3F;
        leftarm.zRot  =  0.3F;
        rightarm.yRot = -(0.1F - f * 0.6F);
        leftarm.yRot  =  0.3F - f * 0.6F;
        rightarm.xRot = -((float) Math.PI / 2F);
        leftarm.xRot  = -((float) Math.PI / 2F);
        rightarm.xRot -= f * 1.2F - f1 * 0.4F;
        leftarm.xRot  -= f * 1.2F - f1 * 0.4F;
        rightarm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        leftarm.zRot  -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
        leftarm.xRot  -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
    }

    // ── Helper ────────────────────────────────────────────────────────────────
    private static float degToRad(float deg) {
        return deg * (float) Math.PI / 180.0F;
    }

    /** Copies animation state from {@code source} into this model (used by aura layer). */
    public void copyPropertiesFrom(SelkieModel source) {
        this.attackTime = source.attackTime;
        this.riding     = source.riding;
        this.young      = source.young;
    }
}
