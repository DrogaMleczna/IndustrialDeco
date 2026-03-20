package com.drogamleczna.industrialdeco.block.custom;

import com.drogamleczna.industrialdeco.block.ModBlocks;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.drogamleczna.industrialdeco.IndustrialDeco.WRENCH_TAG;

public class CageLampBlock extends DirectionalBlock {
    public static final BooleanProperty FORCE_ON = BooleanProperty.create("force_on");
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty HORIZONTAL = BooleanProperty.create("horizontal");
    public CageLampBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(FORCE_ON, false)
                .setValue(LIT, false).setValue(HORIZONTAL, false));
    }

    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_W;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_U;
    public static final VoxelShape SHAPE_D;
    public static final VoxelShape SHAPE_N_H;
    public static final VoxelShape SHAPE_S_H;
    public static final VoxelShape SHAPE_W_H;
    public static final VoxelShape SHAPE_E_H;

    static {
        SHAPE_S = Block.box(3,0,0,12,16,11);
        SHAPE_N = Block.box(3,0,5,12,16,16);
        SHAPE_E = Block.box(0,0,3,11,16,12);
        SHAPE_W = Block.box(5,0,3,16,16,12);
        SHAPE_D = Block.box(3,5,0,12,16,16);
        SHAPE_U = Block.box(3,0,0,12,11,16);

        SHAPE_S_H = Block.box(0,3,0,16,13,11);
        SHAPE_N_H = Block.box(0,3,5,16,13,16);

        SHAPE_E_H = Block.box(0,3,0,11,13,16);
        SHAPE_W_H = Block.box(5,3,0,16,13,16);
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean horizontal = state.getValue(HORIZONTAL);
        switch (direction){
            case Direction.SOUTH: return horizontal ? SHAPE_S_H : SHAPE_S;
            case Direction.WEST: return horizontal ? SHAPE_W_H : SHAPE_W;
            case Direction.EAST: return horizontal ? SHAPE_E_H : SHAPE_E;
            case Direction.UP: return SHAPE_U;
            case Direction.DOWN: return SHAPE_D;
            default: return (horizontal ? SHAPE_N_H : SHAPE_N);
        }

    }

    @Override
    public RenderShape getRenderShape(BlockState pState){
        return RenderShape.MODEL;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        Direction direction = state.getValue(FACING);
        state = isOn(level, pos, state);
        level.setBlock(pos, state, 15);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
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

    private void changeType(BlockState state, Level level, BlockPos pos){
        if(!state.getValue(HORIZONTAL) && !state.getValue(FORCE_ON)){
            state = state.setValue(FORCE_ON, true).setValue(LIT, true);
        }else if(!state.getValue(HORIZONTAL) && state.getValue(FORCE_ON)){
            state = state.setValue(HORIZONTAL, true).setValue(FORCE_ON, false).setValue(LIT, false);
        }else if(state.getValue(HORIZONTAL) && !state.getValue(FORCE_ON)){
            state = state.setValue(FORCE_ON, true).setValue(LIT,true);
        }else{
            state = state.setValue(HORIZONTAL, false).setValue(FORCE_ON, false).setValue(LIT, false);
        }
        level.setBlock(pos, state, 2);
        level.updateNeighborsAt(pos.above(), Blocks.AIR);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        level.updateNeighborsAt(pos, this);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = this.defaultBlockState();
        Direction direction = context.getNearestLookingDirection().getOpposite();
        state = isOn(level, pos, state);
        switch (direction){
            case NORTH -> state = state.setValue(FACING,Direction.NORTH);
            case SOUTH -> state = state.setValue(FACING,Direction.SOUTH);
            case WEST -> state = state.setValue(FACING,Direction.WEST);
            case EAST -> state = state.setValue(FACING,Direction.EAST);
            case DOWN -> state = state.setValue(FACING,Direction.DOWN);
            case UP -> state = state.setValue(FACING,Direction.UP);
        }
        return state;
    }

    @NotNull
    private BlockState isOn(Level level, BlockPos pos, BlockState state) {
        int s_b = level.getBlockState(pos.below()).getSignal(level, pos.below(), Direction.UP );
        int s_a = level.getBlockState(pos.above()).getSignal(level, pos.above(), Direction.DOWN );
        int s_n = level.getBlockState(pos.north()).getSignal(level, pos.north(), Direction.SOUTH );
        int s_s = level.getBlockState(pos.south()).getSignal(level, pos.south(), Direction.NORTH );
        int s_e = level.getBlockState(pos.east()).getSignal(level, pos.east(), Direction.WEST );
        int s_w = level.getBlockState(pos.west()).getSignal(level, pos.west(), Direction.EAST );
        if(state.getValue(FORCE_ON)){state = state.setValue(LIT, true);}
        else{
            if(!state.getValue(HORIZONTAL)) {
                if ((s_b > 0 || s_a > 0)) {
                    state = state.setValue(LIT, true);
                } else {
                    state = state.setValue(LIT, false);
                }
            }else{
                if(state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH){
                    if(s_e > 0 || s_w >0){state = state.setValue(LIT, true);}
                    else{state = state.setValue(LIT, false);}
                }else if(state.getValue(FACING) == Direction.WEST || state.getValue(FACING) == Direction.EAST){
                    if(s_n > 0 || s_s >0){state = state.setValue(LIT, true);}
                    else{state = state.setValue(LIT, false);}
                }else {
                    if(s_w > 0 || s_e > 0 || s_n > 0 || s_s > 0 || s_a >0 || s_b > 0){state = state.setValue(LIT, true);}
                    else{state = state.setValue(LIT, false);}
                }
            }
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(FORCE_ON);
        builder.add(LIT);
        builder.add(HORIZONTAL);
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return null;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 0;
    }
}
