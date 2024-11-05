package com.doublepi.temporang.in_game.entities.hoverboard;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.registries.ModRenderers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class HoverboardRenderer extends EntityRenderer<HoverboardEntity> {

    public HoverboardRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(HoverboardEntity hoverboardEntity) {
        return ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID,"textures/entity/hoverboard.png");
    }
}
