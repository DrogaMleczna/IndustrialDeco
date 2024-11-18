package com.drogamleczna.industrialdeco.block;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.custom.*;
import com.drogamleczna.industrialdeco.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialDeco.MODID);

    public static final RegistryObject<Block> STREET_LAMP = registerBlock("street_lamp",
            () -> new StreetLampBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape().lightLevel((state) -> {return 15;})));

    public static final RegistryObject<Block> CURVED_POLE = registerBlock("curved_pole",
            () -> new CurvedPoleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> DOUBLE_CURVED_POLE = registerBlock("double_curved_pole",
            () -> new DoubleCurvedPoleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> QUADRUPLE_CURVED_POLE = registerBlock("quadruple_curved_pole",
            () -> new QuadrupleCurvedPoleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> WIRE_POLE = registerBlock("wire_pole",
            () -> new WirePoleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> POLE_BASE = registerBlock("pole_base",
            () -> new PoleBaseBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> LARGE_BASE = registerBlock("large_base",
            () -> new LargeBaseBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> POLE_BLOCK = registerBlock("pole_block",
            () -> new PoleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> SPLIT_POLE = registerBlock("split_pole",
            () -> new CurvedPoleBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> SWITCHBOARD = registerBlock("switchboard",
            () -> new SwitchboardBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> WALL_SWITCHBOARD = registerBlock("wall_switchboard",
            () -> new WallSwitchboardBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final RegistryObject<Block> MEDIUM_BASE = registerBlock("medium_pole_base",
            () -> new MediumPoleBaseBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private  static  <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
