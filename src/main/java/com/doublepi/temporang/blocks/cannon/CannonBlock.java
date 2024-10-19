package com.doublepi.temporang.blocks.cannon;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CannonBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING;
    //public static final IntegerProperty AIM;

    public static final int AIM_RANGE = 5;

    public static final MapCodec<CannonBlock> CODEC = simpleCodec(CannonBlock::new);
    protected static final VoxelShape COLLISION_BOX = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public CannonBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide())
            return InteractionResult.FAIL;
//        if(player.isShiftKeyDown())
//            offsetAim(state, -1);
//        else
//            offsetAim(state,1);
        return InteractionResult.SUCCESS;
    }


//    private void offsetAim(BlockState state, int offset){
//        int currentAim = state.getValue(AIM);
//        if(currentAim+offset> AIM_RANGE)
//            return;
//        if(currentAim+offset<0)
//            return;
//        state.setValue(AIM,currentAim+offset);
//    }



    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CannonBlockEntity(blockPos, blockState);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_BOX;
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    //facing property stuff
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    protected BlockState rotate(BlockState state, Rotation rot) {
        return (BlockState)state.setValue(FACING, rot.rotate((Direction)state.getValue(FACING)));
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.getValue(FACING)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
                //.setValue(AIM,0);
    }

    static {
        FACING = BlockStateProperties.FACING;
        //AIM = IntegerProperty.create("Aim",0, AIM_RANGE);
    }
}
