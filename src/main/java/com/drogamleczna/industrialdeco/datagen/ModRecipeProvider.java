package com.drogamleczna.industrialdeco.datagen;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import com.drogamleczna.industrialdeco.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;


import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
    }

    protected static void stonecutterRecipeFromTag(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, TagKey material) {
        SingleItemRecipeBuilder var10000 = SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, 1)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT));
        var10000.save(recipeOutput, getItemName(result) + "_stonecutting");
    }

    public Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    @Override
    protected void buildRecipes(RecipeOutput pWriter) {
        ArrayList<Block> disabled_blocks = new ArrayList<Block>();
        disabled_blocks.add(ModBlocks.BENCH.get());
        disabled_blocks.add(ModBlocks.PALLET.get());
        disabled_blocks.add(ModBlocks.STREET_LAMP.get());
        disabled_blocks.add(ModBlocks.CEILING_OFFICE_LAMP.get());
        disabled_blocks.add(ModBlocks.WIRE_BLOCK.get());
        disabled_blocks.add(ModBlocks.WALL_SWITCH.get());
        disabled_blocks.add(ModBlocks.WIRE_BOX.get());

        getKnownBlocks().forEach(block -> {
            if(!disabled_blocks.contains(block)){
                stonecutterRecipeFromTag( pWriter, RecipeCategory.MISC, (ItemLike) block, ModTags.Items.INDUSTRIAL_DECO_METAL_BLOCKS);
                stonecutterResultFromBase(pWriter, RecipeCategory.MISC, block, Items.IRON_INGOT);
            }
        } );




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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DISTRIBUTION_BOX.get())
                .pattern("SNS")
                .pattern("N N")
                .pattern("SNS")
                .define('S', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WALL_DISTRIBUTION_BOX.get())
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.METAL_FENCE_BLOCK_YELLOW.get())
                .requires(ModBlocks.METAL_FENCE_BLOCK.get())
                .requires(Tags.Items.DYES_YELLOW)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Tags.Items.DYES_YELLOW))
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.WALL_SWITCH.get())
                .requires(ModBlocks.WIRE_BOX.get())
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
                .define('S', ItemTags.WOODEN_SLABS)
                .define('N', ItemTags.WOODEN_FENCES)
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
        stonecutterResultFromBase(pWriter, RecipeCategory.MISC, ModBlocks.WIRE_BLOCK, Items.COPPER_INGOT);


    }



}
