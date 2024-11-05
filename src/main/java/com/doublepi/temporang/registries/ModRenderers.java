package com.doublepi.temporang.registries;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.in_game.blocks.black_boxer.BlackBoxerRenderer;
import com.doublepi.temporang.in_game.entities.hoverboard.HoverboardRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;


@OnlyIn(Dist.CLIENT)
public class ModRenderers {

    public static final ModelLayerLocation HOVERBOARD = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID, ModEntities.HOVERBOARD_NAME), "main");

    public static void register(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntities.CANNON_BALL_ENTITY.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.HOVERBOARD_ENTITY.get(), HoverboardRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLACK_BOXER_BE.get(), BlackBoxerRenderer::new);
        //event.registerBlockEntityRenderer(ModBlockEntities.SCANNER_BE.get(), ScannerRenderer::new);

    }
}
