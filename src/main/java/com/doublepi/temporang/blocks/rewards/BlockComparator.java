package com.doublepi.temporang.blocks.rewards;

import com.doublepi.temporang.TemporangMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net. minecraft. core. Direction.Axis;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PoweredBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;

public class BlockComparator extends RotatedPillarBlock {
    public static final IntegerProperty POWER;
    public static final MapCodec<BlockComparator> CODEC = simpleCodec(BlockComparator::new);

    public MapCodec<BlockComparator> codec() {
        return CODEC;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        TemporangMod.LOGGER.error("sss");
    }

    public BlockComparator(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWER, 0));
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(POWER);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
        TemporangMod.LOGGER.error("whhyy");
        if(level.isClientSide())
            return;
        Axis axis = state.getValue(AXIS);

        Vec3i sidedOffset = switch (axis) {
            case Axis.X -> new Vec3i(1, 0, 0);
            case Axis.Y -> new Vec3i(0, 1, 0);
            case Axis.Z -> new Vec3i(0, 0, 1);
            default -> throw new IllegalStateException("Unexpected value: " + this.AXIS);
        };


        Block side1 = level.getBlockState(pos.offset(sidedOffset)).getBlock();
        Block side2 = level.getBlockState(pos.subtract(sidedOffset)).getBlock();
        TemporangMod.LOGGER.error("Side 1: "+side1.getName().toString()+", Side 2: "+side2.getName());
        if(side1.equals(side2)){
            TemporangMod.LOGGER.error("Do");
            state.setValue(POWER,15);
        }else{
            state.setValue(POWER,0);
        }
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWER);
        pBuilder.add(AXIS);
    }

    static{
        POWER = BlockStateProperties.POWER;
    }
}
