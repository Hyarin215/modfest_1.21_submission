package com.doublepi.temporang.registries;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.in_game.entities.hoverboard.HoverboardEntity;
import com.doublepi.temporang.in_game.entities.hoverboard.HoverboardModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TemporangMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        //event.registerLayerDefinition(ModRenderers.SCANNER_MODEL, ScannerRenderer::createBodyLayer);
        event.registerLayerDefinition(ModRenderers.HOVERBOARD, HoverboardModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        //event.put(ModEntities.HOVERBOARD_ENTITY.get(), HoverboardEntity.createAttributes().build());
    }
}
