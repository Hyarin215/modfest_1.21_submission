package com.doublepi.temporang.datagen;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TemporangMod.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        basicItem(ModItems.TIME_FRAGMENT.get());
        basicItem(ModItems.CANNON_BALL.get());
        basicItem(ModItems.IRON_DUST.get());
        basicItem(ModItems.COPPER_DUST.get());
        basicItem(ModItems.GOLD_DUST.get());
        basicItem(ModItems.GENETIC_INJECTION.get());
    }
}