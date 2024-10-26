package com.doublepi.temporang.in_game.blocks.totem;

import com.doublepi.temporang.registries.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Iterator;

public class TotemBlockEntity extends BlockEntity {
    public static final int RANGE = 5;
    public TotemBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TOTEM_BE.get(), pos, blockState);
    }


    public void tick(Level pLevel1, BlockPos pPos, BlockState pState1) {
        AABB area = new AABB(pPos.offset(-RANGE,-RANGE,-RANGE).getCenter(),pPos.offset(RANGE,RANGE,RANGE).getCenter());
        Iterator<ServerPlayer> players = pLevel1.getEntitiesOfClass(ServerPlayer.class, area).iterator();

        while(players.hasNext()) {
            ServerPlayer serverplayer = players.next();
            MobEffectInstance effect = new MobEffectInstance(MobEffects.REGENERATION,40,0,true,true,true);
            serverplayer.addEffect(effect);
        }
    }
}
