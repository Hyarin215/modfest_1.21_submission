package com.doublepi.temporang.in_game.blocks.refinery;

import com.doublepi.temporang.registries.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RefineryBlock extends AbstractFurnaceBlock {

    public static final MapCodec<RefineryBlock> CODEC = simpleCodec(RefineryBlock::new);
    public RefineryBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<RefineryBlock> codec() {
        return CODEC;
    }

    @Override
    protected void openContainer(Level level, BlockPos blockPos, Player player) {
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        if (blockentity instanceof RefineryBlockEntity) {
            player.openMenu((MenuProvider)blockentity);
            player.awardStat(Stats.INTERACT_WITH_SMOKER);
        }
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RefineryBlockEntity(blockPos, blockState);
    }


    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, ModBlockEntities.REFINERY_BE.get());
    }
}
