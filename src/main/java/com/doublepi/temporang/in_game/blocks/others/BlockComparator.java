package com.doublepi.temporang.in_game.blocks.others;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net. minecraft. core. Direction.Axis;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;

public class BlockComparator extends RotatedPillarBlock {
    public static final BooleanProperty POWERED;
    public static final MapCodec<BlockComparator> CODEC = simpleCodec(BlockComparator::new);

    public MapCodec<BlockComparator> codec() {
        return CODEC;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWERED)? 15:0;
    }

    public BlockComparator(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        if(level.isClientSide())
            return;

        Axis axis = state.getValue(AXIS);

        Vec3i sidedOffset = switch (axis) {
            case Axis.X -> new Vec3i(1, 0, 0);
            case Axis.Y -> new Vec3i(0, 1, 0);
            case Axis.Z -> new Vec3i(0, 0, 1);
            default -> throw new IllegalStateException("Unexpected value!: " + this.AXIS);
        };

        Block side1 = level.getBlockState(pos.offset(sidedOffset)).getBlock();
        Block side2 = level.getBlockState(pos.subtract(sidedOffset)).getBlock();
        if(side1.equals(side2)) {
            level.setBlockAndUpdate(pos, state.setValue(POWERED, true));
        }else{
            level.setBlockAndUpdate(pos, state.setValue(POWERED, false));
        }
    }




    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
        pBuilder.add(AXIS);
    }

    static{
        POWERED = BlockStateProperties.POWERED;
    }
}
