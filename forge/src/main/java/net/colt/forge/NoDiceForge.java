package net.colt.forge;

import dev.architectury.platform.forge.EventBuses;
import net.colt.NoDice;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NoDice.MOD_ID)
public class NoDiceForge {
    public NoDiceForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(NoDice.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        NoDice.init();
    }
}
