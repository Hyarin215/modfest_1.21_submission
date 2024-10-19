package com.doublepi.temporang.utils;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.blocks.ModBlocks;
import com.doublepi.temporang.entities.ModEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModModelLayers {
    public static final ModelLayerLocation TEMPORAL_PORTAL = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID, ModEntities.TEMPORAL_PORTAL_NAME), "main");

    public static final ModelLayerLocation CANNON = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID, ModBlocks.CANNON_NAME), "main");
}
