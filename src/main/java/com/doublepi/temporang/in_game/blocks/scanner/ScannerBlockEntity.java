package com.doublepi.temporang.in_game.blocks.scanner;

import com.doublepi.temporang.in_game.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.Iterator;

public class ScannerBlockEntity extends BlockEntity {
    public static final int RANGE = 50;

    public ScannerBlockEntity( BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SCANNER_BE.get(), pos, blockState);
    }


    public void tick(Level level, BlockPos pos, BlockState state){

        AABB area = new AABB(pos.offset(-RANGE,-RANGE,-RANGE).getCenter(),pos.offset(RANGE,RANGE,RANGE).getCenter());
        Iterator<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, area).iterator();

        while(targets.hasNext()) {
            LivingEntity livingEntity=targets.next();
            MobEffectInstance effect = new MobEffectInstance(MobEffects.GLOWING,40,0,true,true,true);
            livingEntity.addEffect(effect);
        }
    }




}
