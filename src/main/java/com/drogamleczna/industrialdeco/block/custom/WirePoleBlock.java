package com.drogamleczna.industrialdeco.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.logging.Logger;

public class WirePoleBlock extends HorizontalDirectionalBlock {

    //public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH,Direction.SOUTH,Direction.WEST,Direction.EAST);

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;
    public static final VoxelShape SHAPE_ARM_N;
    public static final VoxelShape SHAPE_ARM_S;
    public static final VoxelShape SHAPE_ARM_E;
    public static final VoxelShape SHAPE_ARM_W;
    public static final VoxelShape BASE;

    static {
        BASE = Block.box(6,0,6,10,16,10);
        SHAPE_ARM_E = Block.box(6,6,0,10,10,16);
        SHAPE_ARM_W = Block.box(6,6,0,10,10,16);
        SHAPE_ARM_N = Block.box(0,6,6,16,10,10);
        SHAPE_ARM_S = Block.box(0,6,6,16,10,10);
        SHAPE_N = Shapes.or(BASE, SHAPE_ARM_N);
        SHAPE_S = Shapes.or(BASE, SHAPE_ARM_S);
        SHAPE_E = Shapes.or(BASE, SHAPE_ARM_E);
        SHAPE_W = Shapes.or(BASE, SHAPE_ARM_W);

    };


    public WirePoleBlock(Properties pProperties) {

        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        if (direction == Direction.EAST){
            return SHAPE_E;
        }else if (direction == Direction.WEST){
            return SHAPE_W;
        }else if (direction == Direction.SOUTH){
            return SHAPE_S;
        }else{
            return SHAPE_N;
        }
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
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
}
