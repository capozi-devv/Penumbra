package net.eya.penumbra.common.lodestone.worldvfx;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.awt.*;

public class AllVFX {
    public static void obeliskBeam(Color color, Matrix4f matrix4f, BlockPos start, Vec3d camPos) {
        VFXBuilders.WorldVFXBuilder.WorldVertexConsumerActor consumer  = new VFXBuilders.WorldVFXBuilder.WorldVertexConsumerActor() {
            @Override
            public void placeVertex(VertexConsumer consumer, Matrix4f last, VFXBuilders.WorldVFXBuilder builder, float x, float y, float z, float u, float v) {
                consumer.vertex(last, x, y, z).color(248, 209, 109, 128).next();
            }
        };
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setVertexSupplier(consumer).setRenderType(RenderLayer.getTranslucent()).setFormat(VertexFormats.POSITION_COLOR).setColor(color);
        builder.renderBeam(matrix4f, Vec3d.of(start), Vec3d.of(start).add(0d, 30d, 0f), 3f, camPos);
    }
}
