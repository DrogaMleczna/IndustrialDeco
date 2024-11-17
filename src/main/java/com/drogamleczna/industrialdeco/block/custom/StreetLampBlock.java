package com.drogamleczna.industrialdeco.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.logging.Logger;

public class StreetLampBlock extends HorizontalDirectionalBlock {

    //public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH,Direction.SOUTH,Direction.WEST,Direction.EAST);

    public static final VoxelShape SHAPE_NS = Block.box(3,9,1,13,16,15);
    public static final VoxelShape SHAPE_EW = Block.box(1,9,3,15,16,13);

    public StreetLampBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        System.out.println(direction);

        if (direction == Direction.EAST || direction == Direction.WEST){
            return SHAPE_EW;
        }else{
            return SHAPE_NS;
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
}
