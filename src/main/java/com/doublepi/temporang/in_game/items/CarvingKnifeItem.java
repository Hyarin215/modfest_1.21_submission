package com.doublepi.temporang.in_game.items;

import com.doublepi.temporang.registries.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class CarvingKnifeItem extends Item {
    public CarvingKnifeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if(!level.getBlockState(pos).is(Blocks.JUNGLE_LOG))
            return InteractionResult.FAIL;

        level.setBlockAndUpdate(pos, ModBlocks.TOTEM.get().defaultBlockState());
        context.getItemInHand().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        return InteractionResult.SUCCESS;
    }


}
