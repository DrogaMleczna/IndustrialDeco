package com.drogamleczna.industrialdeco.block;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.custom.*;
import com.drogamleczna.industrialdeco.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final net.neoforged.neoforge.registries.DeferredRegister.Blocks BLOCKS =
            net.neoforged.neoforge.registries.DeferredRegister.createBlocks(IndustrialDeco.MODID);

    public static final DeferredBlock<Block> STREET_LAMP = registerBlock("street_lamp",
            () -> new StreetLampBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape().lightLevel((state) -> {return 15;})));

    public static final DeferredBlock<Block> CURVED_POLE = registerBlock("curved_pole",
            () -> new CurvedPoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> DOUBLE_CURVED_POLE = registerBlock("double_curved_pole",
            () -> new DoubleCurvedPoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> QUADRUPLE_CURVED_POLE = registerBlock("quadruple_curved_pole",
            () -> new QuadrupleCurvedPoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> CORNER_POLE = registerBlock("corner_pole",
            () -> new CornerPoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape().forceSolidOn()));
    public static final DeferredBlock<Block> WALL_CURVED_POLE = registerBlock("curved_wall_pole",
            () -> new WallCurvedPoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape().forceSolidOn()));
    public static final DeferredBlock<Block> WIRE_POLE = registerBlock("wire_pole",
            () -> new WirePoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> POLE_BASE = registerBlock("pole_base",
            () -> new PoleBaseBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> LARGE_BASE = registerBlock("large_base",
            () -> new LargeBaseBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> POLE_BLOCK = registerBlock("pole_block",
            () -> new PoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> HAZARD_POLE = registerBlock("hazard_pole",
            () -> new PoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> WARNING_POLE = registerBlock("warning_pole",
            () -> new PoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> CAMERA_POLE = registerBlock("camera_pole",
            () -> new PoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> CAMERA_POLE_CORNER = registerBlock("camera_pole_corner",
            () -> new PoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> CAMERA_POLE_TRIPLE = registerBlock("camera_pole_triple",
            () -> new PoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> CAMERA_POLE_QUADRUPLE = registerBlock("camera_pole_quadruple",
            () -> new PoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> SPLIT_POLE = registerBlock("split_pole",
            () -> new CurvedPoleBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> SWITCHBOARD = registerBlock("switchboard",
            () -> new SwitchboardBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> WALL_SWITCHBOARD = registerBlock("wall_switchboard",
            () -> new WallSwitchboardBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> MEDIUM_BASE = registerBlock("medium_pole_base",
            () -> new MediumPoleBaseBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> PALLET = registerBlock("pallet",
            () -> new PalletBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape().forceSolidOn()));
    public static final DeferredBlock<Block> SECURITY_CAMERA = registerBlock("security_camera",
            () -> new SecurityCameraBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> CHIMNEY_BLOCK = registerBlock("chimney_block",
            () -> new ChimneyBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> BENT_CHIMNEY = registerBlock("chimney_bent",
            () -> new BentChimneyBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> WALL_CHIMNEY = registerBlock("chimney_wall",
            () -> new WallChimneyBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> WIRE_BOX = registerBlock("wire_box",
            () -> new WireBoxBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> CEILING_OFFICE_LAMP = registerBlock("ceiling_office_lamp",
            () -> new CeilingBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape().lightLevel((state) -> {return 15;})));
    public static final DeferredBlock<Block> OFFICE_CEILING = registerBlock("office_ceiling",
            () -> new CeilingBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(2.0f).noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> BENCH = registerBlock("bench",
            () -> new ChairBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.0f).noOcclusion()
                    .dynamicShape()));

    public static final DeferredBlock<Block> CROSSBUCK_BLOCK = registerBlock("crossbuck_block",
            () -> new CrossbuckBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));
    public static final DeferredBlock<Block> METAL_FENCE_BLOCK = registerBlock("metal_fence_block",
            () -> new MetalFenceBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f).requiresCorrectToolForDrops().noOcclusion()
                    .dynamicShape()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private  static  <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}
