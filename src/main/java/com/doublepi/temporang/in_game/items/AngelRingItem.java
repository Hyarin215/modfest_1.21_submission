package com.doublepi.temporang.in_game.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AngelRingItem extends Item {

    public AngelRingItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if(entity instanceof Player player){
            startFlying(player);
        }
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        stopFlying(player);
        return super.onDroppedByPlayer(item, player);
    }

    private void startFlying(Player player){
        player.getAbilities().mayfly = true;
        player.onUpdateAbilities();
    }

    private void stopFlying(Player player){
        player.getAbilities().flying = false;
        player.getAbilities().mayfly = false;
        player.onUpdateAbilities();
    }
}
