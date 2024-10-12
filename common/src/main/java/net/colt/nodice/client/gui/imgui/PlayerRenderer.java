package net.colt.nodice.client.gui.imgui;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import org.joml.Quaternionf;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class PlayerRenderer {

    public static void renderEntityInInventory(int entityPosLeft, int entityPosTop, int size, int rotatePlayer,
                                               float MouseLookX, float MouseLookY, LivingEntity Player) {
        float f = (float) Math.atan(MouseLookX / 40.0F);
        float f1 = (float) Math.atan(MouseLookY / 40.0F);
        renderEntityInInventoryRaw(entityPosLeft, entityPosTop, size, rotatePlayer, f, f1, Player);
    }

    public static void renderEntityInInventoryRaw(int entityPosLeft, int entityPosTop, int size, int rotatePlayer,
                                                  float angleXComponent, float angleYComponent, LivingEntity Player) {
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate(entityPosLeft, entityPosTop, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float) size, (float) size, (float) size);
        Quaternionf quaternion = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf quaternion1 = new Quaternionf();

        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);
        float f2 = Player.yBodyRot;
        float f3 = Player.getYRot();
        float f4 = Player.getXRot();
        float f5 = Player.yHeadRotO;
        float f6 = Player.yHeadRot;

        Player.yBodyRot = rotatePlayer + 180 + angleXComponent * 20.0F;
        Player.setYRot(rotatePlayer + 180 + angleXComponent * 40.0F);

        Player.setXRot(-angleYComponent * 20.0F);
        Player.yHeadRot = Player.getYRot();
        Player.yHeadRotO = Player.getYRot();
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conjugate();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers()
                .bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(Player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1,
                    multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        Player.yBodyRot = f2;
        Player.setYRot(f3);
        Player.setXRot(f4);
        Player.yHeadRotO = f5;
        Player.yHeadRot = f6;
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }

}