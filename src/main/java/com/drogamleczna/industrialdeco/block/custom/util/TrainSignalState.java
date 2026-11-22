package com.drogamleczna.industrialdeco.block.custom.util;

import net.minecraft.util.StringRepresentable;

public enum TrainSignalState implements StringRepresentable {
    OFF("off"),
    LEFT("left"),
    RIGHT("right"),
    ON("on")
    ;


    private final String name;
    TrainSignalState(String pName){
        name = pName;
    }
    @Override
    public String getSerializedName() {
        return name;
    }
}
