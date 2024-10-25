package com.doublepi.temporang.in_game.blocks.refinery;

import com.doublepi.temporang.in_game.blocks.ModBlocks;
import com.doublepi.temporang.utils.ModRecipeSerializers;
import com.doublepi.temporang.utils.ModRecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Blocks;

public class RefineryRecipe extends AbstractCookingRecipe {
    public RefineryRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(ModRecipeTypes.REFINING.get(), group, category, ingredient, result, experience, cookingTime);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.REFINERY);
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.REFINING_SERIALIZER.get();
    }
}
