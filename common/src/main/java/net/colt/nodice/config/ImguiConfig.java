package net.colt.nodice.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ImguiConfig {


    public static ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> Dyslexia;




    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("ImGui Config");
        builder.comment("Change ImGui settings");
        builder.comment(" ");
        Dyslexia = builder.define("Dyslexia", false);

        builder.pop();
        SPEC = builder.build();
    }
}
