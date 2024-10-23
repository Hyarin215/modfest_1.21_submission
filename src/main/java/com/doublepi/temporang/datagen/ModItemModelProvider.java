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
        basicItem(ModItems.TIME_FRAGMENT.get());
        basicItem(ModItems.CANNON_BALL.get());
        basicItem(ModItems.QUANTUM_ACCELERATOR.get());
    }
}