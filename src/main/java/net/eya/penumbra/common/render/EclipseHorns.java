package net.eya.penumbra.rendering;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;

public class EclipseHorns<T extends Entity> extends EntityModel<T> {
    // Layer location baked with EntityRendererFactory.Context in the renderer
    public static final EntityModelLayer LAYER_LOCATION =
            new EntityModelLayer(new Identifier("modid", "eclipse_horns"), "main");

    private final ModelPart head;
    private final ModelPart halo;
    private final ModelPart righthorn;
    private final ModelPart lefthorn;

    public EclipseHorns(ModelPart root) {
        this.head = root.getChild("Head");
        this.halo = this.head.getChild("halo");
        this.righthorn = this.head.getChild("righthorn");
        this.lefthorn = this.head.getChild("lefthorn");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData head = root.addChild("Head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        head.addChild("halo",
                ModelPartBuilder.create()
                        .uv(-12, 38)
                        .cuboid(-7.0F, 0.0F, -7.0F, 14.0F, 0.0F, 14.0F),
                ModelTransform.pivot(0.0F, -10.5F, 0.0F));

        head.addChild("righthorn",
                ModelPartBuilder.create()
                        .uv(42, 9).mirrored()
                        .cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F).mirrored(false)
                        .uv(42, 0).mirrored()
                        .cuboid(-5.0F, -6.0F, -1.0F, 3.0F, 7.0F, 2.0F).mirrored(false)
                        .uv(14, 47).mirrored()
                        .cuboid(-7.0F, -8.0F, 0.0F, 7.0F, 11.0F, 0.0F).mirrored(false),
                ModelTransform.pivot(-4.0F, -4.0F, 0.0F));

        head.addChild("lefthorn",
                ModelPartBuilder.create()
                        .uv(42, 9)
                        .cuboid(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F)
                        .uv(42, 0)
                        .cuboid(2.0F, -6.0F, -1.0F, 3.0F, 7.0F, 2.0F)
                        .uv(14, 47)
                        .cuboid(0.0F, -8.0F, 0.0F, 7.0F, 11.0F, 0.0F),
                ModelTransform.pivot(4.0F, -4.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                          float netHeadYaw, float headPitch) {
        // Add animation logic if needed
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay,
                       float red, float green, float blue, float alpha) {
        head.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
