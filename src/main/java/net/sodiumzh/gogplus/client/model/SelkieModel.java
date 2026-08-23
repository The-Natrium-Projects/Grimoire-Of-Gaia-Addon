package net.sodiumzh.gogplus.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sodiumzh.gogplus.entity.mob.SelkieEntity;

@OnlyIn(Dist.CLIENT)
public class SelkieModel extends EntityModel<SelkieEntity> implements HeadedModel, ArmedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headeyes;
	private final ModelPart headaccessory;
	private final ModelPart bodytop;
	private final ModelPart rightarm;
	private final ModelPart leftarm;
	private final ModelPart hair1;
	private final ModelPart hair2;
	private final ModelPart righthatear;
	private final ModelPart lefthatear;
	private final ModelPart chestpiece;
	private final ModelPart waist;
	private final ModelPart fin1;
	private final ModelPart fin2;
	private final ModelPart fin3;
	private final ModelPart fin4;
	private final ModelPart fintail;

	private static final double CYCLES_PER_BLOCK = 0.1D;
	private final float[][] undulationCycle = new float[][] {
			{-5F, -10F, -15F, -20F, -25F, -30F},
			{-5F, -7F, -9F, -11F, -13F, -15F},
			{0F, 0F, 0F, 0F, 0F, 0F},
			{5F, 10F, 15F, 20F, 25F, 30F},
			{5F, 7F, 9F, 11F, 13F, 15F},
			{0F, 0F, 0F, 0F, 0F, 0F},
	};

	public SelkieModel(ModelPart root) {
		this.root = root.getChild("selkie");
		this.head = this.root.getChild("head");
		this.headeyes = this.root.getChild("headeyes");
		this.headaccessory = this.root.getChild("headaccessory");
		this.bodytop = this.root.getChild("bodytop");
		this.rightarm = this.root.getChild("rightarm");
		this.leftarm = this.root.getChild("leftarm");
		this.hair1 = this.root.getChild("hair1");
		this.hair2 = this.root.getChild("hair2");
		this.righthatear = this.root.getChild("righthatear");
		this.lefthatear = this.root.getChild("lefthatear");
		this.chestpiece = this.root.getChild("chestpiece");
		this.waist = this.root.getChild("waist");
		this.fin1 = this.waist.getChild("fin1");
		this.fin2 = this.waist.getChild("fin2");
		this.fin3 = this.waist.getChild("fin3");
		this.fin4 = this.waist.getChild("fin4");
		this.fintail = this.waist.getChild("fintail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition selkie = partdefinition.addOrReplaceChild("selkie", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		// Direct children of root (Y_offset = original_Y - 24)
		PartDefinition head = selkie.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		selkie.addOrReplaceChild("headeyes", CubeListBuilder.create()
				.texOffs(24, 0).addBox(-3.0F, -6.0F, -3.1F, 6.0F, 6.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		selkie.addOrReplaceChild("headaccessory", CubeListBuilder.create()
				.texOffs(36, 0).addBox(-3.5F, -6.5F, -3.5F, 7.0F, 7.0F, 7.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		selkie.addOrReplaceChild("neck", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		selkie.addOrReplaceChild("bodytop", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-2.5F, 0.0F, -1.5F, 5.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.0872665F, 0.0F, 0.0F));

		selkie.addOrReplaceChild("bodymiddle", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-2.0F, 5.5F, -1.5F, 4.0F, 3.0F, 2.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		selkie.addOrReplaceChild("bodymiddlebutton", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-0.5F, 6.0F, -1.6F, 1.0F, 2.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		selkie.addOrReplaceChild("bodybottom", CubeListBuilder.create()
				.texOffs(0, 30).addBox(-3.0F, 8.0F, -2.5F, 6.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0872665F, 0.0F, 0.0F));

		selkie.addOrReplaceChild("rightchest", CubeListBuilder.create()
				.texOffs(0, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-1.3F, -21.0F, -1.5F, 0.7853982F, 0.1745329F, 0.0872665F));

		selkie.addOrReplaceChild("leftchest", CubeListBuilder.create()
				.texOffs(0, 36).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(1.3F, -21.0F, -1.5F, 0.7853982F, -0.1745329F, -0.0872665F));

		selkie.addOrReplaceChild("rightarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 0.0F, 0.0F, 0.0F, 0.1745329F));

		selkie.addOrReplaceChild("leftarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offsetAndRotation(2.5F, -21.5F, 0.0F, 0.0F, 0.0F, -0.1745329F));

		selkie.addOrReplaceChild("hair1", CubeListBuilder.create()
				.texOffs(36, 14).addBox(-4.0F, -6.0F, 1.0F, 8.0F, 8.0F, 3.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		selkie.addOrReplaceChild("hair2", CubeListBuilder.create()
				.texOffs(36, 25).addBox(-4.5F, -1.0F, 1.5F, 9.0F, 9.0F, 3.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		selkie.addOrReplaceChild("righthatear", CubeListBuilder.create()
				.texOffs(64, 10).addBox(-5.0F, -6.0F, -4.0F, 0.0F, 18.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0F, 0.0F, 0.1745329F));

		selkie.addOrReplaceChild("lefthatear", CubeListBuilder.create()
				.texOffs(64, 10).addBox(5.0F, -6.0F, -4.0F, 0.0F, 18.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.1745329F));

		selkie.addOrReplaceChild("chestpiece", CubeListBuilder.create()
				.texOffs(64, 36).addBox(-4.0F, -2.0F, -1.0F, 8.0F, 6.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.7853982F, 0.0F, 0.0F));

		// Head children: hat1, hat2 (offset = (0,0,0) relative to head)
		head.addOrReplaceChild("hat1", CubeListBuilder.create()
				.texOffs(64, 0).addBox(-4.0F, -7.5F, -5.0F, 8.0F, 3.0F, 8.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745329F, 0.0F, 0.0F));

		head.addOrReplaceChild("hat2", CubeListBuilder.create()
				.texOffs(64, 11).addBox(-3.0F, -8.5F, -4.0F, 6.0F, 1.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745329F, 0.0F, 0.0F));

		// Waist with initial X rotation only; Y rotation set in setupAnim
		PartDefinition waist = selkie.addOrReplaceChild("waist", CubeListBuilder.create()
				.texOffs(96, 0).addBox(-4.0F, 7.5F, -3.0F, 8.0F, 3.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0872665F, 0.0F, 0.0F));

		// Waist children (offset = (0, 10, 0) relative to waist)
		waist.addOrReplaceChild("zip", CubeListBuilder.create()
				.texOffs(96, 7).addBox(-1.0F, 0.0F, -3.5F, 2.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, -0.6108652F, 0.0F, 0.0F));

		waist.addOrReplaceChild("fin1", CubeListBuilder.create()
				.texOffs(96, 11).addBox(-3.5F, -1.0F, -3.0F, 7.0F, 6.0F, 6.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, -0.2617994F, 0.0F, 0.0F));

		waist.addOrReplaceChild("fin2", CubeListBuilder.create()
				.texOffs(96, 23).addBox(-3.0F, 4.0F, -3.5F, 6.0F, 5.0F, 5.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, -0.0872665F, 0.0F, 0.0F));

		waist.addOrReplaceChild("fin3", CubeListBuilder.create()
				.texOffs(96, 33).addBox(-2.5F, 7.0F, -6.0F, 5.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.2617994F, 0.0F, 0.0F));

		waist.addOrReplaceChild("fin4", CubeListBuilder.create()
				.texOffs(96, 41).addBox(-2.0F, 8.0F, -9.0F, 4.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.6108652F, 0.0F, 0.0F));

		waist.addOrReplaceChild("fintail", CubeListBuilder.create()
				.texOffs(96, 47).addBox(-4.0F, 12.0F, 1.0F, 8.0F, 1.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, -0.0872665F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(SelkieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// head
		head.yRot = netHeadYaw / 57.295776F;
		head.xRot = headPitch / 57.295776F;
		headeyes.yRot = head.yRot;
		headeyes.xRot = head.xRot;
		headaccessory.yRot = head.yRot;
		headaccessory.xRot = head.xRot;
		righthatear.yRot = head.yRot;
		lefthatear.yRot = head.yRot;
		hair1.yRot = head.yRot;
		hair2.yRot = head.yRot * 0.75F;

		headeyes.visible = entity.tickCount % 60 == 0 && limbSwingAmount <= 0.1F;

		// arms
		rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
		leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;

		rightarm.zRot = 0.0F;
		leftarm.zRot = 0.0F;

		// TODO: Bow animation skipped - entity may not support isSwingingArms yet
		if (attackTime > 0.0F) {
			holdingMelee();
		}

		rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
		rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.025F;
		leftarm.zRot -= (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
		leftarm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.025F;

		// undulation (tail fin animation)
		int cycleIndex = Math.abs((int) ((limbSwing * CYCLES_PER_BLOCK) % undulationCycle.length));

		waist.yRot = undulationCycle[cycleIndex][0] * Mth.DEG_TO_RAD;
		fin1.yRot = undulationCycle[cycleIndex][1] * Mth.DEG_TO_RAD;
		fin2.yRot = undulationCycle[cycleIndex][2] * Mth.DEG_TO_RAD;
		fin3.yRot = undulationCycle[cycleIndex][3] * Mth.DEG_TO_RAD;
		fin4.yRot = undulationCycle[cycleIndex][4] * Mth.DEG_TO_RAD;
		fintail.yRot = undulationCycle[cycleIndex][5] * Mth.DEG_TO_RAD;
	}

	public void holdingMelee() {
		float f6;
		float f7;

		f6 = 1.0F - attackTime;
		f6 *= f6;
		f6 *= f6;
		f6 = 1.0F - f6;
		f7 = Mth.sin(f6 * (float) Math.PI);
		float f8 = Mth.sin(attackTime * (float) Math.PI) * -(head.xRot - 0.7F) * 0.75F;

		rightarm.xRot = (float) ((double) rightarm.xRot - ((double) f7 * 1.2D + (double) f8));
		rightarm.xRot += (bodytop.yRot * 2.0F);
		rightarm.zRot = (Mth.sin(attackTime * (float) Math.PI) * -0.4F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getHead() {
		return head;
	}

	private ModelPart getArm(HumanoidArm arm) {
		return arm == HumanoidArm.LEFT ? this.leftarm : this.rightarm;
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
		poseStack.translate(0, 1.5, 0.0);
		getArm(arm).translateAndRotate(poseStack);
	}
}
