package com.drogamleczna.industrialdeco.datagen;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IndustrialDeco.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CURVED_POLE.get())
                .add(ModBlocks.DOUBLE_CURVED_POLE.get())
                .add(ModBlocks.WIRE_POLE.get())
                .add(ModBlocks.QUADRUPLE_CURVED_POLE.get())
                .add(ModBlocks.POLE_BASE.get())
                .add(ModBlocks.LARGE_BASE.get())
                .add(ModBlocks.POLE_BLOCK.get())
                .add(ModBlocks.SPLIT_POLE.get())
                .add(ModBlocks.SWITCHBOARD.get())
                .add(ModBlocks.WALL_SWITCHBOARD.get())
                .add(ModBlocks.STREET_LAMP.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.STREET_LAMP.get())
                .add(ModBlocks.CURVED_POLE.get())
                .add(ModBlocks.DOUBLE_CURVED_POLE.get())
                .add(ModBlocks.WIRE_POLE.get())
                .add(ModBlocks.POLE_BASE.get())
                .add(ModBlocks.LARGE_BASE.get())
                .add(ModBlocks.POLE_BLOCK.get())
                .add(ModBlocks.SPLIT_POLE.get())
                .add(ModBlocks.SWITCHBOARD.get())
                .add(ModBlocks.WALL_SWITCHBOARD.get())
                .add(ModBlocks.QUADRUPLE_CURVED_POLE.get());

    }
}