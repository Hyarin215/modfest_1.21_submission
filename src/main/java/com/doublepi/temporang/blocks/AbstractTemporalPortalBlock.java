package com.doublepi.temporang.blocks;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.items.ModItems;
import com.doublepi.temporang.utils.ModLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractTemporalPortalBlock extends Block {
    public int part;
    public static HashMap<Item, ResourceKey> map = new HashMap<>();

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
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return (level.getBlockState(pos.offset(0,1,0)).canBeReplaced()
                && level.getBlockState(pos.offset(0,-1,0)).canBeReplaced());
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
            return ItemInteractionResult.FAIL;
        if(!map.containsKey(stack.getItemHolder()))
            return ItemInteractionResult.FAIL;


        List<ItemStack> reward = generateReward(level, state, pos, player, stack.getItem().asItem());
        for (ItemStack itemStack : reward) {
            player.addItem(itemStack);
        }
        stack.hurtAndBreak(1,player, EquipmentSlot.MAINHAND);
        player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        player.getCooldowns().addCooldown(stack.getItem(), 20);
        return ItemInteractionResult.SUCCESS;

    }

    private List<ItemStack> generateReward(Level level, BlockState state, BlockPos pos, Player player, Item boomerang){
        ResourceKey<LootTable> resourceKey = map.get(boomerang);
        LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(resourceKey);

        LootParams lootparams = (new LootParams.Builder((ServerLevel) level)).withParameter(LootContextParams.BLOCK_STATE, state)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .create(LootContextParamSets.BLOCK_USE);

        return lootTable.getRandomItems(lootparams, level.getRandom());
    }

    static{
        map.put(ModItems.STONE_BOOMERANG.get(), ModLootTables.STONE_AGE_REWARDS);
        map.put(ModItems.IRON_BOOMERANG.get(), ModLootTables.STONE_AGE_REWARDS);
        map.put(ModItems.INDUSTRIAL_BOOMERANG.get(), ModLootTables.STONE_AGE_REWARDS);
        map.put(ModItems.INFORMATIONAL_BOOMERANG.get(), ModLootTables.STONE_AGE_REWARDS);

    }

}
