package com.doublepi.temporang.in_game.blocks;

import com.doublepi.temporang.registries.ModBlocks;
import com.doublepi.temporang.registries.ModItems;
import com.doublepi.temporang.registries.ModLootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class AbstractTemporalPortalBlock extends Block {
    public int part;
    protected static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);

    public AbstractTemporalPortalBlock(Properties properties, int part) {
        super(properties);
        this.part = part;

    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        for(int i=0; i<3;i++) {
            double angle = random.nextIntBetweenInclusive(0, 360) * Math.PI / 180;
            double d0 = (double) pos.getX() + 0.5;
            double d2 = (double) pos.getZ() + 0.5;
            level.addParticle(ParticleTypes.PORTAL, d0 + Math.cos(angle),  pos.getY(), d2 + Math.sin(angle), 0.0, 2.0, 0.0);
        }
        if (random.nextInt(100) == 0)
            level.playLocalSound(pos, SoundEvents.AMBIENT_CAVE.value(), SoundSource.BLOCKS, 1,1, false);
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

        if(level.isClientSide())
            return ItemInteractionResult.FAIL;

        List<ItemStack> reward = null;

        if(player.getCooldowns().isOnCooldown(stack.getItem()))
            return ItemInteractionResult.FAIL;

        if(stack.is(ModItems.STONE_BOOMERANG))
            reward = generateReward(level, state, pos, player, ModLootTables.STONE_AGE_REWARDS);
        if(stack.is(ModItems.IRON_BOOMERANG))
            reward = generateReward(level, state, pos, player, ModLootTables.IRON_AGE_REWARDS);
        if(stack.is(ModItems.INDUSTRIAL_BOOMERANG))
            reward = generateReward(level, state, pos, player, ModLootTables.INDUSTRIAL_AGE_REWARDS);
        if(stack.is(ModItems.INFORMATIONAL_BOOMERANG))
            reward = generateReward(level, state, pos, player, ModLootTables.INFORMATION_AGE_REWARDS);

        if(reward==null)
            return ItemInteractionResult.FAIL;

        if(level.getRandom().nextIntBetweenInclusive(0,10)>=7) {
            level.playLocalSound(pos, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.BLOCKS, 1, 1, false);
            summonDisaster(level, pos, player);
        }else {
            level.playLocalSound(pos, SoundEvents.NOTE_BLOCK_BIT.value(), SoundSource.BLOCKS, 1, 1, false);
            for (ItemStack itemStack : reward) {
                player.addItem(itemStack);
            }
        }


        EquipmentSlot equipmentSlot = (hand == InteractionHand.MAIN_HAND) ? EquipmentSlot.MAINHAND:EquipmentSlot.OFFHAND;
        stack.hurtAndBreak(1,player, equipmentSlot);
        player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        player.getCooldowns().addCooldown(stack.getItem(), 20);

        return ItemInteractionResult.SUCCESS;

    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }

    private List<ItemStack> generateReward(Level level, BlockState state, BlockPos pos, Player player, ResourceKey<LootTable> resourceKey){
        LootTable lootTable = level.getServer().reloadableRegistries().getLootTable(resourceKey);

        LootParams lootparams = (new LootParams.Builder((ServerLevel) level))
                .withParameter(LootContextParams.BLOCK_STATE, state)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .create(LootContextParamSets.BLOCK_USE);

        return lootTable.getRandomItems(lootparams, level.getRandom());
    }

    private void summonDisaster(Level level, BlockPos pos, Player player){
        int random = level.getRandom().nextIntBetweenInclusive(0,3);
        switch (random){
            case 0:
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,200,2));
                break;
            case 1:
                player.addEffect(new MobEffectInstance(MobEffects.POISON,200,2));
                break;
            case 2:
                for(int i=0;i<level.getRandom().nextIntBetweenInclusive(3,5); i++) {
                    Husk zombie = new Husk(EntityType.HUSK,level);
                    zombie.setBaby(true);
                    zombie.setPos(pos.above(3).getCenter());
                    level.addFreshEntity(zombie);
                }
                break;
            case 3:
                player.displayClientMessage(Component.literal(":3"),true);
        }
    }
}
