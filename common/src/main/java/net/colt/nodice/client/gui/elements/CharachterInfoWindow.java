package net.colt.nodice.client.gui.elements;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.math.*;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import imgui.ImGui;
import imgui.ImGuiViewport;
import net.colt.nodice.NoDice;
import net.colt.nodice.client.gui.imgui.FrameBuffer;
import net.colt.nodice.client.gui.imgui.ImGuiRenderer;
import net.colt.nodice.client.gui.imgui.PlayerRenderer;
import net.colt.nodice.config.PlayerStatsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.SkullBlock;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import org.joml.Quaternionf;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.LivingEntity;
import org.lwjgl.opengl.GL30;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.client.renderer.MultiBufferSource.immediateWithBuffers;


public class CharachterInfoWindow {

    private static final FrameBuffer buffer = new FrameBuffer();
    private static int previewSize = 100;
    private static boolean isPreviewInitialized;

    private static float mousePosX;
    private static float mousePosY;
    private static int windowPosX;
    private static int windowPosY;

    public static void render() {
        if (ImGui.begin("Charachter Info")) {
            ImGui.text("Name: " + PlayerStatsConfig.CharachterName.get());
            ImGui.text("Race: " + PlayerStatsConfig.Race.get());
            ImGui.text("Level: " + PlayerStatsConfig.Level.get());
            if (PlayerStatsConfig.SubRace.get() == null) {
                ImGui.text("Subrace: " + PlayerStatsConfig.SubRace.get());
            }
            ImGui.text("Class: " + PlayerStatsConfig.Class.get());
            if (PlayerStatsConfig.SubClass.get() == null) {
                ImGui.text("Subclass: " + PlayerStatsConfig.SubClass.get());
            }
            ImGui.separator();
            updateSize();
            ImGui.image(buffer.getTexture(),previewSize,previewSize);
        }
        ImGui.end();
    }


    private static void updateSize() {
        int previousPreviewSize;
        previousPreviewSize = previewSize;
        previewSize = ((int) Math.min(ImGui.getContentRegionAvailX(), ImGui.getContentRegionAvailY()));
        mousePosX = ImGui.getMousePosX();
        mousePosY = ImGui.getMousePosY();
        windowPosX = (int) ImGui.getWindowPosX();
        windowPosY = (int) ImGui.getWindowPosY();


        if (!isPreviewInitialized || previewSize != previousPreviewSize) {
            buffer.update(previewSize, previewSize);
            isPreviewInitialized = true;
        }

    }

    public static void renderCharachter() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) {
            return;
        }
        buffer.bind();
        GL30.glClearColor(1,1,1,0.2f);
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
                PlayerRenderer.renderEntityInInventory(0, 30, 50, 0, getMousePosX(), getMousePosY(), player);
        buffer.unbind();
    }

    public static int getPreviewSize() {
        return previewSize;
    }

    public static float getMousePosX() {
        return mousePosX;
    }

    public static float getMousePosY() {
        return mousePosY;
    }

    public static int getWindowPosX() {
        return windowPosX;
    }

    public static int getWindowPosY() {
        return windowPosY;
    }
}
