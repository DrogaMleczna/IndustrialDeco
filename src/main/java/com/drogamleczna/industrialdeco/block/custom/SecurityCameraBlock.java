package com.drogamleczna.industrialdeco.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import static com.drogamleczna.industrialdeco.IndustrialDeco.WRENCH_TAG;

public class SecurityCameraBlock extends HorizontalDirectionalBlock {

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;

    static {
        SHAPE_W = Block.box(6.5,5,0,9.5,10,9);
        SHAPE_E = Block.box(6.5,5,7,9.5,10,16);
        SHAPE_S = Block.box(0,5,6.5,9,10,9.5);
        SHAPE_N = Block.box(7,5,6.5,16,10,9.5);
    }

    public SecurityCameraBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
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
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getCounterClockWise());
    }
    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.updateNeighborsAt(pPos, this);
        if(pPlayer.getMainHandItem().is(Items.STICK)) {
            this.changeType(pState, pLevel, pPos);
            return ItemInteractionResult.SUCCESS;
        }else if(pPlayer.getMainHandItem().is(WRENCH_TAG)) {
            this.changeType(pState, pLevel, pPos);
            return ItemInteractionResult.SUCCESS;
        }else{
            return ItemInteractionResult.FAIL;
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
}
