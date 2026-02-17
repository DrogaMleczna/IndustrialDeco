package com.drogamleczna.industrialdeco.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import javax.annotation.Nullable;

import static com.drogamleczna.industrialdeco.IndustrialDeco.WRENCH_TAG;

public class DistributionBoxBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock{

    public static final VoxelShape SHAPE_NS;
    public static final VoxelShape SHAPE_WE;

    static {
        SHAPE_NS = Block.box(2,0,4.5,14,16,11.5);
        SHAPE_WE = Block.box(4.5,0,2,11.5,16,14);
    }


    public DistributionBoxBlock(Properties pProperties) {

        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Nullable
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        if (direction == Direction.EAST || direction == Direction.WEST) {
            return SHAPE_WE;
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
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.updateNeighborsAt(pPos, this);
        if(pPlayer.getMainHandItem().is(Items.STICK)) {
            this.changeType(pState, pLevel, pPos);
            return InteractionResult.SUCCESS;
        }else if(pPlayer.getMainHandItem().is(WRENCH_TAG)) {
            this.changeType(pState, pLevel, pPos);
            return InteractionResult.SUCCESS;
        }else{
            return InteractionResult.FAIL;
        }
    }

    public void changeType(BlockState pState, Level pLevel, BlockPos pPos){

        switch (pState.getValue(FACING)) {
            case NORTH ->
                    pLevel.setBlock(pPos, pState.setValue(FACING, Direction.EAST), 10);
            case EAST ->
                    pLevel.setBlock(pPos, pState.setValue(FACING, Direction.WEST), 10);
            case WEST ->
                    pLevel.setBlock(pPos, pState.setValue(FACING, Direction.SOUTH), 10);
            case SOUTH ->
                    pLevel.setBlock(pPos, pState.setValue(FACING, Direction.NORTH), 10);

        }

    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }


}
