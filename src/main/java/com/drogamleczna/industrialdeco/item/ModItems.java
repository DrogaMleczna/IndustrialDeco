package com.drogamleczna.industrialdeco.item;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(IndustrialDeco.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}











