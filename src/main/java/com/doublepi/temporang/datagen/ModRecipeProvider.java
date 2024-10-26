package com.doublepi.temporang.datagen;


import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_BOOMERANG.get())
                .pattern("TTT")
                .pattern("TBT")
                .pattern("TTT")
                .define('T', ModItems.TIME_FRAGMENT.get())
                .define('B', ModItems.STONE_BOOMERANG.get())
                .unlockedBy("has_time_fragment",has(ModItems.TIME_FRAGMENT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INDUSTRIAL_BOOMERANG.get())
                .pattern("TTT")
                .pattern("TBT")
                .pattern("TTT")
                .define('T', ModItems.TIME_FRAGMENT.get())
                .define('B', ModItems.IRON_BOOMERANG.get())
                .unlockedBy("has_time_fragment",has(ModItems.IRON_BOOMERANG.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INFORMATIONAL_BOOMERANG.get())
                .pattern("TTT")
                .pattern("TBT")
                .pattern("TTT")
                .define('T', ModItems.TIME_FRAGMENT.get())
                .define('B', ModItems.INDUSTRIAL_BOOMERANG.get())
                .unlockedBy("has_time_fragment",has(ModItems.INDUSTRIAL_BOOMERANG.get())).save(pRecipeOutput);

//        List<ItemLike> BLACK_OPAL_SMELTABLES = List.of(ModItems.RAW_BLACK_OPAL,
//                ModBlocks.BLACK_OPAL_ORE, ModBlocks.BLACK_OPAL_DEEPSLATE_ORE, ModBlocks.BLACK_OPAL_END_ORE, ModBlocks.BLACK_OPAL_NETHER_ORE);
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLACK_OPAL_BLOCK.get())
//                .pattern("BBB")
//                .pattern("BBB")
//                .pattern("BBB")
//                .define('B', ModItems.BLACK_OPAL.get())
//                .unlockedBy("has_block_opal", has(ModItems.BLACK_OPAL.get())).save(pRecipeOutput);
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACK_OPAL.get(), 9)
//                .requires(ModBlocks.BLACK_OPAL_BLOCK.get())
//                .unlockedBy("has_black_opal_block", has(ModBlocks.BLACK_OPAL_BLOCK.get())).save(pRecipeOutput);
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACK_OPAL.get(), 9)
//                .requires(ModBlocks.MAGIC_BLOCK.get())
//                .unlockedBy("has_magic_block", has(ModBlocks.MAGIC_BLOCK.get())).save(pRecipeOutput, "mccourse:black_opal_2");
//        oreSmelting(pRecipeOutput, BLACK_OPAL_SMELTABLES, RecipeCategory.MISC, ModItems.BLACK_OPAL.get(), 0.25f, 200, "black_opal");
//        oreBlasting(pRecipeOutput, BLACK_OPAL_SMELTABLES, RecipeCategory.MISC, ModItems.BLACK_OPAL.get(), 0.25f, 100, "black_opal");
    }



    protected static <T extends AbstractCookingRecipe> void oreFurnacing(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                                  List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, TemporangMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}