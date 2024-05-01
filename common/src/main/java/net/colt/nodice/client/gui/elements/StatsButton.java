package net.colt.nodice.client.gui.elements;


import com.mojang.blaze3d.systems.RenderSystem;
import net.colt.nodice.NoDice;
import net.colt.nodice.config.PlayerStatsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class StatsButton extends Button {

    private ResourceLocation texture = new ResourceLocation(NoDice.MOD_ID, "textures/gui/statbutton.png");
    int textureY = 0;
    String stat;


    private Component Stat_String = null;
    public StatsButton(int x, int y,  Component component, OnPress onPress,String stat) {
        super(x, y, 60, 60, Component.empty(), onPress, DEFAULT_NARRATION);

        Stat_String = component;
        this.stat = stat;

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

    private int getStatInt(String stat)
    {
        switch (stat)
        {
            case "str":
                return PlayerStatsConfig.STRStat.get();
            case "dex":
                return PlayerStatsConfig.DEXStat.get();
            case "con":
                return PlayerStatsConfig.CONStat.get();
            case "int":
                return PlayerStatsConfig.INTStat.get();
            case "wis":
                return PlayerStatsConfig.WISStat.get();
            case "cha":
                return PlayerStatsConfig.CHAStat.get();
        }
        return 0;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick)
    {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        graphics.blit(this.texture, this.getX(), this.getY(), 0, getTextureY(), this.width, this.height);


        graphics.drawCenteredString(Minecraft.getInstance().font, Stat_String , this.getX() + 30, this.getY() + 10, 0xFFFFFF);
        graphics.drawCenteredString(Minecraft.getInstance().font, ""+getStatInt(stat), this.getX() + 30, this.getY() + 25, 0xFFFFFF);
        graphics.drawCenteredString(Minecraft.getInstance().font, String.valueOf((int) (Math.floor( getStatInt(stat) /2)-5)), this.getX() + 30, this.getY() + 43, 0xFFFFFF);

    }
}
