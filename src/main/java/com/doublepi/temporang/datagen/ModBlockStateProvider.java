package com.doublepi.temporang.datagen;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TemporangMod.MOD_ID, exFileHelper);
    }
    @Override
    protected void registerStatesAndModels() {
//        blockWithItem(ModBlocks.BLACK_OPAL_BLOCK);
//        blockWithItem(ModBlocks.RAW_BLACK_OPAL_BLOCK);
//        blockWithItem(ModBlocks.BLACK_OPAL_ORE);
//        blockWithItem(ModBlocks.BLACK_OPAL_DEEPSLATE_ORE );
//        blockWithItem(ModBlocks.BLACK_OPAL_END_ORE);
//        blockWithItem(ModBlocks.BLACK_OPAL_NETHER_ORE);
//        blockWithItem(ModBlocks.MAGIC_BLOCK);
    }
    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}