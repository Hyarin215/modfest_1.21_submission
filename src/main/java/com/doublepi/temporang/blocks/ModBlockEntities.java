package com.doublepi.temporang.blocks;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.blocks.drill.DrillBlockEntity;
import com.doublepi.temporang.blocks.totem.TotemBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TemporangMod.MOD_ID);

    public static final Supplier<BlockEntityType<TotemBlockEntity>> TOTEM_BE =
            BLOCK_ENTITIES.register("totem_be", () -> BlockEntityType.Builder.of(
                    TotemBlockEntity::new, ModBlocks.TOTEM.get()).build(null));

    public static final Supplier<BlockEntityType<DrillBlockEntity>> DRILL_BE =
            BLOCK_ENTITIES.register("drill_be", () -> BlockEntityType.Builder.of(
                    DrillBlockEntity::new, ModBlocks.DRILL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
