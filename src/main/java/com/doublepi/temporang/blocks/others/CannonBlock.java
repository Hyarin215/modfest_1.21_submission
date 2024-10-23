package com.doublepi.temporang.blocks.others;

import com.doublepi.temporang.entities.CannonBallEntity;
import com.doublepi.temporang.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CannonBlock extends Block {
    public static final DirectionProperty FACING;
    public static final IntegerProperty AIM;
    public static final BooleanProperty CAN_SHOOT;

    public static final int AIM_RANGE = 3;

    protected static final VoxelShape COLLISION_BOX = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public CannonBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(AIM,0).setValue(CAN_SHOOT,true));
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int offset = player.isCrouching()? 1:-1;
        int currentAim = state.getValue(AIM);
        if(currentAim+offset> AIM_RANGE)
            return InteractionResult.FAIL;
        if(currentAim+offset<0)
            return InteractionResult.FAIL;
        state = state.setValue(AIM,currentAim+offset);
        level.setBlockAndUpdate(pos,state);
        level.playLocalSound(pos, SoundEvents.SPYGLASS_USE, SoundSource.BLOCKS,1f,1f,false);
        return InteractionResult.SUCCESS;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!state.getValue(CAN_SHOOT))
            return ItemInteractionResult.SUCCESS;
        if(stack.is(Items.FLINT_AND_STEEL)){
            double angle = 0.3926991*state.getValue(AIM); //22*pi/180
            int x = state.getValue(FACING).getNormal().getX();
            int z = state.getValue(FACING).getNormal().getZ();

            Vec3 deltaMovement = new Vec3(x*Math.cos(angle), Math.sin(angle),z*Math.cos(angle));
            CannonBallEntity cannonBall = new CannonBallEntity(level,
                    pos.getX()+x*(0.5*x+0.7)+0.5*Math.abs(z),
                    pos.getY()+0.5+Math.sin(angle),
                    pos.getZ()+0.5*Math.abs(x)+z*(0.5*z+0.7));

            cannonBall.setItem(ModItems.CANNON_BALL.get().getDefaultInstance());
            cannonBall.addDeltaMovement(deltaMovement);

            level.addFreshEntity(cannonBall);
            stack.hurtAndBreak(5, player, EquipmentSlot.MAINHAND);
            level.setBlockAndUpdate(pos, state.setValue(CAN_SHOOT,false));
            level.scheduleTick(pos,state.getBlock(),50);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;


    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlockAndUpdate(pos, state.setValue(CAN_SHOOT,true));
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
        builder.add(FACING);
        builder.add(AIM);
        builder.add(CAN_SHOOT);
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection())
                .setValue(AIM,0)
                .setValue(CAN_SHOOT,true);
    }

    static {
        FACING = BlockStateProperties.FACING;
        AIM = IntegerProperty.create("aim",0, AIM_RANGE);
        CAN_SHOOT = BooleanProperty.create("can_shoot");
    }
}
