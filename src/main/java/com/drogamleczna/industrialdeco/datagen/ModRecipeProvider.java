package com.drogamleczna.industrialdeco.datagen;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STREET_LAMP.get())
                .pattern("SSS")
                .pattern("SNS")
                .define('S', Items.IRON_INGOT)
                .define('N', Items.GLOWSTONE)
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CURVED_POLE.get())
                .pattern("  S")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DOUBLE_CURVED_POLE.get())
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WIRE_POLE.get())
                .pattern(" S ")
                .pattern("SSS")
                .pattern(" S ")
                .define('S', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LARGE_BASE.get())
                .pattern(" Z ")
                .pattern(" N ")
                .define('Z', Items.IRON_INGOT)
                .define('N', Items.IRON_BLOCK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLE_BASE.get())
                .pattern(" Z ")
                .pattern(" N ")
                .define('Z', Items.IRON_NUGGET)
                .define('N', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.POLE_BLOCK.get())
                .pattern(" Z ")
                .pattern(" N ")
                .pattern(" N ")
                .define('Z', Items.IRON_NUGGET)
                .define('N', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPLIT_POLE.get())
                .pattern(" SS")
                .pattern(" S ")
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
    }
}
