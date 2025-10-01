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
    public static void obeliskBeam(BlockPos start) {
        Color color = new Color(248, 209, 109);
        MinecraftClient client = MinecraftClient.getInstance();
        Camera camera = client.gameRenderer.getCamera();
        Vec3d camPos = camera.getPos();
        MatrixStack matrices = new MatrixStack();
        matrices.translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Consumer<VFXBuilders.WorldVFXBuilder> vfxconsumer = (vfx) -> {};
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld().setRenderType(RenderLayer.getTranslucent()).setFormat(VertexFormats.POSITION_COLOR).setColor(color);
        builder.renderBeam(matrix4f, Vec3d.of(start), Vec3d.of(start).add(0d, 100d, 0f), 3f, camPos, vfxconsumer);
    }
}
