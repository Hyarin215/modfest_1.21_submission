package com.doublepi.temporang.registries;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.in_game.blocks.CannonBlock;
import com.doublepi.temporang.in_game.blocks.AbstractTemporalPortalBlock;
import com.doublepi.temporang.in_game.blocks.BlockComparator;
import com.doublepi.temporang.in_game.blocks.black_boxer.BlackBoxerBlock;
import com.doublepi.temporang.in_game.blocks.black_boxer.InputBlock;
import com.doublepi.temporang.in_game.blocks.black_boxer.OutputBlock;
import com.doublepi.temporang.in_game.blocks.drill.DrillBlock;
import com.doublepi.temporang.in_game.blocks.refinery.RefineryBlock;
import com.doublepi.temporang.in_game.blocks.scanner.ScannerBlock;
import com.doublepi.temporang.in_game.blocks.totem.TotemBlock;
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

    public static final DeferredBlock<Block> CANNON = registerBlock("cannon",
            ()-> new CannonBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));

    public static final DeferredBlock<Block> DRILL = registerBlock("redstone_drill",
            ()-> new DrillBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.WOOD).noOcclusion()));

    public static final DeferredBlock<Block> REFINERY = registerBlock("refinery",
            ()-> new RefineryBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TOTEM = registerBlock("totem",
            ()-> new TotemBlock(BlockBehaviour.Properties.of()
                    .strength(4f).sound(SoundType.WOOD).ignitedByLava()));

    public static final DeferredBlock<Block> SCANNER = registerBlock("scanner",
            ()-> new ScannerBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANVIL)));

    public static final DeferredBlock<Block> BLACK_BOXER = registerBlock("black_boxer",
            ()-> new BlackBoxerBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANVIL)));

    public static final DeferredBlock<Block> INPUT_BLOCK = registerBlock("input_block",
            ()-> new InputBlock(BlockBehaviour.Properties.of()
                    .strength(4f).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> OUTPUT_BLOCK = registerBlock("output_block",
            ()-> new OutputBlock(BlockBehaviour.Properties.of()
                    .strength(4f).sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TEMPORAL_PORTAL_MIDDLE = registerBlock("temporal_portal",
            ()-> new AbstractTemporalPortalBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK)
                    .strength(55.0F, 55.0F).noLootTable()
                    .noOcclusion().sound(SoundType.ANCIENT_DEBRIS),0));

    public static final DeferredBlock<Block> TEMPORAL_PORTAL_TOP = registerBlockNoItem("temporal_portal_top",
            ()-> new AbstractTemporalPortalBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK)
                    .strength(55.0F, 55.0F).noLootTable()
                    .noOcclusion().sound(SoundType.ANCIENT_DEBRIS),1));

    public static final DeferredBlock<Block> TEMPORAL_PORTAL_BOTTOM = registerBlockNoItem("temporal_portal_bottom",
            ()-> new AbstractTemporalPortalBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.BLOCK)
                    .strength(55.0F, 55F).noLootTable()
                    .noOcclusion().sound(SoundType.ANCIENT_DEBRIS),-1));




    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlockNoItem(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
