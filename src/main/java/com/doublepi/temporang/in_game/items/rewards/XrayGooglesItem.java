package com.doublepi.temporang.in_game.items.rewards;

import com.doublepi.temporang.in_game.items.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Iterator;

public class XrayGooglesItem extends ArmorItem {
    public static final int RANGE = 30;
    public XrayGooglesItem(Properties properties) {
        super(ArmorMaterials.CHAIN, Type.HELMET, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player) {
            if(!level.isClientSide() && hasItOn(player)) {
                effectGlowing(level, player);
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    private static void effectGlowing(Level level, Player player) {
        Vec3 pos = player.getEyePosition();

        AABB area = new AABB(pos.add(-RANGE,-RANGE,-RANGE),pos.add(RANGE,RANGE,RANGE));
        Iterator<LivingEntity> entityIterator = level.getEntitiesOfClass(LivingEntity.class, area).iterator();

        while(entityIterator.hasNext()) {
            LivingEntity livingEntity = entityIterator.next();
            MobEffectInstance effect = new MobEffectInstance(MobEffects.GLOWING,40,0,true,false,false);
            livingEntity.addEffect(effect);
        }
    }

    private static boolean hasItOn(Player player) {
        return player.getInventory().getArmor(3).is(ModItems.XRAY_GOOGLES);
    }
}
