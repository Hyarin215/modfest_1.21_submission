package com.doublepi.temporang.blocks;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.blocks.totem.TotemBlock;
import com.doublepi.temporang.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TemporangMod.MOD_ID);

    public static final DeferredBlock<Block> BLOCK_COMPARATOR = registerBlock("block_comparator",
            ()-> new BlockComparator(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TOTEM = registerBlock("totem",
            ()-> new TotemBlock(BlockBehaviour.Properties.of()
                    .strength(4f).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> TEMPORAL_PORTAL_MIDDLE = registerBlock("temporal_portal",
            ()-> new AbstractTemporalPortalBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK)
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS),0));

    public static final DeferredBlock<Block> TEMPORAL_PORTAL_TOP = registerBlock("temporal_portal_top",
            ()-> new AbstractTemporalPortalBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK)
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS),1));

    public static final DeferredBlock<Block> TEMPORAL_PORTAL_BOTTOM = registerBlock("temporal_portal_bottom",
            ()-> new AbstractTemporalPortalBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK)
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS),-1));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
