package com.doublepi.temporang.utils;

import com.doublepi.temporang.entities.ModEntities;
import com.doublepi.temporang.entities.temporal_portal.TemporalPortalRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ModRenderers {
    public static void register(){
        EntityRenderers.register(ModEntities.TEMPORAL_PORTAL_ENTITY.get(), TemporalPortalRenderer::new);

        EntityRenderers.register(ModEntities.CANNON_BALL_ENTITY.get(), ThrownItemRenderer::new);

    }
}
