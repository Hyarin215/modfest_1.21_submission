package com.doublepi.temporang.datagen;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.registries.ModItems;
import com.doublepi.temporang.registries.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, TemporangMod.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.BOOMERANGS)
                .add(ModItems.STONE_BOOMERANG.get())
                .add(ModItems.IRON_BOOMERANG.get())
                .add(ModItems.INDUSTRIAL_BOOMERANG.get())
                .add(ModItems.INFORMATIONAL_BOOMERANG.get());
        tag(Tags.Items.DUSTS)
                .add(ModItems.IRON_DUST.get())
                .add(ModItems.GOLD_DUST.get())
                .add(ModItems.COPPER_DUST.get());
    }
}