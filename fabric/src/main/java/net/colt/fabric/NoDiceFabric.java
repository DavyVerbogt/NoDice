package net.colt.fabric;

import net.colt.NoDice;
import net.fabricmc.api.ModInitializer;

public class NoDiceFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NoDice.init();
    }
}
