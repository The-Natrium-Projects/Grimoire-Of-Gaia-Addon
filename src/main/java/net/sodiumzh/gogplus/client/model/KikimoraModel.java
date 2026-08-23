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
import net.sodiumzh.gogplus.entity.mob.KikimoraEntity;

@OnlyIn(Dist.CLIENT)
public class KikimoraModel extends EntityModel<KikimoraEntity> implements HeadedModel, ArmedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headeyes;
	private final ModelPart headaccessory;
	private final ModelPart bodytop;
	private final ModelPart rightarm;
	private final ModelPart leftarm;
	private final ModelPart rightarmlower;
	private final ModelPart leftarmlower;
	private final ModelPart rightleg;
	private final ModelPart leftleg;
	private final ModelPart rightear;
	private final ModelPart leftear;
	private final ModelPart rightskirt01;
	private final ModelPart leftskirt01;
	private final ModelPart rightskirt02;
	private final ModelPart leftskirt02;
	private final ModelPart rightskirt03;
	private final ModelPart leftskirt03;
	private final ModelPart tail01;
	private final ModelPart tail02;
	private final ModelPart tail03;
	private final ModelPart tail04;
	private final ModelPart tail05;

	public KikimoraModel(ModelPart root) {
		this.root = root.getChild("kikimora");
		this.head = this.root.getChild("head");
		this.headeyes = this.root.getChild("headeyes");
		this.headaccessory = this.root.getChild("headaccessory");
		this.bodytop = this.root.getChild("bodytop");
		this.rightarm = this.root.getChild("rightarm");
		this.leftarm = this.root.getChild("leftarm");
		this.rightarmlower = this.rightarm.getChild("rightarmlower");
		this.leftarmlower = this.leftarm.getChild("leftarmlower");
		this.rightleg = this.root.getChild("rightleg");
		this.leftleg = this.root.getChild("leftleg");
		this.rightear = this.head.getChild("rightear");
		this.leftear = this.head.getChild("leftear");
		this.rightskirt01 = this.root.getChild("rightskirt01");
		this.leftskirt01 = this.root.getChild("leftskirt01");
		this.rightskirt02 = this.rightleg.getChild("rightskirt02");
		this.leftskirt02 = this.leftleg.getChild("leftskirt02");
		this.rightskirt03 = this.rightskirt02.getChild("rightskirt03");
		this.leftskirt03 = this.leftskirt02.getChild("leftskirt03");
		this.tail01 = this.root.getChild("tail01");
		this.tail02 = this.tail01.getChild("tail02");
		this.tail03 = this.tail02.getChild("tail03");
		this.tail04 = this.tail03.getChild("tail04");
		this.tail05 = this.tail04.getChild("tail05");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition kikimora = partdefinition.addOrReplaceChild("kikimora", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		// Head
		PartDefinition head = kikimora.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		kikimora.addOrReplaceChild("headeyes", CubeListBuilder.create()
				.texOffs(24, 0).addBox(-3.0F, -6.0F, -3.1F, 6.0F, 6.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		kikimora.addOrReplaceChild("headaccessory", CubeListBuilder.create()
				.texOffs(36, 0).addBox(-3.5F, -6.5F, -3.5F, 7.0F, 7.0F, 7.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		// Head children: hat and ears
		head.addOrReplaceChild("hat", CubeListBuilder.create()
				.texOffs(36, 14).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 4.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		head.addOrReplaceChild("rightear", CubeListBuilder.create()
				.texOffs(36, 26).addBox(0.0F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F), PartPose.offsetAndRotation(-3.5F, -5.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		head.addOrReplaceChild("leftear", CubeListBuilder.create()
				.texOffs(36, 26).mirror().addBox(-3.0F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F).mirror(false), PartPose.offsetAndRotation(3.5F, -5.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

		// Neck
		kikimora.addOrReplaceChild("neck", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		// Body
		kikimora.addOrReplaceChild("bodytop", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-2.5F, 0.0F, -1.5F, 5.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		kikimora.addOrReplaceChild("bodymiddle", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-2.0F, 5.5F, -1.5F, 4.0F, 3.0F, 2.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		kikimora.addOrReplaceChild("bodymiddlebutton", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-0.5F, 6.0F, -1.6F, 1.0F, 2.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		kikimora.addOrReplaceChild("bodybottom", CubeListBuilder.create()
				.texOffs(0, 30).addBox(-3.0F, 8.0F, -2.5F, 6.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		// Chests
		kikimora.addOrReplaceChild("rightchest", CubeListBuilder.create()
				.texOffs(0, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-1.3F, -21.0F, -1.5F, 0.7854F, 0.1745F, 0.0873F));

		kikimora.addOrReplaceChild("leftchest", CubeListBuilder.create()
				.texOffs(0, 36).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(1.3F, -21.0F, -1.5F, 0.7854F, -0.1745F, -0.0873F));

		// Right arm and children
		PartDefinition rightarm = kikimora.addOrReplaceChild("rightarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(-2.5F, -21.5F, 0.0F));

		PartDefinition rightpauldron = rightarm.addOrReplaceChild("rightpauldron", CubeListBuilder.create()
				.texOffs(36, 41).addBox(-2.5F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		rightpauldron.addOrReplaceChild("rightpauldronoverlay", CubeListBuilder.create()
				.texOffs(36, 33).addBox(-2.5F, -1.5F, -2.0F, 4.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition rightarmlower = rightarm.addOrReplaceChild("rightarmlower", CubeListBuilder.create()
				.texOffs(16, 20).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(-1.0F, 5.0F, 1.0F));

		rightarmlower.addOrReplaceChild("rightcufflink", CubeListBuilder.create()
				.texOffs(36, 47).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 6.0F, -1.0F, -0.1745F, 0.0F, -0.1745F));

		// Left arm and children
		PartDefinition leftarm = kikimora.addOrReplaceChild("leftarm", CubeListBuilder.create()
				.texOffs(16, 12).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F).mirror(false), PartPose.offset(2.5F, -21.5F, 0.0F));

		PartDefinition leftpauldron = leftarm.addOrReplaceChild("leftpauldron", CubeListBuilder.create()
				.texOffs(36, 41).mirror().addBox(-0.5F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		leftpauldron.addOrReplaceChild("leftpauldronoverlay", CubeListBuilder.create()
				.texOffs(36, 33).mirror().addBox(-1.5F, -1.5F, -2.0F, 4.0F, 4.0F, 4.0F).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition leftarmlower = leftarm.addOrReplaceChild("leftarmlower", CubeListBuilder.create()
				.texOffs(16, 20).mirror().addBox(-1.0F, 0.0F, -2.0F, 2.0F, 6.0F, 2.0F).mirror(false), PartPose.offset(1.0F, 5.0F, 1.0F));

		leftarmlower.addOrReplaceChild("leftcufflink", CubeListBuilder.create()
				.texOffs(36, 47).mirror().addBox(-1.0F, -5.0F, -1.0F, 2.0F, 4.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(0.0F, 6.0F, -1.0F, -0.1745F, 0.0F, 0.1745F));

		// Legs
		PartDefinition rightleg = kikimora.addOrReplaceChild("rightleg", CubeListBuilder.create()
				.texOffs(24, 12).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 14.0F, 3.0F), PartPose.offsetAndRotation(-2.0F, -13.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		PartDefinition leftleg = kikimora.addOrReplaceChild("leftleg", CubeListBuilder.create()
				.texOffs(24, 12).mirror().addBox(-1.5F, -1.0F, -1.5F, 3.0F, 14.0F, 3.0F).mirror(false), PartPose.offsetAndRotation(2.0F, -13.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		// Leg children: skirts
		PartDefinition rightskirt02 = rightleg.addOrReplaceChild("rightskirt02", CubeListBuilder.create()
				.texOffs(84, 17).addBox(-4.5F, 1.5F, -4.0F, 5.0F, 4.0F, 8.0F), PartPose.offset(2.0F, 0.0F, 0.0F));

		rightskirt02.addOrReplaceChild("rightskirt03", CubeListBuilder.create()
				.texOffs(84, 29).addBox(-5.0F, 5.5F, -4.5F, 6.0F, 5.0F, 9.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftskirt02 = leftleg.addOrReplaceChild("leftskirt02", CubeListBuilder.create()
				.texOffs(84, 17).mirror().addBox(-0.5F, 1.5F, -4.0F, 5.0F, 4.0F, 8.0F).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		leftskirt02.addOrReplaceChild("leftskirt03", CubeListBuilder.create()
				.texOffs(84, 29).mirror().addBox(-1.0F, 5.5F, -4.5F, 6.0F, 5.0F, 9.0F).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		// Standalone skirts
		kikimora.addOrReplaceChild("rightskirt01", CubeListBuilder.create()
				.texOffs(84, 7).addBox(-4.0F, -1.5F, -3.5F, 4.0F, 3.0F, 7.0F), PartPose.offset(0.0F, -13.0F, 0.0F));

		kikimora.addOrReplaceChild("leftskirt01", CubeListBuilder.create()
				.texOffs(84, 7).mirror().addBox(0.0F, -1.5F, -3.5F, 4.0F, 3.0F, 7.0F).mirror(false), PartPose.offset(0.0F, -13.0F, 0.0F));

		// Skirt ribbon
		kikimora.addOrReplaceChild("skirtribbon", CubeListBuilder.create()
				.texOffs(84, 0).addBox(-3.5F, 1.0F, 7.5F, 7.0F, 2.0F, 5.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -1.0472F, 0.0F, 0.0F));

		// Tail chain
		PartDefinition tail01 = kikimora.addOrReplaceChild("tail01", CubeListBuilder.create()
				.texOffs(68, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, -15.0F, 1.0F));

		PartDefinition tail02 = tail01.addOrReplaceChild("tail02", CubeListBuilder.create()
				.texOffs(68, 6).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition tail03 = tail02.addOrReplaceChild("tail03", CubeListBuilder.create()
				.texOffs(68, 14).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail04 = tail03.addOrReplaceChild("tail04", CubeListBuilder.create()
				.texOffs(68, 22).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 0.0F, 4.0F));

		tail04.addOrReplaceChild("tail05", CubeListBuilder.create()
				.texOffs(68, 28).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.offset(0.0F, 0.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(KikimoraEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// head
		head.yRot = netHeadYaw / 57.295776F;
		head.xRot = headPitch / 57.295776F;
		headeyes.yRot = head.yRot;
		headeyes.xRot = head.xRot;
		headaccessory.yRot = head.yRot;
		headaccessory.xRot = head.xRot;

		headeyes.visible = entity.tickCount % 60 == 0 && limbSwingAmount <= 0.1F;

		// ears
		float earDefaultAngleZ = 0.5235988F;

		rightear.zRot = Mth.cos(entity.tickCount * 7 * Mth.DEG_TO_RAD) * (4 * Mth.DEG_TO_RAD);
		rightear.zRot += earDefaultAngleZ;
		leftear.zRot = Mth.cos(entity.tickCount * 7 * Mth.DEG_TO_RAD) * -(4 * Mth.DEG_TO_RAD);
		leftear.zRot += -earDefaultAngleZ;

		// arms
		rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
		leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;

		rightarm.zRot = 0.0F;
		leftarm.zRot = 0.0F;

		if (attackTime > 0.0F) {
			holdingMelee();
		}

		float armDefaultAngleY = 0.349066F;
		float armDefaultAngleZ = 0.174533F;

		rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.05F;
		rightarm.yRot = +armDefaultAngleY;
		rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + armDefaultAngleZ;

		leftarm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
		leftarm.yRot = -armDefaultAngleY;
		leftarm.zRot -= (Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F) + armDefaultAngleZ;

		rightarmlower.xRot = -armDefaultAngleY;
		leftarmlower.xRot = -armDefaultAngleY;

		// tail
		tail02.xRot = -0.3926991F;
		tail03.xRot = -0.785398F;
		tail04.xRot = +0.3926991F;
		tail05.xRot = +0.785398F;

		tail02.yRot = Mth.cos(entity.tickCount * 7 * Mth.DEG_TO_RAD) * (1 * Mth.DEG_TO_RAD);
		tail03.yRot = Mth.cos(entity.tickCount * 7 * Mth.DEG_TO_RAD) * (5 * Mth.DEG_TO_RAD);
		tail04.yRot = Mth.cos(entity.tickCount * 7 * Mth.DEG_TO_RAD) * (10 * Mth.DEG_TO_RAD);
		tail05.yRot = Mth.cos(entity.tickCount * 7 * Mth.DEG_TO_RAD) * (15 * Mth.DEG_TO_RAD);

		// legs
		rightleg.xRot = (Mth.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount) * 0.5F;
		leftleg.xRot = (Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount) * 0.5F;
		rightleg.yRot = -0.0872665F;
		leftleg.yRot = 0.0872665F;
		rightleg.zRot = 0.0F;
		leftleg.zRot = 0.0F;

		if (riding) {
			rightarm.xRot += -((float) Math.PI / 5F);
			leftarm.xRot += -((float) Math.PI / 5F);
			rightleg.xRot = -1.4137167F;
			rightleg.yRot = ((float) Math.PI / 10F);
			rightleg.zRot = 0.07853982F;
			leftleg.xRot = -1.4137167F;
			leftleg.yRot = -((float) Math.PI / 10F);
			leftleg.zRot = -0.07853982F;
		}
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
		poseStack.translate(0, 1.5, -0.0625d);
		getArm(arm).translateAndRotate(poseStack);
	}
}
