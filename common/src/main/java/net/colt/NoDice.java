package net.colt;

import com.google.common.base.Suppliers;
import com.mojang.logging.LogUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientScreenInputEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import dev.architectury.registry.registries.RegistrarManager;
import net.colt.gui.DiceGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.slf4j.Logger;
import java.util.function.Supplier;
import static dev.architectury.event.EventResult.pass;

public class NoDice {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "nodice";
    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static void openGui() {
        Minecraft.getInstance().setScreen(DiceGUI.getInstance());
    }

    private static EventResult onKeyPressed(Minecraft minecraft, Screen screen, int i, int i1, int i2)
    {
        if (NoDiceKeyMapping.KeyGui.consumeClick()){openGui();}
        return pass();
    }

    public static void init() {
        ClientScreenInputEvent.KEY_PRESSED_PRE(NoDice::openGui);
        KeyMappingRegistry.register(NoDiceKeyMapping.KeyGui);
    }

}
