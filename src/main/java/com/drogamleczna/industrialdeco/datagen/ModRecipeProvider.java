package com.drogamleczna.industrialdeco.datagen;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
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
                .pattern(" N ")
                .pattern(" S ")
                .define('Z', Items.IRON_NUGGET)
                .define('N', Items.IRON_INGOT)
                .define('S', Items.IRON_BLOCK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LARGE_BASE.get(),2)
                .pattern(" Z ")
                .pattern(" N ")
                .define('Z', Items.IRON_NUGGET)
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BENCH.get(), 8)
                .pattern("SSS")
                .pattern("N N")
                .define('S', Items.OAK_SLAB)
                .define('N', Items.OAK_FENCE)
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.IRON_INGOT))
                .save(pWriter);

    }

}
