package com.doublepi.temporang.entities;

import com.doublepi.temporang.TemporangMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TemporangMod.MOD_ID);

    public static final String CANNON_BALL_NAME = "cannon_ball";
    public static final Supplier<EntityType<CannonBallEntity>> CANNON_BALL_ENTITY =
            ENTITY_TYPES.register(CANNON_BALL_NAME, () ->
                    EntityType.Builder.<CannonBallEntity>of(CannonBallEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).clientTrackingRange(10).setTrackingRange(4).build(CANNON_BALL_NAME));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
