package net.colt.nodice.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import net.colt.nodice.NoDice;
import net.colt.nodice.client.gui.imgui.ImGuiRenderer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public abstract class MixinRenderSystem {

    @Inject(at = @At("TAIL"), method = "initRenderer(IZ)V", remap = false)
    private static void initRenderer(int flags, boolean enable, CallbackInfo cbi) {
        RenderSystem.assertOnRenderThread();
        NoDice.LOGGER.info("Initalizing ImGui");
        ImGuiRenderer.getInstance().init(() -> {ImGui.getIO().addConfigFlags(ImGuiConfigFlags.DockingEnable);});
    }
    @Inject(at = @At("HEAD"), method = "flipFrame(J)V",remap = false)
    private static void flipFrame(long p_69496_, CallbackInfo cbi) {
        RenderSystem.recordRenderCall(() -> {
            ImGuiRenderer.getInstance().render();
        });
    }
}
