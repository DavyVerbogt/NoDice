package net.colt.nodice.forge;

import dev.architectury.platform.forge.EventBuses;
import net.colt.nodice.NoDice;
import net.colt.nodice.config.ImguiConfig;
import net.colt.nodice.config.PlayerStatsConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NoDice.MOD_ID)
public class NoDiceForge {
    public NoDiceForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(NoDice.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, PlayerStatsConfig.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ImguiConfig.SPEC);
        NoDice.init();
    }
}
