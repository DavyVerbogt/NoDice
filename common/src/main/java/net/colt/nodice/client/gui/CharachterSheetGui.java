package net.colt.nodice.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import imgui.ImGui;
import net.colt.nodice.client.gui.elements.CharachterInfoWindow;
import net.colt.nodice.client.gui.elements.GMToolsWindow;
import net.colt.nodice.client.gui.imgui.FrameBuffer;
import net.colt.nodice.client.gui.imgui.ImGuiRenderer;
import net.colt.nodice.client.gui.imgui.PlayerRenderer;
import net.colt.nodice.config.PlayerStatsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import org.joml.Quaternionf;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class CharachterSheetGui extends Screen {
    private static CharachterSheetGui instance;
    private static FrameBuffer buffer = new FrameBuffer();
    private CharachterSheetGui() {
        super(Component.literal("Charachter Sheet"));
    }

    public void init() {
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks)
    { CharachterInfoWindow.renderCharachter();
        ImGuiRenderer.getInstance().draw(() -> {
            if (PlayerStatsConfig.GameMaster.get()) {
                GMToolsWindow.render();
            }
             CharachterInfoWindow.render();
        });

        PlayerRenderer.renderEntityInInventory(CharachterInfoWindow.getWindowPosX(), CharachterInfoWindow.getWindowPosY()+100,50,0, CharachterInfoWindow.getMousePosX(),CharachterInfoWindow.getMousePosY(),Minecraft.getInstance().player);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static CharachterSheetGui getInstance() {
        if (instance == null) {
            instance = new CharachterSheetGui();
        }

        return instance;
    }

}
