package net.eya.penumbra.common.entity;

import net.eya.penumbra.foundation.EffectInit;
import net.eya.penumbra.foundation.EntityInit;
import net.eya.penumbra.foundation.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class ShackleProjectile extends ThrownItemEntity {
    public ShackleProjectile(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    public ShackleProjectile(LivingEntity entity, World world) {
        super(EntityInit.SHACKLE_PROJECTILE_ENTITY_TYPE, entity, world);
    }
    @Override
    protected Item getDefaultItem() {
        return ItemInit.ELDRITCH_SHACKLE;
    }
    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.getEntityWorld().isClient) {
            if (entityHitResult.getEntity() instanceof PlayerEntity player) {
                player.addStatusEffect(new StatusEffectInstance(EffectInit.CHAINED, 60, 1, false, false, false));
            }
            this.discard();
        }
    }
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
    }
}
