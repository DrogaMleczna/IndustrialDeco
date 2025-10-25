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
                        pOutput.accept(ModBlocks.CEILING_OFFICE_LAMP.get());
                        pOutput.accept(ModBlocks.OFFICE_CEILING.get());
                        pOutput.accept(ModBlocks.STREET_LAMP.get());
                        pOutput.accept(ModBlocks.POLE_BLOCK.get());
                        pOutput.accept(ModBlocks.CURVED_POLE.get());
                        pOutput.accept(ModBlocks.WALL_CURVED_POLE.get());
                        pOutput.accept(ModBlocks.SPLIT_POLE.get());
                        pOutput.accept(ModBlocks.DOUBLE_CURVED_POLE.get());
                        pOutput.accept(ModBlocks.QUADRUPLE_CURVED_POLE.get());
                        pOutput.accept(ModBlocks.CORNER_POLE.get());
                        pOutput.accept(ModBlocks.WIRE_POLE.get());
                        pOutput.accept(ModBlocks.POLE_BASE.get());
                        pOutput.accept(ModBlocks.MEDIUM_BASE.get());
                        pOutput.accept(ModBlocks.LARGE_BASE.get());
                        pOutput.accept(ModBlocks.SWITCHBOARD.get());
                        pOutput.accept(ModBlocks.WALL_SWITCHBOARD.get());
                        pOutput.accept(ModBlocks.WIRE_BOX.get());
                        pOutput.accept(ModBlocks.WARNING_POLE.get());
                        pOutput.accept(ModBlocks.HAZARD_POLE.get());
                        pOutput.accept(ModBlocks.CROSSBUCK_BLOCK.get());
                        pOutput.accept(ModBlocks.METAL_FENCE_BLOCK.get());
                        pOutput.accept(ModBlocks.CAMERA_POLE.get());
                        pOutput.accept(ModBlocks.CAMERA_POLE_CORNER.get());
                        pOutput.accept(ModBlocks.CAMERA_POLE_TRIPLE.get());
                        pOutput.accept(ModBlocks.CAMERA_POLE_QUADRUPLE.get());
                        pOutput.accept(ModBlocks.SECURITY_CAMERA.get());
                        pOutput.accept(ModBlocks.PALLET.get());
                        pOutput.accept(ModBlocks.CHIMNEY_BLOCK.get());
                        pOutput.accept(ModBlocks.BENT_CHIMNEY.get());
                        pOutput.accept(ModBlocks.WALL_CHIMNEY.get());
                        pOutput.accept(ModBlocks.BENCH.get());


                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
