package com.doublepi.temporang.registries;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.in_game.blocks.refinery.RefineryRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS
            = DeferredRegister.create(Registries.RECIPE_SERIALIZER, TemporangMod.MOD_ID);

//    public static final Supplier<SimpleCookingSerializer<?>> REFINING_SERIALIZER =
//            RECIPE_SERIALIZERS.register("refining", () ->{
//                return new SimpleCookingSerializer<AbstractCookingRecipe>(RefineryRecipe::new,200);
//            });
        public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RefineryRecipe>> REFINING_SERIALIZER =
        RECIPE_SERIALIZERS.register("refining", ()->new SimpleCookingSerializer<>(RefineryRecipe::new, 200));

    public static void register(IEventBus event){
        RECIPE_SERIALIZERS.register(event);
    }



}
