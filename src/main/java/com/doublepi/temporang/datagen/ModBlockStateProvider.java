package com.doublepi.temporang.datagen;

import com.doublepi.temporang.TemporangMod;
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
    }


}