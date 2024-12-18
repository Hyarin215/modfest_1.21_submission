package com.doublepi.temporang.registries;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.in_game.blocks.refinery.RefiningRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, TemporangMod.MOD_ID);

    public static final Supplier<RecipeType<RefiningRecipe>> REFINING
            = RECIPE_TYPES.register("refining", () -> new RecipeType<RefiningRecipe>() {
        @Override
        public String toString() {
            return "refining";
        }
    });



    public static void register(IEventBus event){
        RECIPE_TYPES.register(event);
    }





}
