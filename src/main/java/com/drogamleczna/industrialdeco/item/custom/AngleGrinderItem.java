package com.drogamleczna.industrialdeco.item.custom;

import com.drogamleczna.industrialdeco.IndustrialDeco;
import com.drogamleczna.industrialdeco.block.ModBlocks;
import com.drogamleczna.industrialdeco.block.custom.CrossbuckBlock;
import net.minecraft.client.gui.font.providers.UnihexProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.Map;

public class AngleGrinderItem extends Item implements Equipable {
    public AngleGrinderItem(Properties properties) {
        super(properties);
    }

    public static final Map<Block, Block> GRINDING_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(ModBlocks.CHIMNEY_BLOCK_RUSTED.get(), ModBlocks.CHIMNEY_BLOCK.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.CROSSBUCK_BLOCK_RUSTED.get(), ModBlocks.CROSSBUCK_BLOCK.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.POLE_BLOCK_RUSTED.get(), ModBlocks.POLE_BLOCK.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.POLE_BASE_RUSTED.get(), ModBlocks.POLE_BASE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.STREET_LAMP_RUSTED.get(), ModBlocks.STREET_LAMP.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.CURVED_POLE_RUSTED.get(), ModBlocks.CURVED_POLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.WALL_CURVED_POLE_RUSTED.get(), ModBlocks.WALL_CURVED_POLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.SPLIT_POLE_RUSTED.get(), ModBlocks.SPLIT_POLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.DOUBLE_CURVED_POLE_RUSTED.get(), ModBlocks.DOUBLE_CURVED_POLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.QUADRUPLE_CURVED_POLE_RUSTED.get(), ModBlocks.QUADRUPLE_CURVED_POLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.CORNER_POLE_RUSTED.get(), ModBlocks.CORNER_POLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.WIRE_POLE_RUSTED.get(), ModBlocks.WIRE_POLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.MEDIUM_BASE_RUSTED.get(), ModBlocks.MEDIUM_BASE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.LARGE_BASE_RUSTED.get(), ModBlocks.LARGE_BASE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.CAMERA_POLE_RUSTED.get(), ModBlocks.CAMERA_POLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.CAMERA_POLE_CORNER_RUSTED.get(),  ModBlocks.CAMERA_POLE_CORNER.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.CAMERA_POLE_TRIPLE_RUSTED.get(), ModBlocks.CAMERA_POLE_TRIPLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.CAMERA_POLE_QUADRUPLE_RUSTED.get(), ModBlocks.CAMERA_POLE_QUADRUPLE.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.BENT_CHIMNEY_RUSTED.get(), ModBlocks.BENT_CHIMNEY.get()),
            new AbstractMap.SimpleEntry<>(ModBlocks.WALL_CHIMNEY_RUSTED.get(), ModBlocks.WALL_CHIMNEY.get())
    );


    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack, @NotNull LivingEntity entity) {
        return 20;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.CUSTOM;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level pLevel = context.getLevel();
        BlockPos pPos = context.getClickedPos();
        BlockState pState = pLevel.getBlockState(pPos);
        Block pBlock = pState.getBlock();
        RandomSource pRandom = pLevel.getRandom();
        Player pPlayer = context.getPlayer();
        InteractionHand pHand = context.getHand();


        if(GRINDING_MAP.containsKey(pBlock)){
            assert pPlayer != null;
            pPlayer.startUsingItem(pHand);
            pPlayer.getCooldowns().addCooldown(this, 20);
            if (!pLevel.isClientSide()) {

                pLevel.playSound(null, pPos, SoundEvent.createVariableRangeEvent(
                        ResourceLocation.fromNamespaceAndPath(IndustrialDeco.MODID, "angle_grinder")), SoundSource.PLAYERS);

                BlockState toPlace = GRINDING_MAP.get(pBlock).defaultBlockState();
                try {
                    toPlace = toPlace.trySetValue(BlockStateProperties.HORIZONTAL_FACING, pState.getValue(
                            BlockStateProperties.HORIZONTAL_FACING));
                } catch (Exception ignored) {}
                if (pBlock instanceof CrossbuckBlock) {toPlace = toPlace.setValue(CrossbuckBlock.CTYPE,
                        pState.getValue(CrossbuckBlock.CTYPE));}
                pLevel.setBlock(pPos, toPlace, 10);
            }
            for (int i = 0; i <= pRandom.nextInt(20) + 20; i++) {
                pLevel.addAlwaysVisibleParticle(ParticleTypes.FLAME, pPos.getX() + 0.5F, pPos.getY() + 1F, pPos.getZ() + 0.5F,
                        (pRandom.nextDouble() - 0.5F) * 0.3F,
                        (pRandom.nextDouble() + 1F) * (-0.2F),
                        (pRandom.nextDouble() - 0.5F) * 0.3F);
            }
            for (int i = 0; i <= pRandom.nextInt(200) + 200; i++) {
                pLevel.addAlwaysVisibleParticle(ParticleTypes.SMALL_FLAME, pPos.getX() + 0.5F, pPos.getY() + 1F, pPos.getZ() + 0.5F,
                                (pRandom.nextDouble() - 0.5F) * 0.3F,
                                (pRandom.nextDouble() + 1F) * (-0.2F),
                                (pRandom.nextDouble() - 0.5F) * 0.3F);
            }
            return InteractionResult.PASS;

        }else if(pBlock == Blocks.COBBLESTONE){
            assert pPlayer != null;
            pPlayer.startUsingItem(pHand);
            pPlayer.getCooldowns().addCooldown(this, 20);
            if (!pLevel.isClientSide()) {
                pLevel.playSound(null, pPos, SoundEvent.createVariableRangeEvent(
                        ResourceLocation.fromNamespaceAndPath(IndustrialDeco.MODID, "angle_grinder")), SoundSource.PLAYERS);
            }

            Direction pLookingDirection = pPlayer.getDirection();


            for (int i = 0; i <= pRandom.nextInt(20) + 20; i++) {
                double[] randomVectors = getRandomSparkVectors(pRandom, pLookingDirection);
                pLevel.addAlwaysVisibleParticle(ParticleTypes.FLAME, pPos.getX() + 0.5F, pPos.getY() + 1F, pPos.getZ() + 0.5F,
                        randomVectors[0], randomVectors[1], randomVectors[2]);


            }
            for (int i = 0; i <= pRandom.nextInt(200) + 200; i++) {
                double[] randomVectors = getRandomSparkVectors(pRandom, pLookingDirection);
                pLevel.addAlwaysVisibleParticle(ParticleTypes.SMALL_FLAME, pPos.getX() + 0.5F, pPos.getY() + 1F, pPos.getZ() + 0.5F,
                        randomVectors[0], randomVectors[1], randomVectors[2]);
            }

            return InteractionResult.PASS;
        }

        return InteractionResult.FAIL;
    }
    
    double[] getRandomSparkVectors(RandomSource pRandom, Direction pLookingDirection){
        double dir1;
        double dir2;
        double dir3;

        double[] ans = new double[3];
        
        double coeff1 = 0.1F;
        double coeff2 = -0.2F;

        dir1 = (pRandom.nextDouble() - 0.5F) * coeff1;
        dir3 = (pRandom.nextDouble() - 0.5F) * coeff1;
        dir2 = (pRandom.nextDouble() + 1F) * coeff2; // speed of the particles

        if(pLookingDirection == Direction.EAST) {
            ans[0] = dir2;
            ans[1] = dir1;
            ans[2] = dir3;
        }else if(pLookingDirection == Direction.WEST){
            ans[0] = -dir2;
            ans[1] = dir1;
            ans[2] = dir3;
        }else if(pLookingDirection == Direction.SOUTH){
            ans[0] = dir3;
            ans[1] = dir1;
            ans[2] = dir2;
        }else{
            ans[0] = dir3;
            ans[1] = dir1;
            ans[2] = -dir2;
        }

        return ans;
    }
    
    
}
