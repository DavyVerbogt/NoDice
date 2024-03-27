package net.colt;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class NoDiceKeyMapping {


    public static KeyMapping KeyGui =  new KeyMapping(
                "key.nodice.open_gui",
                GLFW.GLFW_KEY_HOME,
                "NoDice");

    private NoDiceKeyMapping() {
    }

}
