package com.drogamleczna.industrialdeco.block.custom.util;

import com.google.common.collect.Lists;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WireBoxTypeProperty extends EnumProperty<WireBoxType> {
    protected WireBoxTypeProperty(String pName, Collection<WireBoxType> pValues) {
        super(pName, WireBoxType.class, pValues);
    }

    public static WireBoxTypeProperty create(String pName) {
        return create(pName, (Predicate)((p_187558_) -> true));
    }

    public static WireBoxTypeProperty create(String pName, Predicate<WireBoxType> pFilter) {
        return create(pName, Arrays.stream(WireBoxType.values()).filter(pFilter).collect(Collectors.toList()));
    }

    public static WireBoxTypeProperty create(String pName, WireBoxType... pValues) {
        return create(pName, Lists.newArrayList(pValues));
    }

    public static WireBoxTypeProperty create(String pName, Collection<WireBoxType> pValues) {
        return new WireBoxTypeProperty(pName, pValues);
    }

}