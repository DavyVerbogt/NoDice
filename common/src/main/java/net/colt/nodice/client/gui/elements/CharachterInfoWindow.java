package net.colt.nodice.client.gui.elements;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import imgui.ImGui;
import net.colt.nodice.config.PlayerStatsConfig;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Map;


public class CharachterInfoWindow {


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
        }
        ImGui.end();
    }
}

