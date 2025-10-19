package com.drogamleczna.industrialdeco.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MetalFenceBlock extends HorizontalDirectionalBlock {

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;
    public static final VoxelShape SHAPE_N_P;
    public static final VoxelShape SHAPE_S_P;
    public static final VoxelShape SHAPE_E_P;
    public static final VoxelShape SHAPE_W_P;
    public static final VoxelShape SHAPE_N_P2;
    public static final VoxelShape SHAPE_S_P2;
    public static final VoxelShape SHAPE_E_P2;
    public static final VoxelShape SHAPE_W_P2;
    public static final VoxelShape LEG_NW;
    public static final VoxelShape LEG_NE;
    public static final VoxelShape LEG_SW;
    public static final VoxelShape LEG_SE;
    static {
        LEG_SE = Block.box(0,0,0,2,19,2);
        LEG_NE = Block.box(0,0,14,2,19,16);
        LEG_SW = Block.box(14,0,0,16,19,2);
        LEG_NW = Block.box(14,0,14,16,19,16);
        SHAPE_S_P = Block.box(1,17,0,15,20,2);
        SHAPE_N_P = Block.box(1,17,14,15,20,16);
        SHAPE_E_P = Block.box(0,17,1,2,20,15);
        SHAPE_W_P = Block.box(14,17,1,16,20,15);
        SHAPE_S_P2 = Block.box(0,10,0,16,12,2);
        SHAPE_N_P2 = Block.box(0,10,14,16,12,16);
        SHAPE_E_P2 = Block.box(0,10,0,2,12,16);
        SHAPE_W_P2 = Block.box(14,10,0,16,12,16);
        SHAPE_S = Shapes.or(Shapes.or(LEG_SW, LEG_SE), Shapes.or(SHAPE_S_P, SHAPE_S_P2));
        SHAPE_N = Shapes.or(Shapes.or(LEG_NW, LEG_NE), Shapes.or(SHAPE_N_P, SHAPE_N_P2));
        SHAPE_W = Shapes.or(Shapes.or(LEG_SW, LEG_NW), Shapes.or(SHAPE_W_P, SHAPE_W_P2));
        SHAPE_E = Shapes.or(Shapes.or(LEG_SE, LEG_NE), Shapes.or(SHAPE_E_P, SHAPE_E_P2));
    };

    public MetalFenceBlock(Properties pProperties) {
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
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }
}
