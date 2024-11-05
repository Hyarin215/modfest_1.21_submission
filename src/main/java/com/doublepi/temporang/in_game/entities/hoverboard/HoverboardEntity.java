package com.doublepi.temporang.in_game.entities.hoverboard;

import com.doublepi.temporang.registries.ModEntities;
import com.doublepi.temporang.registries.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class HoverboardEntity extends VehicleEntity implements Leashable {
    Leashable.LeashData leashData;

    public HoverboardEntity(Level level) {
        super(ModEntities.HOVERBOARD_ENTITY.get(), level);
    }

    public HoverboardEntity(EntityType<HoverboardEntity> hoverboardEntityEntityType, Level level) {
        super(ModEntities.HOVERBOARD_ENTITY.get(), level);
    }

    @Override
    protected Item getDropItem() {
        return ModItems.HOVERBOARD.get();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public @Nullable LeashData getLeashData() {
        return leashData;
    }


    @Override
    public void setLeashData(@Nullable Leashable.LeashData leashData) {
        this.leashData = leashData;
    }
}
