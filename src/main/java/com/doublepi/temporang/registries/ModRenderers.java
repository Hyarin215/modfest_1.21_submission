package com.doublepi.temporang.registries;

import com.doublepi.temporang.in_game.blocks.black_boxer.BlackBoxerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;


@OnlyIn(Dist.CLIENT)
public class ModRenderers {
//    public static final ModelLayerLocation SCANNER_MODEL = new ModelLayerLocation(
//            ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID, "scanner"), "main");

    public static void register(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntities.CANNON_BALL_ENTITY.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.BOOMERANG_ENTITY.get(), ThrownItemRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLACK_BOXER_BE.get(), BlackBoxerRenderer::new);
        //event.registerBlockEntityRenderer(ModBlockEntities.SCANNER_BE.get(), ScannerRenderer::new);

    }
}
