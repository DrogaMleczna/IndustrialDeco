package com.drogamleczna.industrialdeco.block.custom;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Set;

public class WireBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final IntegerProperty POWER = IntegerProperty.create("power", 0,15);
    public WireBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(POWER, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        super.createBlockStateDefinition(builder);
        builder.add(POWER);
        builder.add(FACING);
        builder.add(FACE);
    }

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;

    static {
        SHAPE_N = Block.box(7,0,14,9,16,16);
        SHAPE_S = Block.box(7,0,0,9,16,2);
        SHAPE_W = Block.box(14,0,7,16,16,9);
        SHAPE_E = Block.box(0,0,7,2,16,9);
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
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return null;
    }


    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return (Integer)blockState.getValue(POWER);
    }

    protected boolean isSignalSource(BlockState state) {
        return state.is(this) ? (Integer)state.getValue(POWER)>0 : false;
    }


    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        int inPower = Math.max(level.getSignal(pos.below(), Direction.NORTH), level.getSignal(pos.above(), Direction.NORTH));
        if(inPower > 0){inPower -=1;}
        state = state.setValue(POWER, inPower);
        level.setBlock(pos, state, 3);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        int inPower = Math.max(level.getSignal(pos.below(), Direction.NORTH), level.getSignal(pos.above(), Direction.NORTH));
        if(inPower > 0){inPower -=1;}
        state = state.setValue(POWER, inPower);
        level.setBlock(pos, state, 3);
    }


}
