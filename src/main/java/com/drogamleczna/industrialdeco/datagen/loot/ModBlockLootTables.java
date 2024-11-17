package com.drogamleczna.industrialdeco.datagen.loot;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.STREET_LAMP.get());
        this.dropSelf(ModBlocks.CURVED_POLE.get());
        this.dropSelf(ModBlocks.DOUBLE_CURVED_POLE.get());
        this.dropSelf(ModBlocks.QUADRUPLE_CURVED_POLE.get());
        this.dropSelf(ModBlocks.WIRE_POLE.get());
        this.dropSelf(ModBlocks.POLE_BASE.get());
        this.dropSelf(ModBlocks.LARGE_BASE.get());
        this.dropSelf(ModBlocks.POLE_BLOCK.get());
        this.dropSelf(ModBlocks.SPLIT_POLE.get());
        this.dropSelf(ModBlocks.SWITCHBOARD.get());
        this.dropSelf(ModBlocks.WALL_SWITCHBOARD.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
