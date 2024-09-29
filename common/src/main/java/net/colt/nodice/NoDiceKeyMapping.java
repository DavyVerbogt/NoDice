package net.colt.nodice;

import dev.architectury.event.events.client.ClientTickEvent;
import net.colt.nodice.client.gui.CharachterSheetGui;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class NoDiceKeyMapping {


    public static KeyMapping KeyGui =  new KeyMapping(
                "key.nodice.open_gui",
                GLFW.GLFW_KEY_HOME,
                "NoDice");


    public static void KeyAction()
    {
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            while (NoDiceKeyMapping.KeyGui.consumeClick()) {
                if (Minecraft.getInstance().player != null) {
                    CharachterSheetGui gui = CharachterSheetGui.getInstance();
                    Minecraft.getInstance().setScreen(gui);
                    NoDice.LOGGER.info(NoDice.MOD_ID + " Dice Key has been pressed");
                }
            }
        });
    }
    private NoDiceKeyMapping() {
    }

}
