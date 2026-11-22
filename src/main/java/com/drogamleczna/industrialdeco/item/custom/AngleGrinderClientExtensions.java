package com.drogamleczna.industrialdeco.item.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

public class AngleGrinderClientExtensions implements IClientItemExtensions {
    private boolean animationStarted = false;
    private int animationStartTick = 0;
    private int lastSelectedSlot = 0;

    @Override
    public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack stack, float partialTick,
                                           float equipProcess, float swingProcess) {

        int selectedSlot = player.getInventory().selected;
        if (selectedSlot != lastSelectedSlot) {
            animationStarted = false;
            animationStartTick = 0;
            lastSelectedSlot = selectedSlot;
        }

        if (player.isUsingItem() && player.getUseItem() == stack) {
            poseStack.translate((arm == HumanoidArm.RIGHT ? 1 : -1) * 0.56F, -0.52F, -0.72F);
            if (!animationStarted) {
                animationStarted = true;
                animationStartTick = player.tickCount;
            }
        }

        if (animationStarted) {
            float progress = (player.tickCount + partialTick - animationStartTick) / 20.0F;
            float y_transl = -Mth.sin(progress * Mth.PI);
            poseStack.translate(0.0F, y_transl * 0.15F, 0.0F);

            if (progress >= 1.0F) {
                animationStarted = false;
                animationStartTick = 0;
            }
        }

        return false;
    }

    @Override
    public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
        return HumanoidModel.ArmPose.BLOCK;
    }
}