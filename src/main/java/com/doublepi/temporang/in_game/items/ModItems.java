package com.doublepi.temporang.in_game.items;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.in_game.items.rewards.CarvingKnifeItem;
import com.doublepi.temporang.in_game.items.rewards.PortableCraftingTableItem;
import com.doublepi.temporang.in_game.items.rewards.QuantumAcceleratorItem;
import com.doublepi.temporang.in_game.items.rewards.XrayGooglesItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TemporangMod.MOD_ID);

    // Boomerangs
    public static final DeferredItem<Item> STONE_BOOMERANG = ITEMS.register("stone_boomerang",
        ()-> new Item(new Item.Properties().durability(64).stacksTo(1)));

    public static final DeferredItem<Item> IRON_BOOMERANG= ITEMS.register("iron_boomerang",
            ()-> new Item(new Item.Properties().durability(128).stacksTo(1)));

    public static final DeferredItem<Item> INDUSTRIAL_BOOMERANG = ITEMS.register("industrial_boomerang",
            ()-> new Item(new Item.Properties().durability(256).stacksTo(1)));

    public static final DeferredItem<Item> INFORMATIONAL_BOOMERANG = ITEMS.register("informational_boomerang",
            ()-> new Item(new Item.Properties().durability(512).stacksTo(1)));

    // Stone Age Rewards
    public static final DeferredItem<Item> PORTABLE_CRAFTING_TABLE = ITEMS.register("portable_crafting_table",
        ()-> new PortableCraftingTableItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> CARVING_KNIFE = ITEMS.register("carving_knife",
            ()-> new CarvingKnifeItem(new Item.Properties().stacksTo(1).durability(128)));

    //Iron Age Rewards
    public static final DeferredItem<Item> CANNON_BALL = ITEMS.register("cannon_ball",
            ()-> new Item(new Item.Properties()));


    public static final DeferredItem<Item> COPPER_DUST = ITEMS.register("copper_dust",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IRON_DUST = ITEMS.register("iron_dust",
            ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_DUST = ITEMS.register("gold_dust",
            ()-> new Item(new Item.Properties()));

    //Digital Age Rewards
    public static final DeferredItem<Item> XRAY_GOOGLES = ITEMS.register("xray_googles",
            ()-> new XrayGooglesItem(new Item.Properties()));


    public static final DeferredItem<Item> TIME_FRAGMENT = ITEMS.register("time_fragment",
            ()-> new Item(new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE,true)));

    public static final DeferredItem<Item> QUANTUM_ACCELERATOR= ITEMS.register("quantum_accelerator",
            ()-> new QuantumAcceleratorItem(new Item.Properties().stacksTo(1).durability(2048)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
