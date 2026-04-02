package com.drogamleczna.industrialdeco.block.custom.util;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum CrossbuckType implements StringRepresentable {
    POLAND("poland"),
    POLAND_MULTITRACK("poland_multitrack"),
    SWEDEN("sweden"),
    SWEDEN_MULTITRACK("sweden_multitrack"),
    BELGIUM("belgium"),
    BELGIUM_MULTITRACK("belgium_multitrack"),
    EUROPE("europe"),
    EUROPE_MULTITRACK("europe_multitrack"),
    CANADA("canada"),
    JAPAN("japan")
    ;
    private final String name;
    CrossbuckType(String pName){
        name = pName;
    }
    @Override
    public @NotNull String getSerializedName() {
        return name;
    }
}
