package net.eya.penumbra.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;

public class EclipseWings<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION =
            new EntityModelLayer(new Identifier("penumbra", "eclipse_wings"), "main");

    private final ModelPart body;
    private final ModelPart wing;
    private final ModelPart wing2;

    public EclipseWings(ModelPart root) {
        this.body = root.getChild("Body");
        this.wing = this.body.getChild("wing");
        this.wing2 = this.body.getChild("wing2");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData body = root.addChild("Body", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        body.addChild("wing",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(0.0F, -10.0F, 0.0F, 0.0F, 19.0F, 23.0F, new Dilation(0.0F)),
                ModelTransform.of(2.0F, -17.0F, 2.0F, 0.0F, 1.1781F, 0.0F));

        body.addChild("wing2",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(0.0F, -9.5F, 0.0F, 0.0F, 19.0F, 23.0F, new Dilation(0.0F)),
                ModelTransform.of(-2.0F, -17.5F, 2.0F, 0.0F, -1.1781F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        // Place animation code here if you want flapping etc.
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
