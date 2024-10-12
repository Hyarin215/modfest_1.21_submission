package com.doublepi.temporang.entities;

import com.doublepi.temporang.entities.temporal_portal.TemporalPortalRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class ModEntityRenderers {
    public static void register(){
        EntityRenderers.register(ModEntities.TEMPORAL_PORTAL_ENTITY.get(), TemporalPortalRenderer::new);
    }
}
