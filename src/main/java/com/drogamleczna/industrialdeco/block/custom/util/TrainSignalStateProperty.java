package com.drogamleczna.industrialdeco.block.custom.util;

import com.google.common.collect.Lists;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TrainSignalStateProperty extends EnumProperty<TrainSignalState> {
    protected TrainSignalStateProperty(String name, Collection<TrainSignalState> values) {
        super(name, TrainSignalState.class, values);
    }

    public static TrainSignalStateProperty create(String pName) {
        return create(pName, (Predicate)((p_187558_) -> true));
    }

    public static TrainSignalStateProperty create(String pName, Predicate<TrainSignalState> pFilter) {
        return create(pName, Arrays.stream(TrainSignalState.values()).filter(pFilter).collect(Collectors.toList()));
    }

    public static TrainSignalStateProperty create(String pName, TrainSignalState... pValues) {
        return create(pName, Lists.newArrayList(pValues));
    }

    public static TrainSignalStateProperty create(String pName, Collection<TrainSignalState> pValues) {
        return new TrainSignalStateProperty(pName, pValues);
    }
}
