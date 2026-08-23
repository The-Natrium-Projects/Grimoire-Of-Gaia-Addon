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
import net.sodiumzh.gogplus.entity.mob.SahuaginEntity;

@OnlyIn(Dist.CLIENT)
public class SahuaginModel extends EntityModel<SahuaginEntity> implements HeadedModel, ArmedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headaccessory;
	private final ModelPart hair1;
	private final ModelPart hair2;
	private final ModelPart rightear;
	private final ModelPart leftear;
	private final ModelPart bodytop;
	private final ModelPart rightarm;
	private final ModelPart rightarmlower1;
	private final ModelPart rightarmlower2;
	private final ModelPart righthand;
	private final ModelPart leftarm;
	private final ModelPart leftarmlower1;
	private final ModelPart leftarmlower2;
	private final ModelPart lefthand;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart rightleg;
	private final ModelPart rightleglower1;
	private final ModelPart rightleglower2;
	private final ModelPart rightfoot;
	private final ModelPart leftleg;
	private final ModelPart leftleglower1;
	private final ModelPart leftleglower2;
	private final ModelPart leftfoot;

	public SahuaginModel(ModelPart root) {
		this.root = root.getChild("sahuagin");
		this.head = this.root.getChild("head");
		this.headaccessory = this.root.getChild("headaccessory");
		this.hair1 = this.root.getChild("hair1");
		this.hair2 = this.root.getChild("hair2");
		this.rightear = this.root.getChild("rightear");
		this.leftear = this.root.getChild("leftear");
		this.bodytop = this.root.getChild("bodytop");
		this.rightarm = this.root.getChild("rightarm");
		this.rightarmlower1 = this.root.getChild("rightarmlower1");
		this.rightarmlower2 = this.root.getChild("rightarmlower2");
		this.righthand = this.root.getChild("righthand");
		this.leftarm = this.root.getChild("leftarm");
		this.leftarmlower1 = this.root.getChild("leftarmlower1");
		this.leftarmlower2 = this.root.getChild("leftarmlower2");
		this.lefthand = this.root.getChild("lefthand");
		this.tail1 = this.root.getChild("tail1");
		this.tail2 = this.root.getChild("tail2");
		this.tail3 = this.root.getChild("tail3");
		this.rightleg = this.root.getChild("rightleg");
		this.rightleglower1 = this.root.getChild("rightleglower1");
		this.rightleglower2 = this.root.getChild("rightleglower2");
		this.rightfoot = this.root.getChild("rightfoot");
		this.leftleg = this.root.getChild("leftleg");
		this.leftleglower1 = this.root.getChild("leftleglower1");
		this.leftleglower2 = this.root.getChild("leftleglower2");
		this.leftfoot = this.root.getChild("leftfoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition sahuagin = partdefinition.addOrReplaceChild("sahuagin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		sahuagin.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		sahuagin.addOrReplaceChild("headaccessory", CubeListBuilder.create()
				.texOffs(36, 0).addBox(-3.5F, -6.5F, -3.5F, 7.0F, 7.0F, 7.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		sahuagin.addOrReplaceChild("hair1", CubeListBuilder.create()
				.texOffs(36, 14).addBox(-4.0F, -6.0F, 1.0F, 8.0F, 8.0F, 3.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		sahuagin.addOrReplaceChild("hair2", CubeListBuilder.create()
				.texOffs(36, 25).addBox(-4.5F, -1.0F, 1.5F, 9.0F, 9.0F, 3.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		sahuagin.addOrReplaceChild("rightear", CubeListBuilder.create()
				.texOffs(36, 32).addBox(-4.0F, -5.0F, -1.0F, 0.0F, 4.0F, 5.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		sahuagin.addOrReplaceChild("leftear", CubeListBuilder.create()
				.texOffs(36, 32).mirror().addBox(4.0F, -5.0F, -1.0F, 0.0F, 4.0F, 5.0F).mirror(false), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		sahuagin.addOrReplaceChild("bodytop", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-2.5F, 0.0F, -1.5F, 5.0F, 5.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		sahuagin.addOrReplaceChild("bodymiddle", CubeListBuilder.create()
				.texOffs(0, 20).addBox(-2.0F, 4.5F, -1.5F, 4.0F, 3.0F, 2.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		sahuagin.addOrReplaceChild("bodymiddlebutton", CubeListBuilder.create()
				.texOffs(0, 20).addBox(-0.5F, 5.0F, -1.6F, 1.0F, 2.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		sahuagin.addOrReplaceChild("bodybottom", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-2.5F, 7.0F, -2.5F, 5.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		sahuagin.addOrReplaceChild("rightchest", CubeListBuilder.create()
				.texOffs(0, 31).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-1.3F, -21.0F, -1.5F, 0.7854F, 0.1745F, 0.0873F));

		sahuagin.addOrReplaceChild("leftchest", CubeListBuilder.create()
				.texOffs(8, 31).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(1.3F, -21.0F, -1.5F, 0.7854F, -0.1745F, -0.0873F));

		sahuagin.addOrReplaceChild("rightarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 0.0F, 0.0F, 0.0F, 0.2618F));

		sahuagin.addOrReplaceChild("rightarmlower1", CubeListBuilder.create()
				.texOffs(36, 41).addBox(-4.0F, 2.5F, 0.0F, 2.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 0.0F, 0.0F, 0.0F, 0.2618F));

		sahuagin.addOrReplaceChild("rightarmlower2", CubeListBuilder.create()
				.texOffs(36, 47).addBox(-2.5F, 4.0F, -1.5F, 2.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 0.0F, 0.0F, 0.0F, 0.2618F));

		sahuagin.addOrReplaceChild("righthand", CubeListBuilder.create()
				.texOffs(36, 56).addBox(-2.5F, 8.5F, -2.0F, 2.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 0.0F, 0.0F, 0.0F, 0.1745F));

		sahuagin.addOrReplaceChild("leftarm", CubeListBuilder.create()
				.texOffs(16, 12).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 10.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(2.5F, -21.5F, 0.0F, 0.0F, 0.0F, -0.2618F));

		sahuagin.addOrReplaceChild("leftarmlower1", CubeListBuilder.create()
				.texOffs(40, 41).addBox(2.0F, 2.5F, 0.0F, 2.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(2.5F, -21.5F, 0.0F, 0.0F, 0.0F, -0.2618F));

		sahuagin.addOrReplaceChild("leftarmlower2", CubeListBuilder.create()
				.texOffs(46, 47).addBox(0.5F, 4.0F, -1.5F, 2.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(2.5F, -21.5F, 0.0F, 0.0F, 0.0F, -0.2618F));

		sahuagin.addOrReplaceChild("lefthand", CubeListBuilder.create()
				.texOffs(48, 56).addBox(0.5F, 8.5F, -2.0F, 2.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(2.5F, -21.5F, 0.0F, 0.0F, 0.0F, -0.1745F));

		sahuagin.addOrReplaceChild("tail1", CubeListBuilder.create()
				.texOffs(64, 8).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 5.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -15.0F, 1.0F, 0.1745F, 0.0F, 0.0F));

		sahuagin.addOrReplaceChild("tail2", CubeListBuilder.create()
				.texOffs(64, 16).addBox(-1.0F, 3.5F, 0.5F, 2.0F, 5.0F, 2.0F), PartPose.offsetAndRotation(0.0F, -15.0F, 1.0F, 0.2618F, 0.0F, 0.0F));

		sahuagin.addOrReplaceChild("tail3", CubeListBuilder.create()
				.texOffs(64, 23).addBox(-0.5F, 8.5F, 0.5F, 1.0F, 4.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -15.0F, 1.0F, 0.3491F, 0.0F, 0.0F));

		sahuagin.addOrReplaceChild("waist", CubeListBuilder.create()
				.texOffs(64, 0).addBox(-3.0F, 9.0F, -2.0F, 6.0F, 4.0F, 4.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		sahuagin.addOrReplaceChild("rightleg", CubeListBuilder.create()
				.texOffs(64, 28).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F), PartPose.offset(-1.5F, -13.0F, 0.0F));

		sahuagin.addOrReplaceChild("rightleglower1", CubeListBuilder.create()
				.texOffs(64, 43).addBox(-3.0F, 5.0F, 0.0F, 2.0F, 6.0F, 0.0F), PartPose.offset(-1.5F, -13.0F, 0.0F));

		sahuagin.addOrReplaceChild("rightleglower2", CubeListBuilder.create()
				.texOffs(64, 49).addBox(-1.5F, 7.0F, -1.5F, 3.0F, 5.0F, 3.0F), PartPose.offset(-1.5F, -13.0F, 0.0F));

		sahuagin.addOrReplaceChild("rightfoot", CubeListBuilder.create()
				.texOffs(64, 57).addBox(-2.0F, 12.0F, -3.0F, 4.0F, 1.0F, 6.0F), PartPose.offset(-1.5F, -13.0F, 0.0F));

		sahuagin.addOrReplaceChild("leftleg", CubeListBuilder.create()
				.texOffs(72, 28).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F), PartPose.offset(1.5F, -13.0F, 0.0F));

		sahuagin.addOrReplaceChild("leftleglower1", CubeListBuilder.create()
				.texOffs(68, 43).addBox(1.0F, 5.0F, 0.0F, 2.0F, 6.0F, 0.0F), PartPose.offset(1.5F, -13.0F, 0.0F));

		sahuagin.addOrReplaceChild("leftleglower2", CubeListBuilder.create()
				.texOffs(64, 49).mirror().addBox(-1.5F, 7.0F, -1.5F, 3.0F, 5.0F, 3.0F).mirror(false), PartPose.offset(1.5F, -13.0F, 0.0F));

		sahuagin.addOrReplaceChild("leftfoot", CubeListBuilder.create()
				.texOffs(84, 57).addBox(-2.0F, 12.0F, -3.0F, 4.0F, 1.0F, 6.0F), PartPose.offset(1.5F, -13.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(SahuaginEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// head
		head.yRot = netHeadYaw / 57.295776F;
		head.xRot = headPitch / 57.295776F;
		headaccessory.yRot = head.yRot;
		headaccessory.xRot = head.xRot;
		hair1.yRot = head.yRot;
		hair2.yRot = head.yRot;
		rightear.yRot = head.yRot - 0.5235988F;
		rightear.xRot = head.xRot;
		leftear.yRot = head.yRot + 0.5235988F;
		leftear.xRot = head.xRot;

		// arms
		rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
		rightarmlower1.xRot = rightarm.xRot;
		rightarmlower2.xRot = rightarm.xRot;
		righthand.xRot = rightarm.xRot;
		leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;
		leftarmlower1.xRot = leftarm.xRot;
		leftarmlower2.xRot = leftarm.xRot;
		lefthand.xRot = leftarm.xRot;

		// tail
		tail1.zRot = Mth.cos(limbSwing * 0.6162F) * 0.1F * limbSwingAmount;
		tail2.zRot = Mth.cos(limbSwing * 0.6262F) * 0.1F * limbSwingAmount;
		tail3.zRot = Mth.cos(limbSwing * 0.6362F) * 0.1F * limbSwingAmount;

		// legs
		rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
		rightleglower1.xRot = rightleg.xRot;
		rightleglower2.xRot = rightleg.xRot;
		rightfoot.xRot = rightleg.xRot;
		leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount;
		leftleglower1.xRot = leftleg.xRot;
		leftleglower2.xRot = leftleg.xRot;
		leftfoot.xRot = leftleg.xRot;

		// melee attack animation
		if (attackTime > 0.0F) {
			holdingMelee();
		}
	}

	private void holdingMelee() {
		float f6 = 1.0F - attackTime;
		f6 *= f6;
		f6 *= f6;
		f6 = 1.0F - f6;
		float f7 = Mth.sin(f6 * (float) Math.PI);
		float f8 = Mth.sin(attackTime * (float) Math.PI) * -(head.xRot - 0.7F) * 0.75F;

		rightarm.xRot = (float) ((double) rightarm.xRot - ((double) f7 * 1.2D + (double) f8));
		rightarmlower1.xRot = rightarm.xRot;
		rightarmlower2.xRot = rightarm.xRot;
		rightarm.yRot += (bodytop.yRot * 2.0F);
		rightarmlower1.yRot = rightarm.yRot;
		rightarmlower2.yRot = rightarm.yRot;
		rightarm.zRot = (Mth.sin(attackTime * (float) Math.PI) * -0.4F) + 0.2617994F;
		rightarmlower1.zRot = rightarm.zRot;
		rightarmlower2.zRot = rightarm.zRot;
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
