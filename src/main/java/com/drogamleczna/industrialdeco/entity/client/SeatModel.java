package com.drogamleczna.industrialdeco.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;

public class SeatModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart seat;

    public SeatModel(ModelPart seat) {
        this.seat = seat;
    }

    @Override
    public ModelPart root() {
        return seat;
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition rhino = partdefinition.addOrReplaceChild("seat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
    return LayerDefinition.create(meshdefinition, 0,0);
    }

        @Override
    public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        seat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


}
