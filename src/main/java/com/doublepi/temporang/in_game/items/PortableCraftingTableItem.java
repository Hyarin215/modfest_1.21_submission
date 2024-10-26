package com.doublepi.temporang.in_game.items;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PortableCraftingTableItem extends Item {

    public PortableCraftingTableItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            player.openMenu(this.getContainer(level, player.blockPosition()));
        }

        return super.use(level, player, hand);
    }

    private MenuProvider getContainer(Level level, BlockPos pos) {
        return new SimpleMenuProvider((windowId, playerInventory, playerEntity) -> new CraftingMenu(windowId, playerInventory, ContainerLevelAccess.create(level, pos)) {
            @Override
            public boolean stillValid(Player player) {
                return true;
            }
        }, Component.translatable("temporang.misc.crafting"));
    }
}
