package com.doublepi.temporang.in_game.blocks.refinery;

import com.doublepi.temporang.in_game.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RefineryBlockEntity extends BlockEntity {
    public RefineryBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.REFINERY_BE.get(), pos, blockState);
    }


}
