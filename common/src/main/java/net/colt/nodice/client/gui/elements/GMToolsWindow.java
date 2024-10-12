package net.colt.nodice.client.gui.elements;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imgui.ImGui;
import imgui.type.ImInt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.debug.GameModeSwitcherScreen;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;



import java.util.Arrays;

public class GMToolsWindow {

    public static void render() {
        if (ImGui.begin("Game Master Tools")) {
            ImGui.sameLine();
            if (ImGui.beginListBox("GameMode Selection",115,200))
            {
                Arrays.stream(GameType.values()).forEach(type ->
                        {
                        if (ImGui.button(type.getName(),110,30))
                        {
                            if (Minecraft.getInstance().player.hasPermissions(2) && type != Minecraft.getInstance().gameMode.getPlayerMode() ) {
                                Minecraft.getInstance().player.connection.sendUnsignedCommand("gamemode "+type.getName());
                            }
                        }

                        });
                ImGui.endListBox();
                
            }
        }
        ImGui.end();
    }

}
