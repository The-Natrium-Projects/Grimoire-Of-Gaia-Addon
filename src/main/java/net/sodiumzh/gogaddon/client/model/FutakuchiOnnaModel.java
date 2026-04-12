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
import net.sodiumzh.gogaddon.entity.mob.FutakuchiOnnaEntity;

@OnlyIn(Dist.CLIENT)
public class FutakuchiOnnaModel extends EntityModel<FutakuchiOnnaEntity> implements HeadedModel, ArmedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headaccessory;
	private final ModelPart hair1;
	private final ModelPart hair2;
	private final ModelPart hair3;
	private final ModelPart mouth1;
	private final ModelPart mouth2;
	private final ModelPart rightchest;
	private final ModelPart leftchest;
	private final ModelPart rightarm;
	private final ModelPart rightarmupper;
	private final ModelPart leftarm;
	private final ModelPart leftarmupper;
	private final ModelPart rightleg;
	private final ModelPart rightsandal;
	private final ModelPart leftleg;
	private final ModelPart leftsandal;

	public FutakuchiOnnaModel(ModelPart root) {
		this.root = root.getChild("futakuchionna");
		this.head = this.root.getChild("head");
		this.headaccessory = this.root.getChild("headaccessory");
		this.hair1 = this.root.getChild("hair1");
		this.hair2 = this.root.getChild("hair2");
		this.hair3 = this.root.getChild("hair3");
		this.mouth1 = this.root.getChild("mouth1");
		this.mouth2 = this.root.getChild("mouth2");
		this.rightchest = this.root.getChild("rightchest");
		this.leftchest = this.root.getChild("leftchest");
		this.rightarm = this.root.getChild("rightarm");
		this.rightarmupper = this.root.getChild("rightarmupper");
		this.leftarm = this.root.getChild("leftarm");
		this.leftarmupper = this.root.getChild("leftarmupper");
		this.rightleg = this.root.getChild("rightleg");
		this.rightsandal = this.root.getChild("rightsandal");
		this.leftleg = this.root.getChild("leftleg");
		this.leftsandal = this.root.getChild("leftsandal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition futakuchionna = partdefinition.addOrReplaceChild("futakuchionna", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		futakuchionna.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 2.0F, -0.2618F, 0.0F, 0.0F));

		futakuchionna.addOrReplaceChild("headaccessory", CubeListBuilder.create()
				.texOffs(36, 0).addBox(-3.5F, -6.5F, -3.5F, 7.0F, 7.0F, 7.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 2.0F, -0.2618F, 0.0F, 0.0F));

		futakuchionna.addOrReplaceChild("hair1", CubeListBuilder.create()
				.texOffs(36, 14).addBox(-5.0F, -8.0F, -5.5F, 10.0F, 10.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 2.0F, -0.0873F, 0.0F, 0.0F));

		futakuchionna.addOrReplaceChild("hair2", CubeListBuilder.create()
				.texOffs(36, 28).addBox(-4.0F, -7.0F, -7.5F, 8.0F, 8.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 2.0F, -0.0873F, 0.0F, 0.0F));

		futakuchionna.addOrReplaceChild("hair3", CubeListBuilder.create()
				.texOffs(36, 40).addBox(-4.0F, -6.0F, -1.0F, 8.0F, 10.0F, 6.0F), PartPose.offset(0.0F, -23.0F, 2.0F));

		futakuchionna.addOrReplaceChild("mouth1", CubeListBuilder.create()
				.texOffs(64, 0).addBox(-3.0F, -3.5F, -6.0F, 6.0F, 3.0F, 6.0F), PartPose.offset(0.0F, -26.0F, 0.5F));

		futakuchionna.addOrReplaceChild("mouth2", CubeListBuilder.create()
				.texOffs(64, 9).addBox(-3.0F, -0.5F, -6.0F, 6.0F, 3.0F, 6.0F), PartPose.offset(0.0F, -26.0F, 0.5F));

		futakuchionna.addOrReplaceChild("bodytop", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 5.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.2443F, 0.0F, 0.0F));

		futakuchionna.addOrReplaceChild("bodymiddle", CubeListBuilder.create()
				.texOffs(0, 20).addBox(-2.0F, 4.5F, 0.5F, 4.0F, 3.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		futakuchionna.addOrReplaceChild("bodymiddlebutton", CubeListBuilder.create()
				.texOffs(0, 20).addBox(-0.5F, 5.0F, -1.6F, 1.0F, 2.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		futakuchionna.addOrReplaceChild("bodybottom", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-2.5F, 7.0F, -1.5F, 5.0F, 3.0F, 3.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		futakuchionna.addOrReplaceChild("rightchest", CubeListBuilder.create()
				.texOffs(0, 31).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(1.3F, -20.0F, 3.0F, -0.9599F, 0.1745F, -0.0873F));

		futakuchionna.addOrReplaceChild("leftchest", CubeListBuilder.create()
				.texOffs(8, 31).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-1.3F, -20.0F, 3.0F, -0.9599F, -0.1745F, 0.0873F));

		futakuchionna.addOrReplaceChild("back", CubeListBuilder.create()
				.texOffs(88, 11).addBox(-3.5F, 4.0F, -1.0F, 7.0F, 4.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		futakuchionna.addOrReplaceChild("rightarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 2.0F, 0.0F, 0.0F, 0.1745F));

		futakuchionna.addOrReplaceChild("rightarmupper", CubeListBuilder.create()
				.texOffs(88, 0).addBox(-2.5F, 0.5F, -1.5F, 3.0F, 8.0F, 3.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 2.0F, 0.0F, 0.0F, 0.1745F));

		futakuchionna.addOrReplaceChild("leftarm", CubeListBuilder.create()
				.texOffs(16, 12).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(2.5F, -21.5F, 2.0F, 0.0F, 0.0F, -0.1745F));

		futakuchionna.addOrReplaceChild("leftarmupper", CubeListBuilder.create()
				.texOffs(100, 0).addBox(-0.5F, 0.5F, -1.5F, 3.0F, 8.0F, 3.0F), PartPose.offsetAndRotation(2.5F, -21.5F, 2.0F, 0.0F, 0.0F, -0.1745F));

		futakuchionna.addOrReplaceChild("waist", CubeListBuilder.create()
				.texOffs(88, 16).addBox(-3.0F, 10.0F, -1.0F, 6.0F, 3.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		futakuchionna.addOrReplaceChild("rightleg", CubeListBuilder.create()
				.texOffs(24, 12).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F), PartPose.offset(-1.5F, -13.0F, 0.0F));

		futakuchionna.addOrReplaceChild("rightsandal", CubeListBuilder.create()
				.texOffs(88, 23).addBox(-1.0F, 11.0F, -1.5F, 2.0F, 1.0F, 3.0F), PartPose.offset(-1.5F, -13.0F, 0.0F));

		futakuchionna.addOrReplaceChild("leftleg", CubeListBuilder.create()
				.texOffs(24, 12).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F).mirror(false), PartPose.offset(1.5F, -13.0F, 0.0F));

		futakuchionna.addOrReplaceChild("leftsandal", CubeListBuilder.create()
				.texOffs(88, 23).mirror().addBox(-1.0F, 11.0F, -1.5F, 2.0F, 1.0F, 3.0F).mirror(false), PartPose.offset(1.5F, -13.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(FutakuchiOnnaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// head (yaw only, pitch stays at initial -0.2618)
		head.yRot = netHeadYaw / 57.295776F;
		if (!entity.hasTarget()) head.yRot += (float) Math.PI;	// Face forward when not having a target
		headaccessory.yRot = head.yRot;
		hair1.yRot = head.yRot;
		hair2.yRot = head.yRot;
		hair3.yRot = head.yRot;

		// mouths
		mouth1.xRot = Mth.cos(ageInTicks * 0.8F + (float) Math.PI) * 0.6F * limbSwingAmount * 0.5F;
		mouth2.xRot = Mth.cos(ageInTicks * 0.8F) * 0.6F * limbSwingAmount * 0.5F;
		mouth1.yRot = head.yRot;
		mouth2.yRot = head.yRot;

		// arms
		rightarm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount * 0.5F;
		leftarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount * 0.5F;
		rightarmupper.xRot = rightarm.xRot;
		leftarmupper.xRot = leftarm.xRot;

		// legs
		rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
		leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount;
		rightsandal.xRot = rightleg.xRot;
		leftsandal.xRot = leftleg.xRot;

		// chest
		rightchest.xRot = rightleg.xRot - 0.9599311F;
		leftchest.xRot = rightleg.xRot - 0.9599311F;
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
