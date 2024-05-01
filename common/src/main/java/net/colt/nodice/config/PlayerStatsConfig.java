package net.colt.nodice.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.function.Function;

public class PlayerStatsConfig {

    public static ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> CharachterName;
    public static final ForgeConfigSpec.IntValue STRStat;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Charachter sheet");

        builder.comment("Name and descreption");
        String defauktName = "Insert Name Here";
        CharachterName = builder.define("Charachter Name", defauktName);
        builder.comment("Stats");
        int defaultStat = 10;
        STRStat = builder.defineInRange("Strength Stat", defaultStat, 0, 20);

        builder.pop();
        SPEC = builder.build();
    }
}
