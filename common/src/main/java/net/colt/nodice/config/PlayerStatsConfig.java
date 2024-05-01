package net.colt.nodice.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.function.Function;

public class PlayerStatsConfig {

    public static ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> CharachterName;
    public static final ForgeConfigSpec.IntValue STRStat;
    public static final ForgeConfigSpec.IntValue DEXStat;
    public static final ForgeConfigSpec.IntValue CONStat;
    public static final ForgeConfigSpec.IntValue INTStat;
    public static final ForgeConfigSpec.IntValue WISStat;
    public static final ForgeConfigSpec.IntValue CHAStat;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Charachter sheet");

        builder.comment("Name and descreption");
        String defauktName = "Insert Name Here";
        CharachterName = builder.define("Charachter Name", defauktName);
        builder.comment("Stats");
        int defaultStat = 10;
        STRStat = builder.defineInRange("Strength Stat", defaultStat, 0, 20);
        DEXStat = builder.defineInRange("Dexterity Stat", defaultStat, 0, 20);
        CONStat = builder.defineInRange("Constitution Stat", defaultStat, 0, 20);
        INTStat = builder.defineInRange("Intelligence Stat", defaultStat, 0, 20);
        WISStat = builder.defineInRange("Wisdom Stat", defaultStat, 0, 20);
        CHAStat = builder.defineInRange("Charisma Stat", defaultStat, 0, 20);


        builder.pop();
        SPEC = builder.build();
    }
}
