package com.drogamleczna.industrialdeco.util;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(IndustrialDeco.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> INDUSTRIAL_DECO_METAL_BLOCKS = tag("industrial_deco_metal_blocks");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(IndustrialDeco.MODID, name));
        }
    }
}
