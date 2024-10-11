package com.doublepi.temporang.datagen;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.items.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TemporangMod.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        basicItem(ModItems.STONE_BOOMERANG.get());
        basicItem(ModItems.PORTABLE_CRAFTING_TABLE.get());
    }
}