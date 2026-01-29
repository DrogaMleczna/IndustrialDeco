package com.drogamleczna.industrialdeco.datagen;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IndustrialDeco.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleBlockItem(ModBlocks.STREET_LAMP.get());
        simpleBlockItem(ModBlocks.CURVED_POLE.get());
        simpleBlockItem(ModBlocks.DOUBLE_CURVED_POLE.get());
        simpleBlockItem(ModBlocks.QUADRUPLE_CURVED_POLE.get());
        withExistingParent("curved_wall_pole", ResourceLocation.fromNamespaceAndPath("industrialdeco", "post_bent_wall_item_model"));
        simpleBlockItem(ModBlocks.CORNER_POLE.get());
        simpleBlockItem(ModBlocks.WIRE_POLE.get());
        simpleBlockItem(ModBlocks.POLE_BASE.get());
        simpleBlockItem(ModBlocks.LARGE_BASE.get());
        simpleBlockItem(ModBlocks.MEDIUM_BASE.get());
        simpleBlockItem(ModBlocks.POLE_BLOCK.get());
        simpleBlockItem(ModBlocks.HAZARD_POLE.get());
        simpleBlockItem(ModBlocks.WARNING_POLE.get());
        simpleBlockItem(ModBlocks.CAMERA_POLE.get());
        simpleBlockItem(ModBlocks.CAMERA_POLE_CORNER.get());
        simpleBlockItem(ModBlocks.CAMERA_POLE_TRIPLE.get());
        simpleBlockItem(ModBlocks.CAMERA_POLE_QUADRUPLE.get());
        simpleBlockItem(ModBlocks.SECURITY_CAMERA.get());
        simpleBlockItem(ModBlocks.SPLIT_POLE.get());
        simpleBlockItem(ModBlocks.SWITCHBOARD.get());
        simpleBlockItem(ModBlocks.WALL_SWITCHBOARD.get());
        simpleBlockItem(ModBlocks.CHIMNEY_BLOCK.get());
        simpleBlockItem(ModBlocks.BENT_CHIMNEY.get());
        simpleBlockItem(ModBlocks.WALL_CHIMNEY.get());
        simpleBlockItem(ModBlocks.WIRE_BOX.get());
        simpleBlockItem(ModBlocks.CEILING_OFFICE_LAMP.get());
        simpleBlockItem(ModBlocks.OFFICE_CEILING.get());
        simpleBlockItem(ModBlocks.BENCH.get());
        simpleBlockItem(ModBlocks.CROSSBUCK_BLOCK.get());
        simpleBlockItem(ModBlocks.METAL_FENCE_BLOCK.get());
        simpleBlockItem(ModBlocks.METAL_FENCE_BLOCK_YELLOW.get());

    }


    /**public void blockItem(RegistryObject<Block> block) {
        this.withExistingParent(IndustrialDeco.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void blockItemWithExisting(RegistryObject<Block> block, String modelname){
        this.withExistingParent(IndustrialDeco.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("item/" + modelname));
    }**/
}
