package com.doublepi.temporang.blocks.temporal_portal;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.blocks.ModBlocks;
import com.doublepi.temporang.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.phys.BlockHitResult;

public class AbstractTemporalPortalBlock extends Block {
    public int part;
    public AbstractTemporalPortalBlock(Properties properties, int part) {
        super(properties);
        this.part = part;

    }



    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        super.destroy(level, pos, state);
        if(part ==0) {
            level.destroyBlock(pos.offset(0, -1, 0), false);
            level.destroyBlock(pos.offset(0,1,0), false);
        }else {
            level.destroyBlock(pos.offset(0, -part, 0), false);
            level.destroyBlock(pos.offset(0, -2*part, 0), false);
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if(part != 0)
            return;
        level.setBlockAndUpdate(pos.offset(0,1,0), ModBlocks.TEMPORAL_PORTAL_TOP.get().defaultBlockState());
        level.setBlockAndUpdate(pos.offset(0,-1,0), ModBlocks.TEMPORAL_PORTAL_BOTTOM.get().defaultBlockState());

    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide() || hand == InteractionHand.OFF_HAND)
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        if(stack.is(ModItems.STONE_BOOMERANG)){

            TemporangMod.LOGGER.error(String.valueOf(ResourceLocation.isValidPath("loot_table/stone_age_rewards.json")));
            TemporangMod.LOGGER.error(String.valueOf(ResourceLocation.isValidNamespace("temporang")));

            ResourceKey resourceKey = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("temporang","loot_table/stone_age_rewards.json"));
            LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(resourceKey);


            LootContextParamSet lootContextParameterSet = (new LootContextParamSet.Builder()).build();
            LootParams lootParams = (new LootParams.Builder(level.getServer().overworld())).create(lootContextParameterSet);
            ItemStack reward = lootTable.getRandomItems(lootParams).getFirst();
            player.addItem(reward);
            if(!player.isCreative())
                stack.setDamageValue(stack.getMaxDamage()+1);
            return ItemInteractionResult.CONSUME;
        }
        return ItemInteractionResult.FAIL;
    }


}
