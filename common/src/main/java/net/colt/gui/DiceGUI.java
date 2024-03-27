package net.colt.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class DiceGUI extends Screen {
    private static DiceGUI instance = null;
    protected DiceGUI() {
        super(Component.translatable("nodice:dicegui"));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {

    }

    @Override
    public void onClose() {
        super.onClose();
    }

    public static DiceGUI getInstance() {
        if (instance == null) {
            instance = new DiceGUI();
        }

        return instance;
    }
}
