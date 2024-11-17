package com.drogamleczna.industrialdeco.block;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.custom.CurvedPoleBlock;
import com.drogamleczna.industrialdeco.block.custom.DoubleCurvedPoleBlock;
import com.drogamleczna.industrialdeco.block.custom.StreetLampBlock;
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
