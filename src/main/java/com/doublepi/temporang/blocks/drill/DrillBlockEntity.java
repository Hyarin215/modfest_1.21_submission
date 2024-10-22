package com.doublepi.temporang.blocks.drill;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;


public class DrillBlockEntity extends BlockEntity {

    public int COOLDOWN = 20;
    public int COOLDOWN_TRACKER = COOLDOWN;

    public DrillBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DRILL_BE.get(), pos, blockState);
    }

    public void tick(Level level, BlockPos pos, BlockState state, Direction facing) {
        if(COOLDOWN_TRACKER!=0) {
            COOLDOWN_TRACKER--;
            return;
        }
        if(!level.hasNeighborSignal(pos))
            return;
        Vec3i offset = facing.getNormal();
        BlockPos target = pos.offset(offset);
        if(level.getBlockState(target).getBlock().defaultDestroyTime()>=50F)
            return;

        COOLDOWN_TRACKER=COOLDOWN;
        level.destroyBlock(target,true);
    }
}
