package com.drogamleczna.industrialdeco.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class ChimneyBlock extends Block {
    public ChimneyBlock(Properties pProperties) {
        super(pProperties);
    }

    public static final VoxelShape SHAPE = Block.box(5,0,5,11,16,11);

    @Nullable
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
            if (pRandom.nextInt(1) == 0) {
                for(int i = 0; i < pRandom.nextInt(1) + 1; ++i) {
                    pLevel.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, (double)pPos.getX() + (double)0.5F, (double)pPos.getY() + (double)0.75F, (double)pPos.getZ() + (double)0.5F,
                            (double)(((pRandom.nextFloat() * 2) -1) / 200.0F), 0.05F, (double)(((pRandom.nextFloat() * 2) -1) / 200.0F));
                }
            }
        }


}

