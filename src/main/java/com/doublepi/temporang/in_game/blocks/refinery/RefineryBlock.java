package com.doublepi.temporang.in_game.blocks.refinery;

import com.doublepi.temporang.registries.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class RefineryBlock extends AbstractFurnaceBlock {

    public static final MapCodec<RefineryBlock> CODEC = simpleCodec(RefineryBlock::new);
    public static final VoxelShape SHAPE = Block.box(2,0,2,14,11,14);

    public RefineryBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<RefineryBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
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
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!state.getValue(LIT))
            return;
        double d0 = (double)pos.getX() + 0.5;
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.2) {
            level.playLocalSound(d0, d1, d2, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }
        Direction direction = state.getValue(FACING);
        Direction.Axis direction$axis = direction.getAxis();
        double d3 = 0.52;
        double d4 = random.nextDouble() * 0.6 - 0.3;
        double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : d4;
        double d6 = random.nextDouble() * 9.0 / 16.0;
        double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : d4;
        level.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0, 0.0, 0.0);

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
