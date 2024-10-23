package com.doublepi.temporang.entities;

import com.doublepi.temporang.items.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CannonBallEntity extends ThrowableItemProjectile {
    public CannonBallEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public CannonBallEntity(Level world, double x, double y, double z) {
        super(ModEntities.CANNON_BALL_ENTITY.get(), x, y, z, world);
    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        Vec3 pos = this.position();
        this.level().explode(this,pos.x(),pos.y(),pos.z(),3, Level.ExplosionInteraction.TNT);
        this.discard();
    }



    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.CANNON_BALL.get();
    }
}
