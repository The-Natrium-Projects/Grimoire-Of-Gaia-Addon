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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.sodiumzh.gogaddon.entity.BaphometEntity;

@OnlyIn(Dist.CLIENT)
public class BaphometModel extends EntityModel<BaphometEntity> implements HeadedModel, ArmedModel {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headeyes;
	private final ModelPart headaccessory;
	private final ModelPart bodytop;
	private final ModelPart rightarm;
	private final ModelPart leftarm;
	private final ModelPart rightleg;
	private final ModelPart leftleg;
	private final ModelPart cloak1;
	private final ModelPart cloak2;
	private final ModelPart cloak3;

	public BaphometModel(ModelPart root) {
		this.root = root.getChild("baphomet");
		this.head = this.root.getChild("head");
		this.headeyes = this.root.getChild("headeyes");
		this.headaccessory = this.root.getChild("headaccessory");
		this.bodytop = this.root.getChild("bodytop");
		this.rightarm = this.root.getChild("rightarm");
		this.leftarm = this.root.getChild("leftarm");
		this.rightleg = this.root.getChild("rightleg");
		this.leftleg = this.root.getChild("leftleg");
		this.cloak1 = this.root.getChild("cloak1");
		this.cloak2 = this.root.getChild("cloak2");
		this.cloak3 = this.root.getChild("cloak3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition baphomet = partdefinition.addOrReplaceChild("baphomet", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = baphomet.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		baphomet.addOrReplaceChild("headeyes", CubeListBuilder.create()
				.texOffs(24, 0).addBox(-3.0F, -6.0F, -3.1F, 6.0F, 6.0F, 0.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		baphomet.addOrReplaceChild("headaccessory", CubeListBuilder.create()
				.texOffs(36, 0).addBox(-3.5F, -6.5F, -3.5F, 7.0F, 7.0F, 7.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		baphomet.addOrReplaceChild("neck", CubeListBuilder.create()
				.texOffs(0, 12).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		baphomet.addOrReplaceChild("bodytop", CubeListBuilder.create()
				.texOffs(0, 16).addBox(-2.5F, 0.0F, -1.5F, 5.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		baphomet.addOrReplaceChild("bodymiddle", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-2.0F, 4.5F, -1.5F, 4.0F, 3.0F, 2.0F), PartPose.offset(0.0F, -22.0F, 0.0F));

		baphomet.addOrReplaceChild("bodymiddlebutton", CubeListBuilder.create()
				.texOffs(0, 25).addBox(-0.5F, 5.0F, -1.6F, 1.0F, 2.0F, 0.0F), PartPose.offset(0.0F, -22.0F, 0.0F));

		baphomet.addOrReplaceChild("bodybottom", CubeListBuilder.create()
				.texOffs(0, 30).addBox(-3.0F, 8.0F, -2.5F, 6.0F, 3.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition rightarm = baphomet.addOrReplaceChild("rightarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offsetAndRotation(-2.5F, -21.5F, 0.0F, 0.0F, 0.0F, 0.1745F));

		rightarm.addOrReplaceChild("righthand", CubeListBuilder.create()
				.texOffs(64, 39).addBox(-2.5F, 6.0F, -1.5F, 3.0F, 5.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition leftarm = baphomet.addOrReplaceChild("leftarm", CubeListBuilder.create()
				.texOffs(16, 12).addBox(0.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offsetAndRotation(2.5F, -21.5F, 0.0F, 0.0F, 0.0F, -0.1745F));

		leftarm.addOrReplaceChild("lefthand", CubeListBuilder.create()
				.texOffs(76, 39).addBox(-0.5F, 6.0F, -1.5F, 3.0F, 5.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition rightleg = baphomet.addOrReplaceChild("rightleg", CubeListBuilder.create()
				.texOffs(24, 12).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 14.0F, 3.0F), PartPose.offset(-2.0F, -13.0F, -0.5F));

		rightleg.addOrReplaceChild("rightfoot", CubeListBuilder.create()
				.texOffs(92, 0).addBox(-2.0F, 5.0F, -1.5F, 4.0F, 8.0F, 4.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftleg = baphomet.addOrReplaceChild("leftleg", CubeListBuilder.create()
				.texOffs(24, 12).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 14.0F, 3.0F), PartPose.offset(2.0F, -13.0F, -0.5F));

		leftleg.addOrReplaceChild("leftfoot", CubeListBuilder.create()
				.texOffs(108, 0).addBox(-2.0F, 5.0F, -1.5F, 4.0F, 8.0F, 4.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		// Head children: horns
		head.addOrReplaceChild("righthorn1", CubeListBuilder.create()
				.texOffs(36, 34).addBox(-3.0F, -8.0F, 0.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("righthorn2", CubeListBuilder.create()
				.texOffs(36, 38).addBox(-4.0F, -9.0F, 1.0F, 2.0F, 2.0F, 4.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("righthorn3", CubeListBuilder.create()
				.texOffs(36, 44).addBox(-3.0F, -8.0F, 4.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("righthorn4", CubeListBuilder.create()
				.texOffs(36, 48).addBox(-1.5F, -6.5F, 4.5F, 1.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("lefthorn1", CubeListBuilder.create()
				.texOffs(36, 34).mirror().addBox(1.0F, -8.0F, 0.0F, 2.0F, 2.0F, 2.0F).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("lefthorn2", CubeListBuilder.create()
				.texOffs(36, 38).mirror().addBox(2.0F, -9.0F, 1.0F, 2.0F, 2.0F, 4.0F).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("lefthorn3", CubeListBuilder.create()
				.texOffs(36, 44).mirror().addBox(1.0F, -8.0F, 4.0F, 2.0F, 2.0F, 2.0F).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("lefthorn4", CubeListBuilder.create()
				.texOffs(36, 48).mirror().addBox(0.5F, -6.5F, 4.5F, 1.0F, 1.0F, 1.0F).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		// Head children: ears
		head.addOrReplaceChild("rightear", CubeListBuilder.create()
				.texOffs(36, 46).addBox(-4.0F, -5.0F, -1.0F, 0.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		head.addOrReplaceChild("leftear", CubeListBuilder.create()
				.texOffs(36, 46).mirror().addBox(4.0F, -5.0F, -1.0F, 0.0F, 4.0F, 4.0F).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		// Head children: hair
		head.addOrReplaceChild("righthairclip", CubeListBuilder.create()
				.texOffs(36, 14).addBox(-5.5F, -5.0F, 2.5333F, 3.0F, 5.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.1745F));

		head.addOrReplaceChild("lefthairclip", CubeListBuilder.create()
				.texOffs(48, 14).addBox(2.5F, -5.0F, 2.5F, 3.0F, 5.0F, 3.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, -0.1745F));

		head.addOrReplaceChild("righthair", CubeListBuilder.create()
				.texOffs(36, 22).addBox(-5.0F, -4.5F, 3.0F, 2.0F, 10.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.1745F));

		head.addOrReplaceChild("lefthair", CubeListBuilder.create()
				.texOffs(36, 22).addBox(3.0F, -4.5F, 3.0F, 2.0F, 10.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, -0.1745F));

		// Mantle, cloaks, waist
		baphomet.addOrReplaceChild("mantle", CubeListBuilder.create()
				.texOffs(64, 0).addBox(-5.0F, 0.0F, -0.5F, 10.0F, 3.0F, 3.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		baphomet.addOrReplaceChild("mantlefront", CubeListBuilder.create()
				.texOffs(64, 6).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 3.0F, 3.0F), PartPose.offset(0.0F, -23.0F, 0.0F));

		baphomet.addOrReplaceChild("cloak1", CubeListBuilder.create()
				.texOffs(64, 12).addBox(-4.5F, 1.0F, -0.5F, 9.0F, 4.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		baphomet.addOrReplaceChild("cloak2", CubeListBuilder.create()
				.texOffs(64, 19).addBox(-5.0F, 4.5F, 0.5F, 10.0F, 6.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		baphomet.addOrReplaceChild("cloak3", CubeListBuilder.create()
				.texOffs(64, 28).addBox(-5.5F, 10.0F, 2.0F, 11.0F, 8.0F, 3.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		baphomet.addOrReplaceChild("waist", CubeListBuilder.create()
				.texOffs(64, 47).addBox(-3.5F, 7.5F, -3.0F, 7.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -23.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(BaphometEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.HEAD);

		// head
		head.yRot = netHeadYaw / 57.295776F;
		head.xRot = headPitch / 57.295776F;
		headeyes.yRot = head.yRot;
		headeyes.xRot = head.xRot;
		headaccessory.yRot = head.yRot;
		headaccessory.xRot = head.xRot;

		headeyes.visible = entity.tickCount % 60 == 0 && limbSwingAmount <= 0.1F;

		// arms
		if (itemstack.isEmpty()) {
			rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;
			leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;

			rightarm.zRot = 0.0F;
			leftarm.zRot = 0.0F;

			if (attackTime > 0.0F) {
				holdingMelee();
			}

			rightarm.zRot += (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
			rightarm.xRot += Mth.sin(ageInTicks * 0.067F) * 0.025F;
			leftarm.zRot -= (Mth.cos(ageInTicks * 0.09F) * 0.025F + 0.025F) + 0.1745329F;
			leftarm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.025F;
		}

		if (itemstack.is(Items.ARROW)) {
			animationThrow();
		}

		// body
		cloak1.zRot = Mth.cos(limbSwing * 0.6162F) * 0.1F * limbSwingAmount;
		cloak2.zRot = Mth.cos(limbSwing * 0.6262F) * 0.1F * limbSwingAmount;
		cloak3.zRot = Mth.cos(limbSwing * 0.6362F) * 0.1F * limbSwingAmount;

		// legs
		rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
		rightleg.yRot = 0.0F;
		leftleg.yRot = 0.0F;
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

	private void animationThrow() {
		rightarm.xRot = -1.0472F;
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
		getArm(arm).translateAndRotate(poseStack);
	}
}
