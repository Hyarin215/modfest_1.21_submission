package com.doublepi.temporang.blocks.cannon;

import com.doublepi.temporang.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CannonBlockEntity extends BlockEntity {
    public CannonBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CANNON_BE.get(), pos, blockState);
    }
}
