package net.colt.nodice.client.gui.elements;


import com.mojang.blaze3d.systems.RenderSystem;
import net.colt.nodice.NoDice;
import net.colt.nodice.config.PlayerStatsConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class StatsButton extends Button {

    private ResourceLocation texture = new ResourceLocation(NoDice.MOD_ID, "textures/gui/statbutton.png");
    int textureY = 0;
    private static Component Stat_String = null;
    public StatsButton(int x, int y,  Component component, OnPress onPress) {
        super(x, y, 60, 60, Component.empty(), onPress, DEFAULT_NARRATION);

        Stat_String = component;

    }
    private int getTextureY() {
        int i = 0;
        if (!this.isActive()) {
            i = 2;
            this.setFocused(false);
        } else if (this.isHoveredOrFocused()) {
            i = 1;
        }
        return i * 60 ;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick)
    {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        graphics.blit(this.texture, this.getX(), this.getY(), 0, getTextureY(), this.width, this.height);

        graphics.drawCenteredString(Minecraft.getInstance().font, Stat_String , this.getX() + 30, this.getY() + 10, 0xFFFFFF);
        graphics.drawCenteredString(Minecraft.getInstance().font, PlayerStatsConfig.STRStat.get().toString(), this.getX() + 30, this.getY() + 25, 0xFFFFFF);
        graphics.drawCenteredString(Minecraft.getInstance().font, "0", this.getX() + 30, this.getY() + 43, 0xFFFFFF);

    }
}
