package com.drogamleczna.industrialdeco.block.custom.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public abstract class RedstoneConductingHorizontalDirectionalBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    protected RedstoneConductingHorizontalDirectionalBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWER);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        int power_below = level.getSignal(pos.below(), Direction.UP);
        state = state.setValue(POWER,  power_below == 0 ? 0 : power_below -1);
        level.setBlock(pos, state, 15);
    }

    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return side == Direction.UP || side == Direction.DOWN ? blockState.getValue(POWER) : 0;
    }

    protected boolean isSignalSource(BlockState state) {
        return state.getValue(POWER) > 0;
    }
}
