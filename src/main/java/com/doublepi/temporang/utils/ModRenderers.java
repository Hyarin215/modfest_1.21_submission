package com.doublepi.temporang.utils;

import com.doublepi.temporang.entities.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ModRenderers {
    public static void register(){
        EntityRenderers.register(ModEntities.CANNON_BALL_ENTITY.get(), ThrownItemRenderer::new);

    }
}
