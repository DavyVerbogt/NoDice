package net.colt.nodice.client.gui;

import net.colt.nodice.NoDice;
import net.colt.nodice.client.gui.elements.StatsButton;
import net.colt.nodice.client.gui.elements.DiceToast;
import net.colt.nodice.client.gui.elements.PlayerRenderer;
import net.colt.nodice.config.PlayerStatsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;

import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class DiceGUI extends Screen {
    public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(NoDice.MOD_ID, "textures/gui/sheet.png");

    private final int windowWidth;
    private final int windowHeight;
    private int windowLeft;
    private int windowTop;
    private final String[] StatTypes = {"str", "dex", "con", "int", "wis", "cha"};

    private Button D20Button;
    private ArrayList<StatsButton> StatButtons = new ArrayList<StatsButton>();

    private static DiceGUI instance = null;
    protected DiceGUI() {
        super(Component.nullToEmpty(PlayerStatsConfig.CharachterName.get()));
        this.windowWidth = 600;
        this.windowHeight = 300;

    }

    @Override
    protected void init() {
        super.init();

        this.windowLeft = (this.width - this.windowWidth) / 2;
        this.windowTop = (this.height - this.windowHeight) / 2;

        DiceButtons();
        StatButtons();
    }

    private void DiceButtons(){
    this.D20Button = this.addRenderableWidget(Button.builder(Component.translatable("nodice:d20"),onPress -> {
rollDice(20,1,1);
    }).pos(this.windowLeft+10,this.windowTop+100).size(40,30).tooltip(Tooltip.create(Component.translatable("nodice:d20tooltip"))).build());
    }

    private void StatButtons() {
        for (int i = 0; i < StatTypes.length; i++) {
            this.StatButtons.add(new StatsButton(this.windowLeft + 100 + (i * 60), this.windowTop + 10,Component.translatable("gui." + NoDice.MOD_ID + "." + StatTypes[i] + "_button"), onPress -> {
                rollDice(20, 1, 1);
            },StatTypes[i]));
            this.addRenderableWidget(this.StatButtons.get(i));
        }
    }

    private void rollDice(int DiceType, int DiceAmount, int Modifier)
    {
        minecraft.getToasts().addToast(new DiceToast("Rolling d"+DiceType+": " + (int) ((Math.random() * (DiceType - 1)) + 1)));
        Minecraft.getInstance().player.sendSystemMessage(Component.nullToEmpty(PlayerStatsConfig.CharachterName.get()+"Rolled a D"+DiceType+": "+ (int) ((Math.random() * (DiceType - 1)) + 1)));
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(guiGraphics);

        guiGraphics.blit(GUI_TEXTURE, this.windowLeft, this.windowTop, 0, 0, this.windowWidth, this.windowHeight,this.windowWidth,this.windowHeight);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        if(this.minecraft.player != null)
        {
            guiGraphics.enableScissor(this.windowLeft+10, this.windowTop+20, this.windowLeft+89, this.windowTop+84);
            PlayerRenderer.renderEntityInInventory(this.windowLeft+50, this.windowTop+135, 55, 0,
                    (float) ((this.windowLeft +45) - mouseX) / 2,
                    (float) ((this.windowTop+80) - mouseY) / 6,
                    this.minecraft.player);
            guiGraphics.disableScissor();
        }


        guiGraphics.drawString(this.font, this.title, this.windowLeft + 8, this.windowTop + 8, 4210752, false);

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static DiceGUI getInstance() {
        if (instance == null) {
            instance = new DiceGUI();
        }

        return instance;
    }

}
