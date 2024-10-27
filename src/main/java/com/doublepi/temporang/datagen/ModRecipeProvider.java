package com.doublepi.temporang.datagen;


import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_BOOMERANG.get())
                        .pattern(" T ")
                        .pattern("T T")
                        .define('T', Items.OAK_PLANKS)
                        .unlockedBy("got_planks", has(Items.OAK_PLANKS)).save(pRecipeOutput);
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
    }
}