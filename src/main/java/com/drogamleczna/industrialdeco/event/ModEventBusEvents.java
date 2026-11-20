package com.drogamleczna.industrialdeco.event;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.entity.client.ModModelLayers;
import com.drogamleczna.industrialdeco.entity.client.SeatModel;

import com.drogamleczna.industrialdeco.item.ModItems;
import com.drogamleczna.industrialdeco.item.custom.AngleGrinderClientExtensions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = IndustrialDeco.MODID)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.SEAT_LAYER, SeatModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new AngleGrinderClientExtensions(), ModItems.ANGLE_GRINDER);
    }
}
