package net.sodiumzh.gogaddon.client.model;

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
import net.sodiumzh.gogaddon.entity.GorgonEntity;

@OnlyIn(Dist.CLIENT)
public class GorgonModel extends EntityModel<GorgonEntity> implements HeadedModel, ArmedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headeyes;
	private final ModelPart headaccessory;
	private final ModelPart bodytop;
	private final ModelPart rightarm;
	private final ModelPart leftarm;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;
	private final ModelPart tail5;
	private final ModelPart tail6;
	private final ModelPart tail7;
	private final ModelPart tail8;
	private final ModelPart snake1tongue;
	private final ModelPart snake2tongue;
	private final ModelPart snake3tongue;
	private final ModelPart rightsnaketongue;
	private final ModelPart leftsnaketongue;

	private static final double CYCLES_PER_BLOCK = 0.1D;
	private final float[][] undulationCycle = new float[][] {
		{   5F,   0F,-11.25F,  -45F,-22.5F,    0F, 22.5F,   45F},
		{  10F,  10F,     0F,-22.5F,  -45F,-22.5F,    0F, 22.5F},
		{   5F,  20F, 11.25F,    0F,-22.5F,  -45F,-22.5F,    0F},
		{   0F,  10F, 22.5F, 22.5F,    0F,-22.5F,  -45F,-22.5F},
		{  -5F,   0F, 11.25F,   45F, 22.5F,    0F,-22.5F,  -45F},
		{ -10F, -10F,     0F, 22.5F,   45F, 22.5F,    0F,-22.5F},
		{  -5F, -20F,-11.25F,    0F, 22.5F,   45F, 22.5F,    0F},
		{   0F, -10F, -22.5F,-22.5F,    0F, 22.5F,   45F, 22.5F},
	};

	public GorgonModel(ModelPart root) {
		this.root = root.getChild("gorgon");
		this.head = this.root.getChild("head");
		this.headeyes = this.root.getChild("headeyes");
		this.headaccessory = this.root.getChild("headaccessory");
		this.bodytop = this.root.getChild("bodytop");
		this.rightarm = this.root.getChild("rightarm");
		this.leftarm = this.root.getChild("leftarm");
		this.tail1 = this.root.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.tail4 = this.tail3.getChild("tail4");
		this.tail5 = this.tail4.getChild("tail5");
		this.tail6 = this.tail5.getChild("tail6");
		this.tail7 = this.tail6.getChild("tail7");
		this.tail8 = this.tail7.getChild("tail8");
		this.snake1tongue = this.head.getChild("snake1").getChild("snake1tongue");
		this.snake2tongue = this.head.getChild("snake2").getChild("snake2tongue");
		this.snake3tongue = this.head.getChild("snake3").getChild("snake3tongue");
		this.rightsnaketongue = this.head.getChild("rightsnake1").getChild("rightsnake2").getChild("rightsnaketongue");
		this.leftsnaketongue = this.head.getChild("leftsnake1").getChild("leftsnake2").getChild("leftsnaketongue");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition gorgon = partdefinition.addOrReplaceChild("gorgon", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = gorgon.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		gorgon.addOrReplaceChild("headeyes", CubeListBuilder.create()
				.texOffs(24, 0).addBox(-3.0F, -6.0F, -3.1F, 6.0F, 6.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		gorgon.addOrReplaceChild("headaccessory", CubeListBuilder.create()
				.texOffs(36, 0).addBox(-3.5F, -6.5F, -3.5F, 7.0F, 7.0F, 7.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		gorgon.addOrReplaceChild("neck", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		gorgon.addOrReplaceChild("bodytop", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-2.5F, 0.0F, -1.5F, 5.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		gorgon.addOrReplaceChild("bodymid", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-2.0F, 5.5F, -1.5F, 4.0F, 3.0F, 2.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		gorgon.addOrReplaceChild("bodymidbutton", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-0.5F, 6.0F, -1.6F, 1.0F, 2.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		gorgon.addOrReplaceChild("bodybottom", CubeListBuilder.create()
				.texOffs(0, 30).addBox(-3.0F, 8.0F, -2.0F, 6.0F, 3.0F, 3.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		gorgon.addOrReplaceChild("rightchest", CubeListBuilder.create()
				.texOffs(0, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-1.3F, -21.0F, -1.5F, 0.7854F, 0.1745F, 0.0873F));

		gorgon.addOrReplaceChild("leftchest", CubeListBuilder.create()
				.texOffs(0, 36).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(1.3F, -21.0F, -1.5F, 0.7854F, -0.1745F, -0.0873F));

		gorgon.addOrReplaceChild("rightarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 0.0F, 0.0F, 0.0F, 0.2618F));

		gorgon.addOrReplaceChild("leftarm", CubeListBuilder.create()
				.texOffs(24, 12).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(2.5F, -21.5F, 0.0F, 0.0F, 0.0F, -0.2618F));

		// Head children: hair
		head.addOrReplaceChild("hair", CubeListBuilder.create()
				.texOffs(36, 14).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		// Head children: ears
		head.addOrReplaceChild("rightear", CubeListBuilder.create()
				.texOffs(36, 32).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F), PartPose.offsetAndRotation(-3.0F, -3.0F, -3.0F, 0.0F, -0.5236F, 0.0F));

		head.addOrReplaceChild("leftear", CubeListBuilder.create()
				.texOffs(36, 32).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F), PartPose.offsetAndRotation(3.0F, -3.0F, -3.0F, 0.0F, 0.5236F, 0.0F));

		// Head children: top snakes
		PartDefinition snake1 = head.addOrReplaceChild("snake1", CubeListBuilder.create()
				.texOffs(36, 30).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -6.0F, -1.5F));

		snake1.addOrReplaceChild("snake1tongue", CubeListBuilder.create()
				.texOffs(59, 30).mirror().addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0F, -1.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition snake2 = head.addOrReplaceChild("snake2", CubeListBuilder.create()
				.texOffs(36, 30).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(-3.0F, -6.0F, 0.0F));

		snake2.addOrReplaceChild("snake2tongue", CubeListBuilder.create()
				.texOffs(59, 30).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -1.5F, -1.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition snake3 = head.addOrReplaceChild("snake3", CubeListBuilder.create()
				.texOffs(36, 30).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(3.0F, -6.0F, 0.0F));

		snake3.addOrReplaceChild("snake3tongue", CubeListBuilder.create()
				.texOffs(59, 30).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -1.5F, -1.0F, 0.7854F, 0.0F, 0.0F));

		// Head children: right snake chain
		PartDefinition rightsnake1 = head.addOrReplaceChild("rightsnake1", CubeListBuilder.create()
				.texOffs(52, 30).addBox(-6.0F, -5.5F, -1.5F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightsnake2 = rightsnake1.addOrReplaceChild("rightsnake2", CubeListBuilder.create()
				.texOffs(44, 30).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(-6.0F, -5.5F, -1.5F));

		rightsnake2.addOrReplaceChild("rightsnaketongue", CubeListBuilder.create()
				.texOffs(59, 30).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 1.0F, -1.0F, 0.7854F, 0.0F, 0.0F));

		// Head children: left snake chain
		PartDefinition leftsnake1 = head.addOrReplaceChild("leftsnake1", CubeListBuilder.create()
				.texOffs(52, 30).addBox(4.0F, -5.5F, -1.5F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftsnake2 = leftsnake1.addOrReplaceChild("leftsnake2", CubeListBuilder.create()
				.texOffs(44, 30).addBox(5.0F, -6.5F, -2.5F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		leftsnake2.addOrReplaceChild("leftsnaketongue", CubeListBuilder.create()
				.texOffs(59, 30).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 0.0F, 1.0F), PartPose.offsetAndRotation(6.0F, -4.5F, -2.5F, 0.7854F, 0.0F, 0.0F));

		// Head children: bangs
		head.addOrReplaceChild("rightbang", CubeListBuilder.create()
				.texOffs(36, 39).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F), PartPose.offset(-3.0F, -4.0F, -3.0F));

		head.addOrReplaceChild("leftbang", CubeListBuilder.create()
				.texOffs(36, 39).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F), PartPose.offset(3.0F, -4.0F, -3.0F));

		// Waist
		gorgon.addOrReplaceChild("waist1", CubeListBuilder.create()
				.texOffs(68, 0).addBox(-4.0F, -1.5F, -3.5F, 5.0F, 7.0F, 6.0F), PartPose.offset(0.0F, -13.0F, 0.0F));

		gorgon.addOrReplaceChild("waist2", CubeListBuilder.create()
				.texOffs(68, 13).addBox(-1.0F, -1.5F, -3.0F, 5.0F, 2.0F, 5.0F), PartPose.offsetAndRotation(0.0F, -13.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

		// Tail chain
		PartDefinition tail1 = gorgon.addOrReplaceChild("tail1", CubeListBuilder.create()
				.texOffs(90, 0).addBox(-3.5F, -1.0F, -2.5F, 7.0F, 4.0F, 4.0F), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create()
				.texOffs(90, 8).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 4.0F, 4.0F), PartPose.offset(0.0F, 3.0F, -2.5F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create()
				.texOffs(90, 16).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 4.0F, 4.0F), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create()
				.texOffs(90, 16).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 4.0F, 4.0F), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition tail5 = tail4.addOrReplaceChild("tail5", CubeListBuilder.create()
				.texOffs(90, 24).addBox(-2.0F, 0.0F, 0.5F, 4.0F, 4.0F, 3.0F), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition tail6 = tail5.addOrReplaceChild("tail6", CubeListBuilder.create()
				.texOffs(90, 24).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 3.0F), PartPose.offset(0.0F, 4.0F, 0.5F));

		PartDefinition tail7 = tail6.addOrReplaceChild("tail7", CubeListBuilder.create()
				.texOffs(90, 31).addBox(-1.5F, 0.0F, 0.5F, 3.0F, 3.0F, 2.0F), PartPose.offset(0.0F, 4.0F, 0.0F));

		tail7.addOrReplaceChild("tail8", CubeListBuilder.create()
				.texOffs(90, 36).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 3.0F, 0.5F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(GorgonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		root.y = 26.0F;
		// head
		head.yRot = netHeadYaw / 57.295776F;
		head.xRot = headPitch / 57.295776F;
		headeyes.yRot = head.yRot;
		headeyes.xRot = head.xRot;
		headaccessory.yRot = head.yRot;
		headaccessory.xRot = head.xRot;

		headeyes.visible = entity.tickCount % 60 == 0 && limbSwingAmount <= 0.1F;

		snake1tongue.visible = entity.tickCount % 60 == 0 && limbSwingAmount <= 0.1F;
		snake2tongue.visible = entity.tickCount % 120 == 0 && limbSwingAmount <= 0.1F;
		snake3tongue.visible = entity.tickCount % 180 == 0 && limbSwingAmount <= 0.1F;
		rightsnaketongue.visible = entity.tickCount % 180 == 0 && limbSwingAmount <= 0.1F;
		leftsnaketongue.visible = entity.tickCount % 120 == 0 && limbSwingAmount <= 0.1F;

		// arms
		rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
		leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;

		rightarm.zRot = 0.0F;
		leftarm.zRot = 0.0F;

		// TODO: Add bow animation when entity supports isSwingingArms()
		if (attackTime > 0.0F) {
			holdingMelee();
		}

		rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.4363323F;
		rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.025F;
		leftarm.zRot -= (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.4363323F;
		leftarm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.025F;

		// tail
		tail1.xRot = -0.1308997F;
		tail2.xRot = +0.3926991F;
		tail3.xRot = +0.3926991F;
		tail4.xRot = +0.785398F;
		tail8.xRot = +0.3926991F;

		int cycleIndex = Math.abs((int) ((limbSwing * CYCLES_PER_BLOCK) % undulationCycle.length));

		tail5.zRot = undulationCycle[cycleIndex][4] * Mth.DEG_TO_RAD;
		tail6.zRot = undulationCycle[cycleIndex][5] * Mth.DEG_TO_RAD;
		tail7.zRot = undulationCycle[cycleIndex][6] * Mth.DEG_TO_RAD;
		tail8.zRot = undulationCycle[cycleIndex][7] * Mth.DEG_TO_RAD;
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
		poseStack.translate(0, 1.625, 0.0);
		getArm(arm).translateAndRotate(poseStack);
	}
}
