package net.colt.nodice.fabric;

import net.colt.nodice.NoDice;
import net.fabricmc.api.ModInitializer;

public class NoDiceFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NoDice.init();
    }
}
