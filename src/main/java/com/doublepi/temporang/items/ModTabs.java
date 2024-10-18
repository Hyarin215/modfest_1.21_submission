package com.doublepi.temporang.items;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "temporang" namespace
    public static final DeferredRegister<CreativeModeTab> MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TemporangMod.MOD_ID);

    // Creates a creative tab with the id "temporang:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB
            = MOD_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.temporang")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.TIME_FRAGMENT.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                //main stuff
                output.accept(ModBlocks.TEMPORAL_PORTAL_MIDDLE.get());
                output.accept(ModItems.TIME_FRAGMENT.get());

                //stone age
                output.accept(ModItems.STONE_BOOMERANG.get());
                output.accept(ModBlocks.TOTEM.get());
                output.accept(ModItems.PORTABLE_CRAFTING_TABLE.get());
                output.accept(ModItems.CARVING_KNIFE.get());

                //iron age
                output.accept(ModItems.IRON_BOOMERANG.get());

                //industrial age
                output.accept(ModItems.INDUSTRIAL_BOOMERANG.get());
                output.accept(ModBlocks.BLOCK_COMPARATOR.get());

                //information age
                output.accept(ModItems.INFORMATIONAL_BOOMERANG.get());
                output.accept(ModItems.QUANTUM_ACCELERATOR.get());


            }).build());

    public static void register(IEventBus eventBus){
        MOD_TABS.register(eventBus);
    }

    public static void addToVanillaTabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            //event.accept(ModBlocks.EXAMPLE_BLOCK);
        }
    }
}
