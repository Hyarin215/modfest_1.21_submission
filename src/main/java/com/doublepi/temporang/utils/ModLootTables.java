package com.doublepi.temporang.utils;

import com.doublepi.temporang.TemporangMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ModLootTables {

    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet();
    private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS;
    public static final ResourceKey<LootTable> STONE_AGE_REWARDS;

    public ModLootTables(){}

    private static ResourceKey<LootTable> register(String path) {
        ResourceLocation lootTableLocation = ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID,path);
        return register(ResourceKey.create(Registries.LOOT_TABLE, lootTableLocation));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> name) {
        if (LOCATIONS.add(name)) {
            return name;
        } else {
            throw new IllegalArgumentException(String.valueOf(name.location()) + " is already a registered built-in loot table");
        }
    }

    public static Set<ResourceKey<LootTable>> all() {
        return IMMUTABLE_LOCATIONS;
    }

    static {
        IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);
        STONE_AGE_REWARDS = register("portal_rewards/stone_age_rewards");
    }
}
