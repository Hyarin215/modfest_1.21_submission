package com.doublepi.temporang.entities.temporal_portal;


import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.entities.ModEntities;
import com.doublepi.temporang.entities.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TemporalPortalRenderer extends MobRenderer<TemporalPortalEntity, TemporalPortalModel> {

    public TemporalPortalRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TemporalPortalModel(pContext.bakeLayer(ModModelLayers.TEMPORAL_PORTAL)), 0.75f);
    }
    @Override
    public ResourceLocation getTextureLocation(TemporalPortalEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID, "textures/entity/"+ ModEntities.TEMPORAL_PORTAL_NAME+"/texture.png");
    }

    @Override
    public void render(TemporalPortalEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}