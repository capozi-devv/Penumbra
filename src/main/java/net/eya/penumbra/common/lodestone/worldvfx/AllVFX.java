package net.eya.penumbra.common.lodestone.worldvfx;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.awt.*;
import java.util.function.Consumer;

public class AllVFX {
    public static final Color color = new Color(248, 209, 109);
    public static void obeliskBeam(BlockPos pos, int intensity) {
        MinecraftClient client = MinecraftClient.getInstance();
        Camera camera = client.gameRenderer.getCamera();
        Vec3d camPos = camera.getPos();
        MatrixStack matrices = new MatrixStack();
        VertexConsumer vertexConsumer = client.getBufferBuilders().getEntityVertexConsumers().getBuffer(RenderLayer.getTranslucent());
        matrices.translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Vec3d startPos = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        Vec3d targetPos = startPos.add(0d, 100d, 0d);
        float alpha = intensity / 60f;
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld()
                .setColor(color)
                .setFormatRaw(VertexFormats.POSITION_COLOR)
                .setRenderType(RenderLayer.getTranslucent())
                .setVertexConsumer(vertexConsumer)
                .setAlpha(alpha);
        Consumer<VFXBuilders.WorldVFXBuilder> consumer = b -> {
            builder.renderBeam(matrix4f, startPos, targetPos, 3.0f, camPos);
        };
        builder.renderBeam(matrix4f, startPos, targetPos, 3.0f, camPos, consumer);
    }
}
