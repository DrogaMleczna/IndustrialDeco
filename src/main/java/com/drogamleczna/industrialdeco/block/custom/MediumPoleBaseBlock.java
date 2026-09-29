package com.drogamleczna.industrialdeco.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MediumPoleBaseBlock extends Block {
    public static final VoxelShape SHAPE = Block.box(5,0,5,11,16,11);

    public MediumPoleBaseBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState){
        return RenderShape.MODEL;
    }
}
