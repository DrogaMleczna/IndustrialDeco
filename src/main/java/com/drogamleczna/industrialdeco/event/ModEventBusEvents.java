package com.drogamleczna.industrialdeco.event;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.entity.client.ModModelLayers;
import com.drogamleczna.industrialdeco.entity.client.SeatModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IndustrialDeco.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.SEAT_LAYER, SeatModel::createBodyLayer);
    }
}
