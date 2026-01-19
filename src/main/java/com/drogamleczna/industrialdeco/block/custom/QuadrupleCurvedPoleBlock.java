package com.drogamleczna.industrialdeco.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import javax.annotation.Nullable;

public class QuadrupleCurvedPoleBlock extends HorizontalDirectionalBlock {

    public static final VoxelShape SHAPE;
    public static final VoxelShape SHAPE_ARM_NS;
    public static final VoxelShape SHAPE_ARM_WE;
    public static final VoxelShape BASE;

    static {
        BASE = Block.box(5,0,5,11,12,11);
        SHAPE_ARM_NS = Block.box(0,11,5,16,15,11);
        SHAPE_ARM_WE = Block.box(5,11,0,11,15,16);
        SHAPE = Shapes.or(BASE, SHAPE_ARM_NS, SHAPE_ARM_WE);
    }


    public QuadrupleCurvedPoleBlock(Properties pProperties) {

        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getCounterClockWise());
    }
}
