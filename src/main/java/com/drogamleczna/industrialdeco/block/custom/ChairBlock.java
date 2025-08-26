package com.drogamleczna.industrialdeco.block.custom;

import com.drogamleczna.industrialdeco.entity.ModEntities;
import com.drogamleczna.industrialdeco.entity.Seat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class ChairBlock extends HorizontalDirectionalBlock {


    public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;

    public ChairBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }
    public static final VoxelShape SHAPE_N;
    public static final VoxelShape SHAPE_S;
    public static final VoxelShape SHAPE_E;
    public static final VoxelShape SHAPE_W;
    public static final VoxelShape SHAPE_ARM_N;
    public static final VoxelShape SHAPE_ARM_S;
    public static final VoxelShape SHAPE_ARM_E;
    public static final VoxelShape SHAPE_ARM_W;
    public static final VoxelShape SHAPE_BACK_N;
    public static final VoxelShape SHAPE_BACK_S;
    public static final VoxelShape SHAPE_BACK_E;
    public static final VoxelShape SHAPE_BACK_W;
    public static final VoxelShape BASE;

     static {
             BASE = Block.box(0,0,0,16,8,16);
             SHAPE_ARM_E = Block.box(14,8,0,16,12,16);
             SHAPE_ARM_W = Block.box(0,8,0,2,12,16);
             SHAPE_ARM_N = Block.box(0,8,14,16,12,16);
             SHAPE_ARM_S = Block.box(0,8,0,16,12,2);
             SHAPE_BACK_N = Block.box(0,8,0,16,16,2);
             SHAPE_BACK_S = Block.box(0,8,14,16,16,16);
             SHAPE_BACK_E = Block.box(14,8,0,16,16,16);
             SHAPE_BACK_W = Block.box(0,8,0,2,16,16);
             SHAPE_N = Shapes.or(Shapes.or(BASE, SHAPE_BACK_N), Shapes.or(SHAPE_ARM_W, SHAPE_ARM_E));
             SHAPE_S = Shapes.or(Shapes.or(BASE, SHAPE_BACK_S), Shapes.or(SHAPE_ARM_W, SHAPE_ARM_E));
             SHAPE_W = Shapes.or(Shapes.or(BASE, SHAPE_BACK_W), Shapes.or(SHAPE_ARM_N, SHAPE_ARM_S));
             SHAPE_E = Shapes.or(Shapes.or(BASE, SHAPE_BACK_E), Shapes.or(SHAPE_ARM_N, SHAPE_ARM_S));

         };
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result)
    {
        if (Seat.sit(ModEntities.SEAT.get(),player, pos, 0.25, state.getValue(DIRECTION).getOpposite()))
        {
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }

}
