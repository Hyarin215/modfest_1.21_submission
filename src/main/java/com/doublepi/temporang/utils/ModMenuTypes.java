package com.doublepi.temporang.utils;

import com.doublepi.temporang.TemporangMod;
import com.doublepi.temporang.in_game.blocks.refinery.RefineryMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, TemporangMod.MOD_ID);
    public static final DeferredHolder<MenuType<?>, MenuType<RefineryMenu>> REFINERY_MENU =
            registerMenuType("refinery_menu", RefineryMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }
    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}