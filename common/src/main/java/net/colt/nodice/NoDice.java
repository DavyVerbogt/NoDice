package net.colt.nodice;

import com.mojang.logging.LogUtils;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;

import net.minecraft.resources.*;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;

public class NoDice {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "nodice";
    public static Path configDirectory;
    public static void init() {
        KeyMappingRegistry.register(NoDiceKeyMapping.KeyGui);
        NoDiceKeyMapping.KeyAction();
    }

    public static Path getConfigDirectory() {

        if (configDirectory == null) {
            configDirectory = Platform.getConfigFolder();
            try {
                Files.createDirectories(configDirectory);
            } catch (Exception e) {
                LOGGER.error("Unable to create directories for config folder", e);
            }
        }
        return configDirectory;
    }

}
