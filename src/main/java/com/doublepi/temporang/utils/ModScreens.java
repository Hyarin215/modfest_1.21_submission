package com.doublepi.temporang.utils;

import com.doublepi.temporang.in_game.blocks.refinery.RefineryScreen;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class ModScreens {

    public static void register(RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.REFINERY_MENU.get(), RefineryScreen::new);
    }
}
