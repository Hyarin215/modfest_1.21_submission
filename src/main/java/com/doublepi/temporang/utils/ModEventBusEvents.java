package com.doublepi.temporang.utils;

import com.doublepi.temporang.entities.ModEntities;
import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.entities.temporal_portal.TemporalPortalEntity;
import com.doublepi.temporang.entities.temporal_portal.TemporalPortalModel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TemporangMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.TEMPORAL_PORTAL, TemporalPortalModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TEMPORAL_PORTAL_ENTITY.get(), TemporalPortalEntity.createAttributes().build());
    }
}
