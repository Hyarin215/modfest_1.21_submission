package com.doublepi.temporang.in_game.entities;

import com.doublepi.temporang.registries.ModEntities;
import com.doublepi.temporang.registries.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BoomerangEntity extends ThrowableItemProjectile {
    public BoomerangEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }
    public BoomerangEntity(Level world, double x, double y, double z) {
        super(ModEntities.BOOMERANG_ENTITY.get(), x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.STONE_BOOMERANG.get();
    }
}
