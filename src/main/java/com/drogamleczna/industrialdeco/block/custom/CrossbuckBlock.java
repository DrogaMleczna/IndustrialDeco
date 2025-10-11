package com.drogamleczna.industrialdeco.block.custom;

import com.drogamleczna.industrialdeco.block.custom.util.CrossbuckType;
import com.drogamleczna.industrialdeco.block.custom.util.CrossbuckTypeProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class CrossbuckBlock extends PoleBlock {
    private boolean canSwitchState = false;
    TagKey<Item> WRENCH_TAG = ItemTags.create(new ResourceLocation("forge", "tools/wrench"));

    public static final CrossbuckTypeProperty CTYPE = CrossbuckTypeProperty.create("crossbuck_type",
            CrossbuckType.CANADA, CrossbuckType.POLAND, CrossbuckType.POLAND_MULTITRACK, CrossbuckType.SWEDEN, CrossbuckType.SWEDEN_MULTITRACK,
            CrossbuckType.BELGIUM, CrossbuckType.BELGIUM_MULTITRACK, CrossbuckType.EUROPE, CrossbuckType.EUROPE_MULTITRACK, CrossbuckType.JAPAN);
    public CrossbuckBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(defaultBlockState().setValue(CTYPE, CrossbuckType.POLAND));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pPlayer.getMainHandItem().is(Items.STICK) && canSwitchState) {
            changeType(pState, pLevel, pPos);
            return InteractionResult.SUCCESS;
        }else if(pPlayer.getMainHandItem().is(WRENCH_TAG) && canSwitchState) {
            changeType(pState, pLevel, pPos);
            return InteractionResult.SUCCESS;
        }else if (!canSwitchState) {
            canSwitchState = true;
            return InteractionResult.CONSUME;
        }else{
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        super.createBlockStateDefinition(builder);
        builder.add(CTYPE);
    }

    public void changeType(BlockState pState, Level pLevel, BlockPos pPos){

        switch (pState.getValue(CTYPE)) {
            case POLAND ->
                    pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.POLAND_MULTITRACK), 0);
            case POLAND_MULTITRACK ->
                    pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.SWEDEN), 0);
            case SWEDEN ->
                    pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.SWEDEN_MULTITRACK), 0);
            case SWEDEN_MULTITRACK ->
                    pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.BELGIUM), 0);
            case BELGIUM ->
                    pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.BELGIUM_MULTITRACK), 0);
            case BELGIUM_MULTITRACK ->
                    pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.EUROPE), 0);
            case EUROPE ->
                    pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.EUROPE_MULTITRACK), 0);
            case EUROPE_MULTITRACK ->
                    pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.CANADA), 0);
            case CANADA -> pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.JAPAN), 0);
            case JAPAN -> pLevel.setBlock(pPos, (BlockState) pState.setValue(CTYPE, CrossbuckType.POLAND), 0);
        }
        pLevel.updateNeighborsAt(pPos, this);
        this.canSwitchState = false;

    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }
}
