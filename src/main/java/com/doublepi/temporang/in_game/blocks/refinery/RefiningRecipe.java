package com.doublepi.temporang.in_game.blocks.refinery;

import com.doublepi.temporang.registries.ModBlocks;
import com.doublepi.temporang.registries.ModRecipeSerializers;
import com.doublepi.temporang.registries.ModRecipeTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class RefiningRecipe extends AbstractCookingRecipe {
    public RefiningRecipe(String group, CookingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(ModRecipeTypes.REFINING.get(), group, category, ingredient, result, experience, cookingTime);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.REFINERY);
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.REFINING_SERIALIZER.get();
    }

}
