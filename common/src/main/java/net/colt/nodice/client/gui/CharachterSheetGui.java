package net.colt.nodice.client.gui;

import imgui.ImFontConfig;
import imgui.ImGui;
import net.colt.nodice.client.gui.elements.CharachterInfoWindow;
import net.colt.nodice.client.gui.elements.GMToolsWindow;
import net.colt.nodice.client.gui.imgui.ImGuiRenderer;
import net.colt.nodice.config.ImguiConfig;
import net.colt.nodice.config.PlayerStatsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;

public class CharachterSheetGui extends Screen {
    private static CharachterSheetGui instance;
    private CharachterSheetGui() {
        super(Component.literal("Charachter Sheet"));
    }

    public void init() {
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks)
    {
        ImGuiRenderer.getInstance().draw(() -> {
            if (PlayerStatsConfig.GameMaster.get()) {
                GMToolsWindow.render();
            }

            CharachterInfoWindow.render();
        });

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
