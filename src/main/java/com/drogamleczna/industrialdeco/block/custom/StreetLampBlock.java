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

public class StreetLampBlock extends HorizontalDirectionalBlock {

    //public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH,Direction.SOUTH,Direction.WEST,Direction.EAST);

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;
    public static final VoxelShape SHAPE_HEAD_N;
    public static final VoxelShape SHAPE_HEAD_S;
    public static final VoxelShape SHAPE_HEAD_E;
    public static final VoxelShape SHAPE_HEAD_W;
    public static final VoxelShape SHAPE_ARM_N;
    public static final VoxelShape SHAPE_ARM_S;
    public static final VoxelShape SHAPE_ARM_E;
    public static final VoxelShape SHAPE_ARM_W;

    static {
        SHAPE_ARM_N = Block.box(6,11,0,10,15,16);
        SHAPE_ARM_S = Block.box(6,11,0,10,15,16);
        SHAPE_ARM_W = Block.box(0,11,6,16,15,10);
        SHAPE_ARM_E = Block.box(0,11,6,16,15,10);
        SHAPE_HEAD_N = Block.box(4,10,0,12,16,12);
        SHAPE_HEAD_S = Block.box(4,10,4,12,16,16);
        SHAPE_HEAD_W = Block.box(0,10,4,12,16,12);
        SHAPE_HEAD_E = Block.box(4,10,4,16,16,12);
        SHAPE_N = Shapes.or(SHAPE_HEAD_N, SHAPE_ARM_N);
        SHAPE_S = Shapes.or(SHAPE_HEAD_S, SHAPE_ARM_S);
        SHAPE_E = Shapes.or(SHAPE_HEAD_E, SHAPE_ARM_E);
        SHAPE_W = Shapes.or(SHAPE_HEAD_W, SHAPE_ARM_W);

    };


    public StreetLampBlock(Properties pProperties) {

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
