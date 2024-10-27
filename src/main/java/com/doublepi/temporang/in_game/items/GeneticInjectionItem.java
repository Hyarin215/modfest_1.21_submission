package com.doublepi.temporang.in_game.items;

import com.doublepi.temporang.registries.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GeneticInjectionItem extends Item {

    public GeneticInjectionItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        MobEffectInstance INJECTED = new MobEffectInstance(ModEffects.INJECTED,20*90,0);
        player.addEffect(INJECTED);
        player.playSound(SoundEvents.TOTEM_USE,1,1);
        EquipmentSlot equipmentSlot = (usedHand == InteractionHand.MAIN_HAND) ? EquipmentSlot.MAINHAND:EquipmentSlot.OFFHAND;
        player.getItemInHand(usedHand).hurtAndBreak(1,player,equipmentSlot);
        player.getCooldowns().addCooldown(this,20*5);
        return super.use(level, player, usedHand);
    }



}
