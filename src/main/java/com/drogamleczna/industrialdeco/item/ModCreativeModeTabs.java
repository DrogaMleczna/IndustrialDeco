package com.drogamleczna.industrialdeco.item;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IndustrialDeco.MODID);

    public static final RegistryObject<CreativeModeTab> INDUSTRIAL_DECO_TAB = CREATIVE_MODE_TABS.register("industrial_deco_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.STREET_LAMP.get()))
                    .title(Component.translatable("creativetab.industrial_deco_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.STREET_LAMP.get());
                        pOutput.accept(ModBlocks.POLE_BLOCK.get());
                        pOutput.accept(ModBlocks.CURVED_POLE.get());
                        pOutput.accept(ModBlocks.SPLIT_POLE.get());
                        pOutput.accept(ModBlocks.DOUBLE_CURVED_POLE.get());
                        pOutput.accept(ModBlocks.QUADRUPLE_CURVED_POLE.get());
                        pOutput.accept(ModBlocks.WIRE_POLE.get());
                        pOutput.accept(ModBlocks.POLE_BASE.get());
                        pOutput.accept(ModBlocks.LARGE_BASE.get());
                        pOutput.accept(ModBlocks.SWITCHBOARD.get());
                        pOutput.accept(ModBlocks.WALL_SWITCHBOARD.get());

                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
