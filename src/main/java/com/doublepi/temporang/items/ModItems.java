package com.doublepi.temporang.items;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.items.boomerangs.StoneBoomerangItem;
import com.doublepi.temporang.items.rewards.PortableCraftingTableItem;
import com.doublepi.temporang.items.rewards.QuantumAcceleratorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TemporangMod.MOD_ID);

    public static final DeferredItem<Item> STONE_BOOMERANG = ITEMS.register("stone_boomerang",
        ()-> new StoneBoomerangItem(new Item.Properties().durability(64).stacksTo(1)));

    public static final DeferredItem<Item> PORTABLE_CRAFTING_TABLE = ITEMS.register("portable_crafting_table",
        ()-> new PortableCraftingTableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> QUANTUM_ACCELERATOR= ITEMS.register("quantum_accelerator",
            ()-> new QuantumAcceleratorItem(new Item.Properties().stacksTo(1).durability(2048)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
