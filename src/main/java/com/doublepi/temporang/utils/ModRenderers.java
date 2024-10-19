package com.doublepi.temporang.utils;

import com.doublepi.temporang.blocks.ModBlockEntities;
import com.doublepi.temporang.blocks.ModBlocks;
import com.doublepi.temporang.blocks.cannon.CannonRenderer;
import com.doublepi.temporang.entities.ModEntities;
import com.doublepi.temporang.entities.temporal_portal.TemporalPortalRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ModRenderers {
    public static void register(){
        EntityRenderers.register(ModEntities.TEMPORAL_PORTAL_ENTITY.get(), TemporalPortalRenderer::new);

        //BlockEntityRenderers.register(ModBlockEntities.CANNON_BE.get(), CannonRenderer::new);
    }
}
