package com.doublepi.temporang.entities.temporal_portal;

import com.doublepi.temporang.entities.ModEntities;
import com.doublepi.temporang.items.ModItems;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class TemporalPortalEntity extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public TemporalPortalEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.noPhysics=true;
    }


//    @Override
//    protected void registerGoals() {
//        //this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
//    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(ModItems.STONE_BOOMERANG.get());
    }


    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide())
            this.setupAnimationStates();
        else {
            BlockPos pos = this.blockPosition();
            Player player = this.level().getNearestPlayer((double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, 3.0, false);

            if (player != null) {
                double d0 = player.getX() - ((double) pos.getX() + 0.5);
                double d1 = player.getZ() - ((double) pos.getZ() + 0.5);
                Vec3 target = new Vec3(player.getX(), this.getEyeY(), player.getZ());
                this.lookAt(EntityAnchorArgument.Anchor.EYES, target);
            }
        }
    }


    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.TEMPORAL_PORTAL_ENTITY.get().create(serverLevel);
    }
}