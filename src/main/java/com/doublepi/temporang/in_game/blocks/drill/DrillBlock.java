package com.doublepi.temporang.in_game.blocks.drill;

import com.doublepi.temporang.in_game.blocks.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class DrillBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty POWERED;
    public static final MapCodec<DrillBlock> CODEC = simpleCodec(DrillBlock::new);
    protected static VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

    public DrillBlock(Properties properties) {
        super(properties);
    }

    static{
        POWERED = BlockStateProperties.POWERED;
        FACING = BlockStateProperties.FACING;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(POWERED);
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if(context.getPlayer().isCrouching())
            return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection())
                .setValue(POWERED,context.getLevel().hasNeighborSignal(context.getClickedPos()));
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite())
                .setValue(POWERED,context.getLevel().hasNeighborSignal(context.getClickedPos()));
    }


    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (level.isClientSide())
            return;
        boolean isPowered = state.getValue(POWERED);
        if (isPowered == level.hasNeighborSignal(pos)) // if state is as power, continue
            return;
        if (isPowered) //if state is powered but no redstone signal in sight
            level.scheduleTick(pos, this, 1); //call tick() after 1 redstone tick(?)
        else //if state is unpowered but there is signal in sight
            level.setBlockAndUpdate(pos, state.cycle(POWERED));

    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED) && !level.hasNeighborSignal(pos)) { //if is powered and no signal
            level.setBlockAndUpdate(pos, state.cycle(POWERED));
        }
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // z+ -> (0,0,0)-(16,16,12)
        // z- -> (0,0,4)-(16,16,16)
        // y+ -> (0,0,0)-(16,12,16)
        // y- -> (0,4,0)-(16,16,16)
        // x+ -> (0,0,0)-(12,16,16)
        // x- -> (4,0,0)-(16,16,16)
        Vec3i normal = state.getValue(FACING).getNormal();
        int x = normal.getX();
        int z = normal.getZ();
        int y = normal.getY();
        SHAPE = Block.box(-2*Math.abs(x)*(x-1),-2*Math.abs(y)*(y-1),-2*Math.abs(z)*(z-1),16-2*Math.abs(x)*(x+1),16-2*Math.abs(y)*(y+1),16-2*Math.abs(z)*(z+1));
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DrillBlockEntity(blockPos,blockState);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        Direction facing = state.getValue(FACING);

        return createTickerHelper(blockEntityType, ModBlockEntities.DRILL_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1, facing));
    }
}
