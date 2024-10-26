package com.doublepi.temporang.in_game.blocks.refinery;

import com.doublepi.temporang.registries.ModMenuTypes;
import com.doublepi.temporang.registries.ModRecipeTypes;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RefineryMenu extends AbstractFurnaceMenu {


    public RefineryMenu(int containerId, Inventory playerInventory, Container refineryContainer, ContainerData refineryData) {
        super(ModMenuTypes.REFINERY_MENU.get(), ModRecipeTypes.REFINING.get(), RecipeBookType.FURNACE, containerId, playerInventory, refineryContainer, refineryData);
    }

    public RefineryMenu(int containerId, Inventory inventory, RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        super(ModMenuTypes.REFINERY_MENU.get(),ModRecipeTypes.REFINING.get(), RecipeBookType.FURNACE, containerId,inventory);
    }
}