package net.colt.nodice.fabric;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.colt.nodice.NoDice;
import net.colt.nodice.config.PlayerStatsConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.config.ModConfig;

public class NoDiceFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(NoDice.MOD_ID, ModConfig.Type.SERVER, PlayerStatsConfig.SPEC,"nodice/player_stats.toml");
        NoDice.init();
    }
}
