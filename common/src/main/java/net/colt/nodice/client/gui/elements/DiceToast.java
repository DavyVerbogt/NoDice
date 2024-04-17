package net.colt.nodice.client.gui.elements;

import net.colt.nodice.client.gui.DiceGUI;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.util.Bytecode;

public class DiceToast implements Toast {

    private static final Component TITLE = Component.translatable("nodice:rolling_dice").withStyle(ChatFormatting.YELLOW);

    public String result;

    public DiceToast( String result)
    {
        this.result = result;
    }
    @Override
    public Visibility render(GuiGraphics graphics, ToastComponent gui, long delta)
    {
        graphics.blit(TEXTURE, 0, 0, 0, 0, 160, 32);
        graphics.drawString(gui.getMinecraft().font, TITLE, 35, 7, 0xFFFFFF, false);
        graphics.drawString(gui.getMinecraft().font, this.result, 35, 18, 0xFFFFFF, false);
        return delta >= 5000L ? Visibility.HIDE : Visibility.SHOW;
    }
}
