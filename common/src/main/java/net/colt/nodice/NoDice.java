package net.colt.nodice;

import com.google.common.base.Suppliers;
import com.mojang.logging.LogUtils;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import dev.architectury.registry.registries.RegistrarManager;
import net.colt.nodice.client.gui.DiceGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.function.Supplier;
import static dev.architectury.event.EventResult.pass;

public class NoDice {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "nodice";
    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));


    public static void init() {
        @
        KeyMappingRegistry.register(NoDiceKeyMapping.KeyGui);
        NoDiceKeyMapping.KeyAction();

    }

}
