package net.colt.nodice.client.gui.imgui;

import imgui.ImFontAtlas;
import imgui.ImFontConfig;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.colt.nodice.NoDice;
import net.colt.nodice.client.gui.CharachterSheetGui;
import net.colt.nodice.config.ImguiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImGuiRenderer {
    private static ImGuiRenderer instance = null;
    private static ImGuiIO imGuiIo = null;

    public static ImGuiRenderer getInstance() {
        if (instance == null) instance = new ImGuiRenderer();
        return instance;
    }

    private List<ImGuiDrawCall> preDrawCalls = new ArrayList<ImGuiDrawCall>();
    private List<ImGuiDrawCall> drawCalls = new ArrayList<ImGuiDrawCall>();
    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl = new ImGuiImplGl3();

    private ImGuiRenderer() {
        // TODO Auto-generated constructor stub
    }

    public void init() {
        init(() -> {
        });
    }

    public void init(ImGuiDrawCall config) {
        ImGui.createContext();

        config.execute();
        imGuiGlfw.init(Minecraft.getInstance().getWindow().getWindow(), false);
        imGuiGl.init();
    }

    public void preDraw(ImGuiDrawCall drawCall) {
        preDrawCalls.add(drawCall);
    }

    public void draw(ImGuiDrawCall drawCall) {
        drawCalls.add(drawCall);
    }

    public void render() {

        for (ImGuiDrawCall preDrawCall : preDrawCalls) {
            preDrawCall.execute();
        }
        preDrawCalls.clear();

        initFont(ImguiConfig.Dyslexia.get() ? "opendyslexic-regular.otf":"silkscreen-regular.ttf", ImguiConfig.Dyslexia.get() ?25:20);
        imGuiGlfw.newFrame();
        ImGui.newFrame();

        // Render ImGui Here
        for (ImGuiDrawCall drawCall : drawCalls) {
            drawCall.execute();
        }
        drawCalls.clear();

        ImGui.render();
        imGuiGl.renderDrawData(Objects.requireNonNull(ImGui.getDrawData()));

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = GLFW.glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    private void initFont(String fontName, int fontSize) {

        // Setup ImGui font
        ImGuiIO io = ImGui.getIO();
        ImFontAtlas fonts = io.getFonts();
        final ImFontConfig FontConfig = new ImFontConfig();
        fonts.clear();
        FontConfig.setName(fontName + ", " + fontSize+"px");
        fonts.addFontFromMemoryTTF(loadFont(fontName), fontSize, FontConfig);
        fonts.build();
        imGuiGl.updateFontsTexture();
        FontConfig.destroy();
    }

    private static byte[] loadFont(String name) {
        Resource font = Minecraft.getInstance().getResourceManager().getResource(new ResourceLocation(NoDice.MOD_ID, "font/" + name)).get();
        try {
            return font.open().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

