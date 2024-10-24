package com.doublepi.temporang.utils;

import com.doublepi.temporang.TemporangMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTables {


    public static final ResourceKey<LootTable> STONE_AGE_REWARDS;
    public static final ResourceKey<LootTable> IRON_AGE_REWARDS;
    public static final ResourceKey<LootTable> INDUSTRIAL_AGE_REWARDS;
    public static final ResourceKey<LootTable> INFORMATION_AGE_REWARDS;

    public ModLootTables(){}

    private static ResourceKey<LootTable> register(String path) {
        ResourceLocation lootTableLocation = ResourceLocation.fromNamespaceAndPath(TemporangMod.MOD_ID,path);
        return ResourceKey.create(Registries.LOOT_TABLE, lootTableLocation);
    }



    static {
        STONE_AGE_REWARDS = register("portal_rewards/stone_age_rewards");
        IRON_AGE_REWARDS = register("portal_rewards/iron_age_rewards");
        INDUSTRIAL_AGE_REWARDS = register("portal_rewards/industrial_age_rewards");
        INFORMATION_AGE_REWARDS = register("portal_rewards/informational_age_rewards");
    }
}
