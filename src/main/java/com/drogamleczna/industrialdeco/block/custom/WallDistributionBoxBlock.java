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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import javax.annotation.Nullable;

import static com.drogamleczna.industrialdeco.IndustrialDeco.WRENCH_TAG;

public class WallDistributionBoxBlock extends HorizontalDirectionalBlock {

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;

    static {
        SHAPE_S = Block.box(2,3,0,14,15,6);
        SHAPE_N = Block.box(2,3,10,14,15,16);
        SHAPE_E = Block.box(0,3,2,6,15,14);
        SHAPE_W = Block.box(10,3,2,16,15,14);

    }


    public WallDistributionBoxBlock(Properties pProperties) {

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
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()){
        pLevel.updateNeighborsAt(pPos, this);
        if(pPlayer.getMainHandItem().is(Items.STICK)) {
            this.changeType(pState, pLevel, pPos);
            return InteractionResult.SUCCESS;
        }else if(pPlayer.getMainHandItem().is(WRENCH_TAG)) {
            this.changeType(pState, pLevel, pPos);
            return InteractionResult.SUCCESS;
        }else{
            return InteractionResult.FAIL;
        }}else{
            return InteractionResult.SUCCESS;
        }

    }

    public void changeType(BlockState pState, Level pLevel, BlockPos pPos){

        switch (pState.getValue(FACING)) {
            case NORTH ->
                    pLevel.setBlock(pPos, pState.setValue(FACING, Direction.EAST), 0);
            case EAST ->
                    pLevel.setBlock(pPos, pState.setValue(FACING, Direction.WEST), 0);
            case WEST ->
                    pLevel.setBlock(pPos, pState.setValue(FACING, Direction.SOUTH), 0);
            case SOUTH ->
                    pLevel.setBlock(pPos, pState.setValue(FACING, Direction.NORTH), 0);

        }

    }
    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }
}
