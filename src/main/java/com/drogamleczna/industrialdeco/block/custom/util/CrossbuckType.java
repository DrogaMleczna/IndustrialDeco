package com.drogamleczna.industrialdeco.block.custom.util;

import net.minecraft.util.StringRepresentable;

public enum CrossbuckType implements StringRepresentable {
    POLAND("poland"),
    POLAND_MULTITRACK("poland_multitrack"),
    SWEDEN("sweden"),
    SWEDEN_MULTITRACK("sweden_multitrack"),
    CANADA("canada")
    ;
    private String name;
    private CrossbuckType(String pName){
        name = pName;
    }
    @Override
    public String getSerializedName() {
        return name;
    }
}
