package com.doublepi.temporang.entities;

import com.doublepi.temporang.TemporangMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation TEMPORAL_PORTAL = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID, ModEntities.TEMPORAL_PORTAL_NAME), "main");
}
