package com.drogamleczna.industrialdeco.block.custom;

import com.drogamleczna.industrialdeco.block.custom.util.TrainSignalState;
import com.drogamleczna.industrialdeco.block.custom.util.TrainSignalStateProperty;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class TrainSignalBlock extends PoleBlock {
    public static final TrainSignalStateProperty LIGHTS = TrainSignalStateProperty.create("lights",
            TrainSignalState.OFF, TrainSignalState.LEFT, TrainSignalState.RIGHT, TrainSignalState.ON);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_W;
    public static final VoxelShape SHAPE_E;

    static {
        SHAPE_S = Shapes.or(Block.box(0, 3, 5, 16, 13, 7), SHAPE);
        SHAPE_N = Shapes.or(Block.box(0, 3, 9, 16, 13, 11), SHAPE);
        SHAPE_E = Shapes.or(Block.box(5, 3, 0, 7, 13, 16), SHAPE);
        SHAPE_W = Shapes.or(Block.box(9, 3, 0, 11, 11, 16), SHAPE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case SOUTH -> SHAPE_S;
            case WEST -> SHAPE_W;
            case EAST -> SHAPE_E;
            default -> (SHAPE_N);
        };

    }

    public TrainSignalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIGHTS);
        builder.add(LIT);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection()).setValue(LIGHTS, TrainSignalState.OFF)
                .setValue(LIT, false);
    }


    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        int power_below = level.getSignal(pos.below(), Direction.UP);
        state = state.setValue(POWER,  power_below == 0 ? 0 : power_below -1);
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        if( state.getValue(POWER) >=12  ){
            state = state.setValue(LIGHTS, TrainSignalState.ON).setValue(LIT, true);
        } else if (state.getValue(POWER) >= 8) {
            state = state.setValue(LIGHTS, TrainSignalState.RIGHT).setValue(LIT, true);
        }else if (state.getValue(POWER) >= 4) {
            state = state.setValue(LIGHTS, TrainSignalState.LEFT).setValue(LIT, true);
        }else {
            state = state.setValue(LIGHTS, TrainSignalState.OFF).setValue(LIT, false);
        }
        level.setBlock(pos, state, 15);
    }
}
