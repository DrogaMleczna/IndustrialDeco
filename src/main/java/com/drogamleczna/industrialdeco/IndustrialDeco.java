package com.drogamleczna.industrialdeco;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import com.drogamleczna.industrialdeco.entity.ModEntities;
import com.drogamleczna.industrialdeco.entity.client.SeatRenderer;
import com.drogamleczna.industrialdeco.item.ModCreativeModeTabs;
import com.drogamleczna.industrialdeco.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(IndustrialDeco.MODID)
public class IndustrialDeco
{
    public static final String MODID = "industrialdeco";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final TagKey<Item> WRENCH_TAG = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/wrench"));
    public IndustrialDeco(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModEntities.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.SEAT.get(), SeatRenderer::new);
        }
    }
}