package com.doublepi.temporang.items.rewards;

import com.doublepi.temporang.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class CarvingKnifeItem extends Item {
    public CarvingKnifeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if(!level.getBlockState(pos).is(BlockTags.LOGS))
            return InteractionResult.FAIL;

        level.setBlockAndUpdate(pos, ModBlocks.TOTEM.get().defaultBlockState());
        if(!context.getPlayer().isCreative())
            context.getItemInHand().setDamageValue(getDamage(context.getItemInHand())+1);
        return InteractionResult.SUCCESS;
    }


}
