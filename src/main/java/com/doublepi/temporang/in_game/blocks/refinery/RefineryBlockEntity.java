package com.doublepi.temporang.in_game.blocks.refinery;

import com.doublepi.temporang.registries.ModBlockEntities;
import com.doublepi.temporang.registries.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RefineryBlockEntity extends AbstractFurnaceBlockEntity {


    public RefineryBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.REFINERY_BE.get(), pos, blockState, ModRecipeTypes.REFINING.get());
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("temporang.misc.refinery");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new RefineryMenu(id, player, this, this.dataAccess);
    }
}
