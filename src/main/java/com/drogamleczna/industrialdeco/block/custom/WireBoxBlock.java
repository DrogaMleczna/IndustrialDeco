package com.drogamleczna.industrialdeco.block.custom;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class WireBoxBlock extends FaceAttachedHorizontalDirectionalBlock {
    public WireBoxBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final IntegerProperty WIRE = IntegerProperty.create("wire", 0, 3);

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;
    public static final VoxelShape SHAPE_U;
    public static final VoxelShape SHAPE_D;

    static {
        SHAPE_S = Block.box(4,4,0,12,12,3);
        SHAPE_N = Block.box(4,4,13,12,12,16);
        SHAPE_E = Block.box(0,4,4,3,12,12);
        SHAPE_W = Block.box(13,4,4,16,12,12);
        SHAPE_U = Block.box(4,0,4,12,3,12);
        SHAPE_D = Block.box(4,13,4,12,16,12);

    }

    @Nullable
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        if(pState.getValue(FACE) == AttachFace.FLOOR){
            return SHAPE_U;
        }
        else if(pState.getValue(FACE) == AttachFace.CEILING){
            return SHAPE_D;
        }else {
            if (direction == Direction.EAST) {
                return SHAPE_E;
            } else if (direction == Direction.WEST) {
                return SHAPE_W;
            } else if (direction == Direction.SOUTH) {
                return SHAPE_S;
            } else {
                return SHAPE_N;
            }
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
        builder.add(FACE);
        builder.add(POWERED);
        builder.add(WIRE);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        int inPower = Math.max(pLevel.getSignal(pPos.below(), Direction.NORTH), pLevel.getSignal(pPos.above(), Direction.NORTH));
        int in_wire = (pLevel.getBlockState(pPos.above()).is(ModBlocks.WIRE_BLOCK.get())
                || pLevel.getBlockState(pPos.above()).canRedstoneConnectTo(pLevel, pPos.above(), Direction.DOWN) ? 1:0)
                + (pLevel.getBlockState(pPos.below()).is(ModBlocks.WIRE_BLOCK.get())
                || pLevel.getBlockState(pPos.below()).canRedstoneConnectTo(pLevel, pPos.below(), Direction.UP) ? 2:0);
        pState = pState.setValue(POWERED, inPower>0).setValue(WIRE, in_wire);
        pLevel.setBlock(pPos, pState, 3);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        int inPower = Math.max(level.getSignal(pos.below(), Direction.NORTH), level.getSignal(pos.above(), Direction.NORTH));
        state = state.setValue(POWERED, inPower>0);
        level.setBlock(pos, state, 3);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        for(Direction direction : pContext.getNearestLookingDirections()) {
            BlockState blockstate;
            BlockPos pPos = pContext.getClickedPos();
            int is_wire_above = pContext.getLevel().getBlockState(pPos.above()).is(ModBlocks.WIRE_BLOCK.get())
                    || pContext.getLevel().getBlockState(pPos.above()).canRedstoneConnectTo(pContext.getLevel(), pPos.above(), Direction.DOWN) ? 1 : 0;
            int is_wire_below = pContext.getLevel().getBlockState(pPos.below()).is(ModBlocks.WIRE_BLOCK.get())
                    || pContext.getLevel().getBlockState(pPos.below()).canRedstoneConnectTo(pContext.getLevel(), pPos.below(), Direction.UP)? 2 : 0;
            int wires = is_wire_below + is_wire_above;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = (BlockState)((BlockState)this.defaultBlockState().setValue(FACE, direction == Direction.UP ?
                        AttachFace.CEILING : AttachFace.FLOOR)).setValue(FACING, pContext.getHorizontalDirection()).setValue(WIRE, wires);
            }else{
                blockstate = (BlockState)((BlockState)this.defaultBlockState().setValue(FACE, AttachFace.WALL)).setValue(FACING,
                        direction.getOpposite()).setValue(WIRE, wires);
            }

            if (blockstate.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                return blockstate;
            }
        }

        return null;

    }

    @Override
    public boolean isSignalSource(BlockState pState) {
        return true;
    }

    @Override
    public int getSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection) {
        return pLevel.getBlockState(pPos.below()).is(ModBlocks.WIRE_BOX.get()) ||
                pLevel.getBlockState(pPos.above()).is(ModBlocks.WIRE_BOX.get()) ? 15 :
                Math.max(pLevel.getBlockState(pPos.below()).getSignal(pLevel, pPos.below(), Direction.NORTH),
                pLevel.getBlockState(pPos.above()).getSignal(pLevel, pPos.above(), Direction.NORTH))>0?15:0;
    }

    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return (Boolean)blockState.getValue(POWERED) && getConnectedDirection(blockState) == side ? 15 : 0;
    }
}
