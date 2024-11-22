package com.doublepi.temporang.in_game.items;

import com.doublepi.temporang.registries.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Iterator;

public class InjectedEffect extends MobEffect {
    public boolean couldFly;
    public InjectedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onEffectStarted(LivingEntity livingEntity, int amplifier) {
        if(livingEntity instanceof Player player)
            couldFly = player.getAbilities().mayfly;
        super.onEffectStarted(livingEntity, amplifier);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        MobEffectInstance REGEN = new MobEffectInstance(MobEffects.REGENERATION, 20, amplifier+1);
        MobEffectInstance ABSORPTION = new MobEffectInstance(MobEffects.ABSORPTION, 20, amplifier+1);
        MobEffectInstance DAMAGE_RESIST = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20, amplifier+1);
        livingEntity.addEffect(REGEN);
        livingEntity.addEffect(ABSORPTION);
        livingEntity.addEffect(DAMAGE_RESIST);
        if(livingEntity instanceof Player player) {

            player.getAbilities().mayfly=true;
            if (getCurrentDuration(livingEntity) <= 5) {
                player.getAbilities().flying=false;
                player.getAbilities().mayfly=couldFly;
            }
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    private int getCurrentDuration(LivingEntity livingEntity){
        for (MobEffectInstance currentEffect : livingEntity.getActiveEffects()) {
            if (currentEffect.is(ModEffects.INJECTED))
                return currentEffect.getDuration();
        }
        return -1;
    }

}
