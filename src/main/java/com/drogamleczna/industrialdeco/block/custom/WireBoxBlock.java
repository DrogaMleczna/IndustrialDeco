package com.drogamleczna.industrialdeco.block.custom;

import com.drogamleczna.industrialdeco.block.ModBlocks;
import com.drogamleczna.industrialdeco.block.custom.util.WireBoxType;
import com.drogamleczna.industrialdeco.block.custom.util.WireBoxTypeProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import static com.drogamleczna.industrialdeco.IndustrialDeco.WRENCH_TAG;

public class WireBoxBlock extends FaceAttachedHorizontalDirectionalBlock {
    public WireBoxBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final WireBoxTypeProperty INPUT = WireBoxTypeProperty.create("input", WireBoxType.B,WireBoxType.T,WireBoxType.R,
            WireBoxType.L, WireBoxType.BT,WireBoxType.BR,WireBoxType.BL,WireBoxType.TR,WireBoxType.TL, WireBoxType.BLR,WireBoxType.BLT,
            WireBoxType.BRT,WireBoxType.TRL);

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
        builder.add(INPUT);
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        int inPower = evalSignal(pState, pLevel, pPos, Direction.NORTH);
        pState = pState.setValue(POWERED, inPower>0);
        pLevel.setBlock(pPos, pState, 10);
        pLevel.updateNeighborsAt(pPos, this);
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

        switch (pState.getValue(INPUT)) {
            case B ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.T), 10);
            case T ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.R), 10);
            case R ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.L), 10);
            case L ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.BR), 10);
            case BR ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.BT), 10);
            case BT ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.BL), 10);
            case BL ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.TR), 10);
            case TR ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.TL), 10);
            case TL ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.BLR), 10);
            case BLR ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.BLT), 10);
            case BLT ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.BRT), 10);
            case BRT ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.TRL), 10);
            case TRL ->
                    pLevel.setBlock(pPos, pState.setValue(INPUT, WireBoxType.B), 10);
        }
        pLevel.updateNeighborsAt(pPos, this);

    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState oldState, boolean movedByPiston) {
        int inPower = evalSignal(pState, pLevel, pPos, Direction.NORTH);
        pState = pState.setValue(POWERED, inPower>0);
        pLevel.setBlock(pPos, pState, 3);
    }


    @Override
    public boolean isSignalSource(BlockState pState) {
        return true;
    }

    private int evalSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pSide){
        int s_u = pLevel.getBlockState(pPos.above()).getSignal(pLevel, pPos.above(), Direction.DOWN);
        int s_d = pLevel.getBlockState(pPos.below()).getSignal(pLevel, pPos.below(), Direction.UP);
        int s_w = pLevel.getBlockState(pPos.west()).getSignal(pLevel, pPos.west(), Direction.EAST);
        int s_e = pLevel.getBlockState(pPos.east()).getSignal(pLevel, pPos.east(), Direction.WEST);
        int s_n = pLevel.getBlockState(pPos.north()).getSignal(pLevel, pPos.north(), Direction.SOUTH);
        int s_s = pLevel.getBlockState(pPos.south()).getSignal(pLevel, pPos.south(), Direction.NORTH);
        WireBoxType in = pState.getValue(INPUT);

        if(pState.getValue(FACE) == AttachFace.WALL){
            if (in == WireBoxType.B && pSide != Direction.DOWN && s_d > 0) {
                return 15;
            } else if (in == WireBoxType.T && pSide != Direction.UP && s_u > 0) {
                return 15;
            } else if (in == WireBoxType.BT && (pSide != Direction.UP && pSide != Direction.DOWN) && (s_u > 0 || s_d > 0)) {
                return 15;
            } else {
                if (pState.getValue(FACING) == Direction.NORTH) {
                    if(in == WireBoxType.R && pSide != Direction.WEST && s_w >0){return 15;}
                    if(in == WireBoxType.TR && !(pSide == Direction.WEST || pSide == Direction.UP) && (s_w > 0 || s_u > 0)){return 15;}
                    if(in == WireBoxType.BR && !(pSide == Direction.WEST || pSide == Direction.DOWN) && (s_w > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.L && pSide != Direction.EAST && s_e >0){return 15;}
                    if(in == WireBoxType.TL && !(pSide == Direction.EAST || pSide == Direction.UP) && (s_e > 0 || s_u > 0)){return 15;}
                    if(in == WireBoxType.BL && !(pSide == Direction.EAST || pSide == Direction.DOWN) && (s_e > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.TRL && !(pSide == Direction.EAST || pSide == Direction.WEST || pSide == Direction.UP)
                            && (s_e > 0 || s_u > 0 || s_w > 0)){return 15;}
                    if(in == WireBoxType.BLR && !(pSide == Direction.EAST || pSide == Direction.DOWN || pSide == Direction.WEST)
                            && (s_e > 0 || s_d > 0 || s_w > 0)){return 15;}
                    if(in == WireBoxType.BLT && !(pSide == Direction.EAST || pSide == Direction.UP || pSide == Direction.DOWN)
                            && (s_e > 0 || s_u > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.BRT && !(pSide == Direction.WEST || pSide == Direction.DOWN || pSide == Direction.UP)
                            && (s_w > 0 || s_d > 0 || s_u >0)){return 15;}
                } else if (pState.getValue(FACING) == Direction.SOUTH) {
                    if(in == WireBoxType.R && pSide != Direction.EAST && s_e >0){return 15;}
                    if(in == WireBoxType.TR && !(pSide == Direction.EAST || pSide == Direction.UP) && (s_e > 0 || s_u > 0)){return 15;}
                    if(in == WireBoxType.BR && !(pSide == Direction.EAST || pSide == Direction.DOWN) && (s_e > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.L && pSide != Direction.WEST && s_w >0){return 15;}
                    if(in == WireBoxType.TL && !(pSide == Direction.EAST || pSide == Direction.UP) && (s_w > 0 || s_u > 0)){return 15;}
                    if(in == WireBoxType.BL && !(pSide == Direction.EAST || pSide == Direction.DOWN) && (s_w > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.TRL && !(pSide == Direction.WEST || pSide == Direction.EAST || pSide == Direction.UP)
                            && (s_w > 0 || s_u > 0 || s_e > 0)){return 15;}
                    if(in == WireBoxType.BLR && !(pSide == Direction.WEST || pSide == Direction.DOWN || pSide == Direction.EAST)
                            && (s_w > 0 || s_d > 0 || s_e > 0)){return 15;}
                    if(in == WireBoxType.BLT && !(pSide == Direction.WEST || pSide == Direction.UP || pSide == Direction.DOWN)
                            && (s_w > 0 || s_u > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.BRT && !(pSide == Direction.EAST || pSide == Direction.DOWN || pSide == Direction.UP)
                            && (s_e > 0 || s_d > 0 || s_u >0)){return 15;}
                } else if (pState.getValue(FACING) == Direction.WEST) {
                    if(in == WireBoxType.R && pSide != Direction.SOUTH && s_s > 0){return 15;}
                    if(in == WireBoxType.TR && !(pSide == Direction.SOUTH || pSide == Direction.UP) && (s_s > 0 || s_u > 0)){return 15;}
                    if(in == WireBoxType.BR && !(pSide == Direction.SOUTH || pSide == Direction.DOWN) && (s_s > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.L && pSide != Direction.NORTH && s_n >0){return 15;}
                    if(in == WireBoxType.TL && !(pSide == Direction.NORTH || pSide == Direction.UP) && (s_n > 0 || s_u > 0)){return 15;}
                    if(in == WireBoxType.BL && !(pSide == Direction.NORTH || pSide == Direction.DOWN) && (s_n > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.TRL && !(pSide == Direction.NORTH || pSide == Direction.SOUTH || pSide == Direction.UP)
                            && (s_n > 0 || s_u > 0 || s_s > 0)){return 15;}
                    if(in == WireBoxType.BLR && !(pSide == Direction.NORTH || pSide == Direction.DOWN || pSide == Direction.SOUTH)
                            && (s_n > 0 || s_d > 0 || s_s > 0)){return 15;}
                    if(in == WireBoxType.BLT && !(pSide == Direction.NORTH || pSide == Direction.UP || pSide == Direction.DOWN)
                            && (s_n > 0 || s_u > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.BRT && !(pSide == Direction.SOUTH || pSide == Direction.DOWN || pSide == Direction.UP)
                            && (s_s > 0 || s_d > 0 || s_u >0)){return 15;}

                } else if (pState.getValue(FACING) == Direction.EAST){
                    System.out.println(s_n);
                    if(in == WireBoxType.R && pSide != Direction.NORTH && s_n > 0)return 15;}
                    if(in == WireBoxType.TR && !(pSide == Direction.NORTH || pSide == Direction.UP) && (s_n > 0 || s_u > 0)){return 15;}
                    if(in == WireBoxType.BR && !(pSide == Direction.NORTH || pSide == Direction.DOWN) && (s_n > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.L && pSide != Direction.SOUTH && s_s >0){return 15;}
                    if(in == WireBoxType.TL && !(pSide == Direction.SOUTH || pSide == Direction.UP) && (s_s > 0 || s_u > 0)){return 15;}
                    if(in == WireBoxType.BL && !(pSide == Direction.SOUTH || pSide == Direction.DOWN) && (s_s > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.TRL && !(pSide == Direction.SOUTH || pSide == Direction.NORTH || pSide == Direction.UP)
                            && (s_s > 0 || s_u > 0 || s_n > 0)){return 15;}
                    if(in == WireBoxType.BLR && !(pSide == Direction.SOUTH || pSide == Direction.DOWN || pSide == Direction.NORTH)
                            && (s_s > 0 || s_d > 0 || s_n > 0)){return 15;}
                    if(in == WireBoxType.BLT && !(pSide == Direction.SOUTH || pSide == Direction.UP || pSide == Direction.DOWN)
                            && (s_s > 0 || s_u > 0 || s_d > 0)){return 15;}
                    if(in == WireBoxType.BRT && !(pSide == Direction.NORTH || pSide == Direction.DOWN || pSide == Direction.UP)
                            && (s_n > 0 || s_d > 0 || s_u >0)){return 15;}
                }
            }
        return 0;
    }


    @Override
    public int getSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pSide) {
        return evalSignal(pState, pLevel, pPos, pSide);
    }

    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return (Boolean)blockState.getValue(POWERED) && getConnectedDirection(blockState) == side ? 15 : 0;
    }
}
