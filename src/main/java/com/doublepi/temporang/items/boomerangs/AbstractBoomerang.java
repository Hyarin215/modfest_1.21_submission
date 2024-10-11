package com.doublepi.temporang.items.boomerangs;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;

public class AbstractBoomerang extends Item {
    public Entity associatedBoomerang;

    public AbstractBoomerang(Properties properties) {
        super(properties);
    }


}
