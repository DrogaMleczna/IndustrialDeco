package com.drogamleczna.industrialdeco.item;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.item.custom.AngleGrinderItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(IndustrialDeco.MODID);

    public static final DeferredItem<AngleGrinderItem> ANGLE_GRINDER = ITEMS.register("angle_grinder",
            () -> new AngleGrinderItem(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}











