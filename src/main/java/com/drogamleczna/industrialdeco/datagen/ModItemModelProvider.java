package com.drogamleczna.industrialdeco.datagen;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IndustrialDeco.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        blockItem(ModBlocks.STREET_LAMP);
        blockItem(ModBlocks.CURVED_POLE);
        blockItem(ModBlocks.DOUBLE_CURVED_POLE);
        blockItem(ModBlocks.QUADRUPLE_CURVED_POLE);
        blockItem(ModBlocks.WIRE_POLE);
        blockItem(ModBlocks.POLE_BASE);
        blockItem(ModBlocks.LARGE_BASE);
        blockItem(ModBlocks.MEDIUM_BASE);
        blockItem(ModBlocks.POLE_BLOCK);
        blockItem(ModBlocks.HAZARD_POLE);
        blockItem(ModBlocks.WARNING_POLE);
        blockItem(ModBlocks.CAMERA_POLE);
        blockItem(ModBlocks.CAMERA_POLE_CORNER);
        blockItem(ModBlocks.CAMERA_POLE_TRIPLE);
        blockItem(ModBlocks.CAMERA_POLE_QUADRUPLE);
        blockItem(ModBlocks.SECURITY_CAMERA);
        blockItem(ModBlocks.SPLIT_POLE);
        blockItem(ModBlocks.SWITCHBOARD);
        blockItem(ModBlocks.WALL_SWITCHBOARD);
        blockItem(ModBlocks.CHIMNEY_BLOCK);
        blockItem(ModBlocks.BENT_CHIMNEY);
        blockItem(ModBlocks.WALL_CHIMNEY);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(IndustrialDeco.MODID, "item/" + item.getId().getPath()));
    }

    public void blockItem(RegistryObject<Block> block) {
        this.withExistingParent(IndustrialDeco.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
}
