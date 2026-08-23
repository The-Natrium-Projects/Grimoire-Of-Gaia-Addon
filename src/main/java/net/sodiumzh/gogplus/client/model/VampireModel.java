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
import net.sodiumzh.gogplus.entity.mob.VampireEntity;

@OnlyIn(Dist.CLIENT)
public class VampireModel extends EntityModel<VampireEntity> implements HeadedModel, ArmedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headeyes;
	private final ModelPart headaccessory;
	private final ModelPart bodytop;
	private final ModelPart rightarm;
	private final ModelPart leftarm;
	private final ModelPart cloak1;
	private final ModelPart cloak2;
	private final ModelPart cloak3;
	private final ModelPart cloak4;
	private final ModelPart waist1;
	private final ModelPart waist2;
	private final ModelPart waist3;
	private final ModelPart waist4;

	public VampireModel(ModelPart root) {
		this.root = root.getChild("vampire");
		this.head = this.root.getChild("head");
		this.headeyes = this.root.getChild("headeyes");
		this.headaccessory = this.root.getChild("headaccessory");
		this.bodytop = this.root.getChild("bodytop");
		this.rightarm = this.root.getChild("rightarm");
		this.leftarm = this.root.getChild("leftarm");
		this.cloak1 = this.root.getChild("cloak1");
		this.cloak2 = this.cloak1.getChild("cloak2");
		this.cloak3 = this.cloak2.getChild("cloak3");
		this.cloak4 = this.cloak3.getChild("cloak4");
		this.waist1 = this.root.getChild("waist1");
		this.waist2 = this.root.getChild("waist2");
		this.waist3 = this.waist2.getChild("waist3");
		this.waist4 = this.waist3.getChild("waist4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition vampire = partdefinition.addOrReplaceChild("vampire", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = vampire.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition headeyes = vampire.addOrReplaceChild("headeyes", CubeListBuilder.create()
				.texOffs(24, 0).addBox(-3.0F, -6.0F, -3.1F, 6.0F, 6.0F, 0.0F), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition headaccessory = vampire.addOrReplaceChild("headaccessory", CubeListBuilder.create()
				.texOffs(36, 0).addBox(-3.5F, -6.5F, -3.5F, 7.0F, 7.0F, 7.0F), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition neck = vampire.addOrReplaceChild("neck", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bodytop = vampire.addOrReplaceChild("bodytop", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-2.5F, 0.0F, -1.5F, 5.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition bodymiddle = vampire.addOrReplaceChild("bodymiddle", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-2.0F, 5.5F, -1.5F, 4.0F, 3.0F, 2.0F), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bodymiddlebutton = vampire.addOrReplaceChild("bodymiddlebutton", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-0.5F, 6.0F, -1.6F, 1.0F, 2.0F, 0.0F), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bodybottom = vampire.addOrReplaceChild("bodybottom", CubeListBuilder.create()
				.texOffs(0, 30).addBox(-3.0F, 8.0F, -2.5F, 6.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightchest = vampire.addOrReplaceChild("rightchest", CubeListBuilder.create()
				.texOffs(0, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-1.3F, -6.0F, -1.5F, 0.7854F, 0.1745F, 0.0873F));

		PartDefinition leftchest = vampire.addOrReplaceChild("leftchest", CubeListBuilder.create()
				.texOffs(0, 36).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(1.3F, -6.0F, -1.5F, 0.7854F, -0.1745F, -0.0873F));

		PartDefinition rightarm = vampire.addOrReplaceChild("rightarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offsetAndRotation(-2.5F, -6.5F, 0.0F, 0.0873F, 0.0F, 0.1745F));

		PartDefinition rightshoulder = rightarm.addOrReplaceChild("rightshoulder", CubeListBuilder.create()
				.texOffs(80, 0).addBox(-2.5F, -1.0F, -1.5F, 3.0F, 4.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition leftarm = vampire.addOrReplaceChild("leftarm", CubeListBuilder.create()
				.texOffs(16, 12).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(2.5F, -6.5F, 0.0F, 0.0873F, 0.0F, -0.1745F));

		PartDefinition leftshoulder = leftarm.addOrReplaceChild("leftshoulder", CubeListBuilder.create()
				.texOffs(80, 0).mirror().addBox(-0.5F, -1.0F, -1.5F, 3.0F, 4.0F, 3.0F).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition rightleg = vampire.addOrReplaceChild("rightleg", CubeListBuilder.create()
				.texOffs(24, 12).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 14.0F, 3.0F), PartPose.offsetAndRotation(-2.0F, 2.0F, 0.0F, 0.0873F, 0.0F, -0.0349F));

		PartDefinition leftleg = vampire.addOrReplaceChild("leftleg", CubeListBuilder.create()
				.texOffs(24, 12).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 14.0F, 3.0F), PartPose.offsetAndRotation(2.0F, 2.0F, 0.0F, -0.0873F, 0.0F, 0.0349F));

		PartDefinition mantle = vampire.addOrReplaceChild("mantle", CubeListBuilder.create()
				.texOffs(36, 14).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 7.0F, 6.0F), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cloak1 = vampire.addOrReplaceChild("cloak1", CubeListBuilder.create()
				.texOffs(36, 27).addBox(-6.5F, 0.0F, 0.0F, 13.0F, 4.0F, 3.0F), PartPose.offset(0.0F, -7.0F, 1.0F));

		PartDefinition cloak2 = cloak1.addOrReplaceChild("cloak2", CubeListBuilder.create()
				.texOffs(36, 34).addBox(-7.0F, 0.0F, -4.0F, 14.0F, 5.0F, 4.0F), PartPose.offset(0.0F, 4.0F, 3.0F));

		PartDefinition cloak3 = cloak2.addOrReplaceChild("cloak3", CubeListBuilder.create()
				.texOffs(36, 43).addBox(-7.5F, 0.0F, -5.0F, 15.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition cloak4 = cloak3.addOrReplaceChild("cloak4", CubeListBuilder.create()
				.texOffs(36, 53).addBox(-8.0F, 0.0F, -6.0F, 16.0F, 6.0F, 6.0F), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition waist = vampire.addOrReplaceChild("waist", CubeListBuilder.create()
				.texOffs(80, 7).addBox(-3.0F, 5.0F, -2.5F, 6.0F, 2.0F, 5.0F), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition waist1 = vampire.addOrReplaceChild("waist1", CubeListBuilder.create()
				.texOffs(80, 14).addBox(-3.5F, 7.5F, -3.0F, 7.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition waist2 = vampire.addOrReplaceChild("waist2", CubeListBuilder.create()
				.texOffs(80, 22).addBox(-3.5F, 0.0F, -0.5F, 7.0F, 4.0F, 6.0F), PartPose.offset(0.0F, -1.0F, -2.5F));

		PartDefinition waist3 = waist2.addOrReplaceChild("waist3", CubeListBuilder.create()
				.texOffs(80, 32).addBox(-4.0F, 0.0F, -0.5667F, 8.0F, 4.0F, 7.0F), PartPose.offset(0.0F, 4.0F, -0.5F));

		PartDefinition waist4 = waist3.addOrReplaceChild("waist4", CubeListBuilder.create()
				.texOffs(80, 43).addBox(-4.5F, 0.0F, -1.0F, 9.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 4.0F, -0.5F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(VampireEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// anchor (bobbing)
		root.y = 6.0F + Mth.cos((1.5F + ageInTicks) * 0.1F) * 0.5F;

		// head
		head.yRot = netHeadYaw / 57.295776F;
		head.xRot = headPitch / 57.295776F;
		headeyes.yRot = head.yRot;
		headeyes.xRot = head.xRot;
		headaccessory.yRot = head.yRot;
		headaccessory.xRot = head.xRot;

		headeyes.visible = (int) ageInTicks % 60 == 0 && limbSwingAmount <= 0.1F;

		// arms
		rightarm.zRot = 0.0F;
		leftarm.zRot = 0.0F;
		rightarm.xRot = 0.0F;
		leftarm.xRot = 0.0F;

		if (attackTime > 0.0F) {
			holdingMelee();
		}

		rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + 0.1745329F;
		leftarm.zRot -= (Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + 0.1745329F;
		rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
		leftarm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;

		// body
		cloak1.xRot = 5 * Mth.DEG_TO_RAD;
		cloak2.xRot = 5 * Mth.DEG_TO_RAD;
		cloak3.xRot = 5 * Mth.DEG_TO_RAD;
		cloak4.xRot = 5 * Mth.DEG_TO_RAD;

		cloak1.zRot = Mth.cos(ageInTicks * 7 * Mth.DEG_TO_RAD) * (1 * Mth.DEG_TO_RAD);
		cloak2.zRot = Mth.cos(ageInTicks * 7 * Mth.DEG_TO_RAD) * (2 * Mth.DEG_TO_RAD);
		cloak3.zRot = Mth.cos(ageInTicks * 7 * Mth.DEG_TO_RAD) * (3 * Mth.DEG_TO_RAD);
		cloak4.zRot = Mth.cos(ageInTicks * 7 * Mth.DEG_TO_RAD) * (4 * Mth.DEG_TO_RAD);

		waist2.xRot = 5 * Mth.DEG_TO_RAD;
		waist3.xRot = 5 * Mth.DEG_TO_RAD;
		waist4.xRot = 5 * Mth.DEG_TO_RAD;

		waist1.zRot = Mth.cos(ageInTicks * 7 * Mth.DEG_TO_RAD) * (1 * Mth.DEG_TO_RAD);
		waist2.zRot = waist1.zRot;
		waist3.zRot = Mth.cos(ageInTicks * 7 * Mth.DEG_TO_RAD) * (2 * Mth.DEG_TO_RAD);
		waist4.zRot = waist3.zRot;
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
		poseStack.translate(0, 0.5, 0.0);
		getArm(arm).translateAndRotate(poseStack);
	}
}
