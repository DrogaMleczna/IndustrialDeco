package com.drogamleczna.industrialdeco.block.custom.util;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum WireBoxType implements StringRepresentable {
    B("b"),
    T("t"),
    R("r"),
    L("l"),
    BR("br"),
    BT("bt"),
    BL("bl"),
    TR("tr"),
    TL("tl"),
    BLR("blr"),
    BLT("blt"),
    BRT("brt"),
    TRL("trl")
    ;
    private final String name;
    WireBoxType(String pName){
        name = pName;
    }
    @Override
    public @NotNull String getSerializedName() {
        return name;
    }
}