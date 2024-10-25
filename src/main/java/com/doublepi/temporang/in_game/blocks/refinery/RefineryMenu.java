package com.doublepi.temporang.in_game.blocks.refinery;

import com.doublepi.temporang.utils.ModMenuTypes;
import com.doublepi.temporang.utils.ModRecipeTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RefineryMenu extends AbstractFurnaceMenu {


    public RefineryMenu(int containerId, Inventory playerInventory, Container refineryContainer, ContainerData refineryData) {
        super(ModMenuTypes.REFINERY_MENU.get(), ModRecipeTypes.REFINING.get(), RecipeBookType.BLAST_FURNACE, containerId, playerInventory, refineryContainer, refineryData);
    }

    public RefineryMenu(int containerId, Inventory inventory, RegistryFriendlyByteBuf registryFriendlyByteBuf) {
        super(ModMenuTypes.REFINERY_MENU.get(),ModRecipeTypes.REFINING.get(), RecipeBookType.BLAST_FURNACE, containerId,inventory);
    }
}