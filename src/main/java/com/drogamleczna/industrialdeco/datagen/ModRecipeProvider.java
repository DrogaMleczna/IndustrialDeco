package com.drogamleczna.industrialdeco.datagen;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import com.drogamleczna.industrialdeco.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ArrayList<RegistryObject<Block>> blocks = new ArrayList<RegistryObject<Block>>();
        blocks.add(ModBlocks.STREET_LAMP);
        blocks.add(ModBlocks.CURVED_POLE);
        blocks.add(ModBlocks.DOUBLE_CURVED_POLE);
        blocks.add(ModBlocks.QUADRUPLE_CURVED_POLE);
        blocks.add(ModBlocks.CORNER_POLE);
        blocks.add(ModBlocks.WALL_CURVED_POLE);
        blocks.add(ModBlocks.WIRE_POLE);
        blocks.add(ModBlocks.POLE_BASE);
        blocks.add(ModBlocks.LARGE_BASE);
        blocks.add(ModBlocks.POLE_BLOCK);
        blocks.add(ModBlocks.HAZARD_POLE);
        blocks.add(ModBlocks.WARNING_POLE);
        blocks.add(ModBlocks.CAMERA_POLE);
        blocks.add(ModBlocks.CAMERA_POLE_CORNER);
        blocks.add(ModBlocks.CAMERA_POLE_TRIPLE);
        blocks.add(ModBlocks.CAMERA_POLE_QUADRUPLE);
        blocks.add(ModBlocks.SPLIT_POLE);
        blocks.add(ModBlocks.SWITCHBOARD);
        blocks.add(ModBlocks.WALL_SWITCHBOARD);
        blocks.add(ModBlocks.MEDIUM_BASE);
        blocks.add(ModBlocks.PALLET);
        blocks.add(ModBlocks.SECURITY_CAMERA);
        blocks.add(ModBlocks.CHIMNEY_BLOCK);
        blocks.add(ModBlocks.BENT_CHIMNEY);
        blocks.add(ModBlocks.WALL_CHIMNEY);
        blocks.add(ModBlocks.WIRE_BOX);
        blocks.add(ModBlocks.CEILING_OFFICE_LAMP);
        blocks.add(ModBlocks.OFFICE_CEILING);
        blocks.add(ModBlocks.BENCH);
        blocks.add(ModBlocks.CROSSBUCK_BLOCK);
        blocks.add(ModBlocks.METAL_FENCE_BLOCK);
        for (RegistryObject<Block> block : blocks){
            if(!(block == ModBlocks.BENCH || block == ModBlocks.PALLET || block == ModBlocks.STREET_LAMP || block == ModBlocks.CEILING_OFFICE_LAMP) ){
                CustomRecipeBuilder.stonecutting(Ingredient.of(Items.IRON_INGOT),RecipeCategory.MISC, block.get() )
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .saveWithSuffix("_iron",pWriter, block.getId());
                CustomRecipeBuilder.stonecutting(Ingredient.of(ModTags.Items.INDUSTRIAL_DECO_METAL_BLOCKS),RecipeCategory.MISC, block.get() )
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .save(pWriter);
            }

        }


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STREET_LAMP.get(), 4)
                .pattern("SSS")
                .pattern("SNS")
                .define('S', Items.IRON_INGOT)
                .define('N', Items.GLOWSTONE)
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CURVED_POLE.get(),2)
                .pattern("  S")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DOUBLE_CURVED_POLE.get(),2)
                .pattern("S S")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.QUADRUPLE_CURVED_POLE.get())
                .pattern("SSS")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WIRE_POLE.get(),4)
                .pattern(" S ")
                .pattern("SSS")
                .pattern(" S ")
                .define('S', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MEDIUM_BASE.get())
                .pattern(" Z ")
                .pattern("N N")
                .pattern("N N")
                .define('Z', Items.IRON_NUGGET)
                .define('N', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LARGE_BASE.get(),2)
                .pattern(" N ")
                .pattern("N N")
                .pattern("N N")
                .define('N', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLE_BASE.get())
                .pattern(" Z ")
                .pattern(" N ")
                .define('Z', Items.IRON_NUGGET)
                .define('N', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLE_BLOCK.get(), 3)
                .pattern(" Z ")
                .pattern(" N ")
                .pattern(" N ")
                .define('Z', Items.IRON_NUGGET)
                .define('N', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPLIT_POLE.get(),2)
                .pattern(" SS")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CORNER_POLE.get(),2)
                .pattern(" SS")
                .pattern(" SS")
                .pattern(" S ")
                .define('S', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SWITCHBOARD.get())
                .pattern("SNS")
                .pattern("N N")
                .pattern("SNS")
                .define('S', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WALL_SWITCHBOARD.get())
                .pattern("SNS")
                .pattern("N N")
                .pattern("SNS")
                .define('N', Items.IRON_INGOT)
                .define('S', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PALLET.get())
                .pattern("N N")
                .pattern("SSS")
                .define('N', Items.IRON_NUGGET)
                .define('S', Blocks.OAK_SLAB)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.HAZARD_POLE.get())
                .requires(ModBlocks.POLE_BLOCK.get())
                .requires(Tags.Items.DYES_YELLOW)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.DYES_YELLOW))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.METAL_FENCE_BLOCK_YELLOW.get())
                .requires(ModBlocks.METAL_FENCE_BLOCK.get())
                .requires(Tags.Items.DYES_YELLOW)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.DYES_YELLOW))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WARNING_POLE.get())
                .requires(ModBlocks.POLE_BLOCK.get())
                .requires(Tags.Items.DYES_RED)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.DYES_RED))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CAMERA_POLE_CORNER.get())
                .requires(ModBlocks.POLE_BLOCK.get())
                .requires(Items.IRON_NUGGET, 2)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.DYES_RED))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CAMERA_POLE_TRIPLE.get())
                .requires(ModBlocks.POLE_BLOCK.get())
                .requires(Items.IRON_NUGGET, 3)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.DYES_RED))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CAMERA_POLE_QUADRUPLE.get())
                .requires(ModBlocks.POLE_BLOCK.get())
                .requires(Items.IRON_NUGGET, 4)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.DYES_RED))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CAMERA_POLE.get())
                .requires(ModBlocks.POLE_BLOCK.get())
                .requires(Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.DYES_RED))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WALL_CURVED_POLE.get())
                .requires(ModBlocks.CURVED_POLE.get())
                .unlockedBy(getHasName(ModBlocks.WALL_CURVED_POLE.get()), has(ModBlocks.WALL_CURVED_POLE.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WIRE_BOX.get())
                .requires(Items.IRON_NUGGET)
                .requires(Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SECURITY_CAMERA.get())
                .pattern("NNN")
                .pattern("N S")
                .define('N', Items.IRON_NUGGET)
                .define('S', Blocks.GLASS_PANE)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHIMNEY_BLOCK.get())
                .pattern("NNN")
                .pattern("NSN")
                .pattern("N N")
                .define('N', Items.IRON_NUGGET)
                .define('S', Blocks.CAMPFIRE)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BENT_CHIMNEY.get())
                .pattern("N N")
                .pattern(" NN")
                .pattern("  N")
                .define('N', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WALL_CHIMNEY.get())
                .pattern("N N")
                .pattern("N N")
                .pattern("N N")
                .define('N', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CEILING_OFFICE_LAMP.get(), 8)
                .pattern("SSS")
                .pattern("NNN")
                .define('S', Items.IRON_INGOT)
                .define('N', Items.GLOWSTONE)
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OFFICE_CEILING.get(), 16)
                .pattern(" S ")
                .pattern("NNN")
                .define('S', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BENCH.get(), 8)
                .pattern("SSS")
                .pattern("N N")
                .define('S', Items.OAK_SLAB)
                .define('N', Items.OAK_FENCE)
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CROSSBUCK_BLOCK.get(), 1)
                .pattern("S S")
                .pattern(" N ")
                .pattern("S S")
                .define('S', Items.IRON_NUGGET)
                .define('N', ModBlocks.POLE_BLOCK.get())
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.METAL_FENCE_BLOCK.get(), 8)
                .pattern(" S ")
                .pattern("SSS")
                .pattern("S S")
                .define('S', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.IRON_INGOT))
                .save(pWriter);

    }

}
