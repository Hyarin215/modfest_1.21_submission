package com.doublepi.temporang.blocks.block_entities;

import com.doublepi.temporang.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class TotemBlockEntity extends BlockEntity {
    public static final int RANGE = 5;
    public TotemBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TOTEM_BE.get(), pos, blockState);
    }


    public void tick(Level pLevel1, BlockPos pPos, BlockState pState1) {
        AABB area = new AABB(pPos.offset(-RANGE,-RANGE,-RANGE).getCenter(),pPos.offset(RANGE,RANGE,RANGE).getCenter());

        //pLevel1.getEntities(Player,area);
    }
}
