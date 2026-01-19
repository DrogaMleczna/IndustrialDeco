package com.drogamleczna.industrialdeco.block.custom.util;

import com.google.common.collect.Lists;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CrossbuckTypeProperty extends EnumProperty<CrossbuckType> {
    protected CrossbuckTypeProperty(String pName, Collection<CrossbuckType> pValues) {
        super(pName, CrossbuckType.class, pValues);
    }

    public static CrossbuckTypeProperty create(String pName) {
        return create(pName, (Predicate)((p_187558_) -> true));
    }

    public static CrossbuckTypeProperty create(String pName, Predicate<CrossbuckType> pFilter) {
        return create(pName, Arrays.stream(CrossbuckType.values()).filter(pFilter).collect(Collectors.toList()));
    }

    public static CrossbuckTypeProperty create(String pName, CrossbuckType... pValues) {
        return create(pName, Lists.newArrayList(pValues));
    }

    public static CrossbuckTypeProperty create(String pName, Collection<CrossbuckType> pValues) {
        return new CrossbuckTypeProperty(pName, pValues);
    }

}
