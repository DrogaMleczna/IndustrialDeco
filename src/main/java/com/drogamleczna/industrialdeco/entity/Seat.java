package com.drogamleczna.industrialdeco.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

//Most of the code here is taken from MrCrayfish's Furniture Mod, which is licensed under MIT
//https://github.com/MrCrayfish/MrCrayfishFurnitureMod-Refurbished
public class Seat extends Entity {
    public Seat(EntityType<Seat> pEntityType, Level pLevel)
    {
        super(ModEntities.SEAT.get(), pLevel);
    }


    private Seat(EntityType<Seat> pEntityType, Level pLevel, BlockPos pos, double seatHeight, float seatYaw, boolean lockYaw)
    {
        this(pEntityType, pLevel);
        this.setPos(Vec3.atBottomCenterOf(pos).add(0, seatHeight, 0));
        this.setRot(seatYaw, 0);
    }


    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {}

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void tick()
    {
        super.tick();
        Level level = this.level();
        if(!level.isClientSide())
        {
            BlockPos pos = this.blockPosition();
            if(this.getPassengers().isEmpty() || level.isEmptyBlock(pos))
            {
                this.discard();
                level.updateNeighbourForOutputSignal(pos, level.getBlockState(pos).getBlock());
            }
        }
    }

    @Override
    protected void addPassenger(Entity entity)
    {
        super.addPassenger(entity);
        entity.setYRot(this.getYRot());
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity entity)
    {
        Vec3 pos = DismountHelper.findSafeDismountLocation(entity.getType(), this.level(), this.blockPosition(), false);
        if(pos != null)
        {
            return pos.add(0, 0.25, 0);
        }

        return super.getDismountLocationForPassenger(entity);
    }


    public static boolean sit(EntityType<Seat> entityType, Player player, BlockPos pos, double seatHeight, @Nullable Direction direction)
    {
        Level level = player.level();
        if(!level.isClientSide() && availableAt(level, pos))
        {
            float seatYaw = direction != null ? direction.toYRot() : player.getYRot();
            Seat seat = new Seat(entityType, level, pos, seatHeight, seatYaw, direction != null);
            level.addFreshEntity(seat);
            return player.startRiding(seat);
        }
        return false;
    }

    public static boolean availableAt(Level level, BlockPos pos)
    {
        return level.getEntitiesOfClass(Seat.class, new AABB(pos)).isEmpty();
    }
}

