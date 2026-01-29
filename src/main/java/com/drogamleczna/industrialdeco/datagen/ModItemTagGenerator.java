package com.drogamleczna.industrialdeco.datagen;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.ModBlocks;
import com.drogamleczna.industrialdeco.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                               CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, IndustrialDeco.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.INDUSTRIAL_DECO_METAL_BLOCKS)
                .add(ModBlocks.CURVED_POLE.get().asItem())
                .add(ModBlocks.DOUBLE_CURVED_POLE.get().asItem())
                .add(ModBlocks.QUADRUPLE_CURVED_POLE.get().asItem())
                .add(ModBlocks.CORNER_POLE.get().asItem())
                .add(ModBlocks.WALL_CURVED_POLE.get().asItem())
                .add(ModBlocks.WIRE_POLE.get().asItem())
                .add(ModBlocks.POLE_BASE.get().asItem())
                .add(ModBlocks.LARGE_BASE.get().asItem())
                .add(ModBlocks.POLE_BLOCK.get().asItem())
                .add(ModBlocks.HAZARD_POLE.get().asItem())
                .add(ModBlocks.WARNING_POLE.get().asItem())
                .add(ModBlocks.CAMERA_POLE_TRIPLE.get().asItem())
                .add(ModBlocks.CAMERA_POLE_QUADRUPLE.get().asItem())
                .add(ModBlocks.CAMERA_POLE.get().asItem())
                .add(ModBlocks.CAMERA_POLE_CORNER.get().asItem())
                .add(ModBlocks.SPLIT_POLE.get().asItem())
                .add(ModBlocks.SWITCHBOARD.get().asItem())
                .add(ModBlocks.WALL_SWITCHBOARD.get().asItem())
                .add(ModBlocks.MEDIUM_BASE.get().asItem())
                .add(ModBlocks.SECURITY_CAMERA.get().asItem())
                .add(ModBlocks.CHIMNEY_BLOCK.get().asItem())
                .add(ModBlocks.BENT_CHIMNEY.get().asItem())
                .add(ModBlocks.WALL_CHIMNEY.get().asItem())
                .add(ModBlocks.METAL_FENCE_BLOCK.get().asItem())
                .add(ModBlocks.WIRE_BOX.get().asItem());
    }
}
