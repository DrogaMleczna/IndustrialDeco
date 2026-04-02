package com.drogamleczna.industrialdeco.block.custom;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.ModBlocks;
import com.drogamleczna.industrialdeco.block.custom.util.WireBoxType;
import com.google.common.collect.Sets;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Set;

import static com.drogamleczna.industrialdeco.IndustrialDeco.WRENCH_TAG;

public class WireBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final IntegerProperty POWER = IntegerProperty.create("power", 0,15);
    public static final IntegerProperty LONG_SIDE = IntegerProperty.create("long_side", 0,3);
    public static final BooleanProperty HORIZONTAL = BooleanProperty.create("horizontal");
    public WireBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(POWER, 0).setValue(HORIZONTAL, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        super.createBlockStateDefinition(builder);
        builder.add(POWER);
        builder.add(FACING);
        builder.add(FACE);
        builder.add(LONG_SIDE);
        builder.add(HORIZONTAL);
    }

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;
    public static final VoxelShape SHAPE_N_H;
    public static final VoxelShape SHAPE_S_H;
    public static final VoxelShape SHAPE_E_H;
    public static final VoxelShape SHAPE_W_H;

    static {
        SHAPE_N = Block.box(7,0,14,9,16,16);
        SHAPE_S = Block.box(7,0,0,9,16,2);
        SHAPE_W = Block.box(14,0,7,16,16,9);
        SHAPE_E = Block.box(0,0,7,2,16,9);
        SHAPE_N_H = Block.box(0,7,14,16,9,16);
        SHAPE_S_H = Block.box(0,7,0,16,9,2);
        SHAPE_W_H = Block.box(14,7,0,16,9,16);
        SHAPE_E_H = Block.box(0,7,0,2,9,16);
    }

    @Nullable
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        if(pState.getValue(HORIZONTAL)){
            if (direction == Direction.EAST){
                return SHAPE_E_H;
            }else if (direction == Direction.WEST){
                return SHAPE_W_H;
            }else if (direction == Direction.SOUTH){
                return SHAPE_S_H;
            }else{
                return SHAPE_N_H;
            }
        }
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

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.updateNeighborsAt(pPos, this);
        if(pPlayer.getMainHandItem().is(Items.STICK) && pState.getValue(FACE) == AttachFace.WALL) {
            if(pState.getValue(HORIZONTAL)){
                pLevel.setBlock(pPos, pState.setValue(HORIZONTAL, false), 10);
            }else{
                pLevel.setBlock(pPos, pState.setValue(HORIZONTAL, true), 10);
            }
            return ItemInteractionResult.SUCCESS;
        }else if(pPlayer.getMainHandItem().is(WRENCH_TAG) && pState.getValue(FACE) == AttachFace.WALL) {
            if(pState.getValue(HORIZONTAL)){
                pLevel.setBlock(pPos, pState.setValue(HORIZONTAL, false), 10);
            }else{
                pLevel.setBlock(pPos, pState.setValue(HORIZONTAL, true), 10);
            }
            return ItemInteractionResult.SUCCESS;
        }else{
            return ItemInteractionResult.FAIL;
        }
    }

    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if(!blockState.getValue(HORIZONTAL)){
            return side == Direction.DOWN || side == Direction.UP ? blockState.getValue(POWER) : 0;
        }else{
            if(blockState.getValue(FACING) == Direction.NORTH || blockState.getValue(FACING) == Direction.SOUTH){
                return side == Direction.WEST || side == Direction.EAST ? blockState.getValue(POWER) : 0;
            }else{
                return side == Direction.NORTH || side == Direction.SOUTH ? blockState.getValue(POWER) : 0;
            }
        }
    }

    protected boolean isSignalSource(BlockState state) {
        return state.is(this) ? (Integer)state.getValue(POWER)>0 : false;
    }


    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        BlockState newState = state.setValue(POWER, getPower(level, pos, state)).setValue(LONG_SIDE, sides(level, state, pos));
        if(state != newState) {
            level.updateNeighborsAt(pos.above(), this);
            level.updateNeighborsAt(pos.below(), this);
            level.updateNeighborsAt(pos.west(), this);
            level.updateNeighborsAt(pos.east(), this);
            level.updateNeighborsAt(pos.south(), this);
            level.updateNeighborsAt(pos.north(), this);
        }
        level.setBlock(pos, newState, 3);
    }

    private int getPower(Level level, BlockPos pos, BlockState state){
        Direction facing = state.getValue(FACING);
        int inPower = 0;

        if(state.getValue(HORIZONTAL)){
            if(facing == Direction.NORTH || facing == Direction.SOUTH){
                if(!(level.getBlockState(pos.west()).is(ModBlocks.WIRE_BLOCK.get()) || level.getBlockState(pos.east()).is(ModBlocks.WIRE_BLOCK.get()))){
                    inPower = Math.max(level.getSignal(pos.east(), Direction.EAST), level.getSignal(pos.west(), Direction.WEST));
                }else if ((level.getBlockState(pos.west()).is(ModBlocks.WIRE_BLOCK.get())) && (level.getBlockState(pos.east()).is(ModBlocks.WIRE_BLOCK.get()))) {
                    if (!level.getBlockState(pos.west()).getValue(HORIZONTAL) && !level.getBlockState(pos.east()).getValue(HORIZONTAL)) {
                        inPower = 0;
                    } else if (!level.getBlockState(pos.east()).getValue(HORIZONTAL)) {
                        inPower = level.getSignal(pos.west(), Direction.WEST);
                    } else if (!level.getBlockState(pos.west()).getValue(HORIZONTAL)) {
                        inPower = level.getSignal(pos.east(), Direction.EAST);
                    }else{
                        inPower = Math.max(level.getSignal(pos.east(), Direction.EAST), level.getSignal(pos.west(), Direction.WEST));
                    }
                }else if (level.getBlockState(pos.west()).is(ModBlocks.WIRE_BLOCK.get())){
                    if (!level.getBlockState(pos.west()).getValue(HORIZONTAL)){
                        inPower = level.getSignal(pos.east(), Direction.EAST);
                    }else{
                        inPower = Math.max(level.getSignal(pos.east(), Direction.EAST), level.getSignal(pos.west(), Direction.WEST));
                    }
                }else{
                    if (!level.getBlockState(pos.east()).getValue(HORIZONTAL)){
                        inPower = level.getSignal(pos.west(), Direction.WEST);
                    }else{
                        inPower = Math.max(level.getSignal(pos.east(), Direction.EAST), level.getSignal(pos.west(), Direction.WEST));
                    }
                }
            }else{
                if(!(level.getBlockState(pos.north()).is(ModBlocks.WIRE_BLOCK.get()) || level.getBlockState(pos.south()).is(ModBlocks.WIRE_BLOCK.get()))){
                    inPower = Math.max(level.getSignal(pos.south(), Direction.NORTH), level.getSignal(pos.north(), Direction.SOUTH));
                }else if ((level.getBlockState(pos.north()).is(ModBlocks.WIRE_BLOCK.get())) && (level.getBlockState(pos.south()).is(ModBlocks.WIRE_BLOCK.get()))) {
                    if (!level.getBlockState(pos.north()).getValue(HORIZONTAL) && !level.getBlockState(pos.south()).getValue(HORIZONTAL)) {
                        inPower = 0;
                    } else if (!level.getBlockState(pos.south()).getValue(HORIZONTAL)) {
                        inPower = level.getSignal(pos.north(), Direction.SOUTH);
                    } else if (!level.getBlockState(pos.north()).getValue(HORIZONTAL)) {
                        inPower = level.getSignal(pos.south(), Direction.NORTH);
                    }else{
                        inPower = Math.max(level.getSignal(pos.south(), Direction.NORTH), level.getSignal(pos.north(), Direction.SOUTH));
                    }
                }else if (level.getBlockState(pos.north()).is(ModBlocks.WIRE_BLOCK.get())){
                    if (!level.getBlockState(pos.north()).getValue(HORIZONTAL)){
                        inPower = level.getSignal(pos.south(), Direction.NORTH);
                    }else{
                        inPower = Math.max(level.getSignal(pos.south(), Direction.NORTH), level.getSignal(pos.north(), Direction.SOUTH));
                    }
                }else{
                    if (!level.getBlockState(pos.south()).getValue(HORIZONTAL)){
                        inPower = level.getSignal(pos.north(), Direction.SOUTH);
                    }else{
                        inPower = Math.max(level.getSignal(pos.south(), Direction.NORTH), level.getSignal(pos.north(), Direction.SOUTH));
                    }
                }
            }
        }else {
            if(!(level.getBlockState(pos.above()).is(ModBlocks.WIRE_BLOCK.get()) || level.getBlockState(pos.below()).is(ModBlocks.WIRE_BLOCK.get()))){
                inPower = Math.max(level.getSignal(pos.below(), Direction.UP), level.getSignal(pos.above(), Direction.DOWN));
            }else if ((level.getBlockState(pos.above()).is(ModBlocks.WIRE_BLOCK.get())) && (level.getBlockState(pos.below()).is(ModBlocks.WIRE_BLOCK.get()))) {
                if (level.getBlockState(pos.above()).getValue(HORIZONTAL) && level.getBlockState(pos.below()).getValue(HORIZONTAL)) {
                    inPower = 0;
                } else if (level.getBlockState(pos.below()).getValue(HORIZONTAL)) {
                    inPower = level.getSignal(pos.above(), Direction.DOWN);
                } else if (level.getBlockState(pos.above()).getValue(HORIZONTAL)) {
                    inPower = level.getSignal(pos.below(), Direction.UP);
                }else{
                    inPower = Math.max(level.getSignal(pos.below(), Direction.UP), level.getSignal(pos.above(), Direction.DOWN));
                }
            }else if (level.getBlockState(pos.above()).is(ModBlocks.WIRE_BLOCK.get())){
                if (level.getBlockState(pos.above()).getValue(HORIZONTAL)){
                    inPower = level.getSignal(pos.below(), Direction.UP);
                }else{
                    inPower = Math.max(level.getSignal(pos.below(), Direction.UP), level.getSignal(pos.above(), Direction.DOWN));
                }
            }else{
                if (level.getBlockState(pos.below()).getValue(HORIZONTAL)){
                    inPower = level.getSignal(pos.above(), Direction.DOWN);
                }else{
                    inPower = Math.max(level.getSignal(pos.below(), Direction.UP), level.getSignal(pos.above(), Direction.DOWN));
                }
            }
        }
        if(inPower > 0){inPower -=1;}

        return inPower;
    }

    private int sides(Level level, BlockState state, BlockPos pos){
        Direction facing = state.getValue(FACING);

        BlockState above;
        BlockState below;

        if(state.getValue(HORIZONTAL)){
            if(facing == Direction.SOUTH){
                above = level.getBlockState(pos.west());
                below = level.getBlockState(pos.east());
            } else if (facing == Direction.NORTH) {
                above = level.getBlockState(pos.east());
                below = level.getBlockState(pos.west());
            } else if(facing == Direction.WEST){
                above = level.getBlockState(pos.north());
                below = level.getBlockState(pos.south());
            } else if (facing == Direction.EAST) {
                above = level.getBlockState(pos.south());
                below = level.getBlockState(pos.north());
            } else {
                above = level.getBlockState(pos.above());
                below = level.getBlockState(pos.below());
            }
        }else{
            above = level.getBlockState(pos.above());
            below = level.getBlockState(pos.below());
        }

        return ((below.is(ModBlocks.WIRE_BOX) || below.is(ModBlocks.WALL_SWITCH)) ? 2 : 0)
                + ((above.is(ModBlocks.WIRE_BOX) || above.is(ModBlocks.WALL_SWITCH)) ? 1 : 0);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        state = state.setValue(POWER, getPower(level, pos, state)).setValue(LONG_SIDE, sides(level, state, pos));
        level.setBlock(pos, state, 3);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        for(Direction direction : pContext.getNearestLookingDirections()) {
            BlockState blockstate;
            BlockPos pPos = pContext.getClickedPos();
            BlockState above = pContext.getLevel().getBlockState(pPos.above());
            BlockState below = pContext.getLevel().getBlockState(pPos.below());
            int longside = ((below.is(ModBlocks.WIRE_BOX) || below.is(ModBlocks.WALL_SWITCH)) ? 2 : 0)
                    + ((above.is(ModBlocks.WIRE_BOX) || above.is(ModBlocks.WALL_SWITCH)) ? 1 : 0);
            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = (BlockState)((BlockState)this.defaultBlockState().setValue(FACE, direction == Direction.UP ?
                        AttachFace.CEILING : AttachFace.FLOOR)).setValue(FACING, pContext.getHorizontalDirection()).setValue(LONG_SIDE, longside);
            } else {
                blockstate = (BlockState)((BlockState)this.defaultBlockState().setValue(FACE, AttachFace.WALL)).setValue(FACING,
                        direction.getOpposite()).setValue(LONG_SIDE, longside);
            }
            if (blockstate.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                return blockstate;
            }
        }

        return null;

    }

}
