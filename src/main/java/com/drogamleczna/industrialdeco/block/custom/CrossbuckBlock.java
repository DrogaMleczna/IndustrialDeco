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
    private int type = 0;
    private boolean canSwitchState = false;

    public static final CrossbuckTypeProperty CTYPE = CrossbuckTypeProperty.create("crossbuck_type", CrossbuckType.CANADA, CrossbuckType.POLAND, CrossbuckType.POLAND_MULTITRACK, CrossbuckType.SWEDEN, CrossbuckType.SWEDEN_MULTITRACK);
    public CrossbuckBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(defaultBlockState().setValue(CTYPE, CrossbuckType.POLAND));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        TagKey<Item> WRENCH_TAG = ItemTags.create(new ResourceLocation("forge", "tools/wrench"));
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
            return InteractionResult.FAIL;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        super.createBlockStateDefinition(builder);
        builder.add(CTYPE);
    }

    public void changeType(BlockState pState, Level pLevel, BlockPos pPos){
        if(type==0){
            pLevel.setBlock(pPos, (BlockState)pState.setValue(CTYPE, CrossbuckType.POLAND_MULTITRACK), 3);
            type ++;
        }else if(type==1){
            pLevel.setBlock(pPos, (BlockState)pState.setValue(CTYPE, CrossbuckType.CANADA), 3);
            type ++;
        }else if(type==2){
            pLevel.setBlock(pPos, (BlockState)pState.setValue(CTYPE, CrossbuckType.SWEDEN), 3);
            type ++;
        }else if(type==3){
            pLevel.setBlock(pPos, (BlockState)pState.setValue(CTYPE, CrossbuckType.SWEDEN_MULTITRACK), 3);
            type ++;
        }else if(type==4){
            pLevel.setBlock(pPos, (BlockState)pState.setValue(CTYPE, CrossbuckType.POLAND), 3);
            type = 0;
        }else{}
        pLevel.updateNeighborsAt(pPos, this);
        this.canSwitchState = false;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }
}
