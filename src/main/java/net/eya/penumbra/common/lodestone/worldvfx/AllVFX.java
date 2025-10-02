package net.eya.penumbra.common.lodestone.worldvfx;

import net.eya.penumbra.common.block.EclipseObeliskBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
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
        matrices.translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Vec3d startPos = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        Vec3d targetPos = startPos.add(0d, 100d, 0d);
        float alpha = intensity / 60f;
        Vec3d midPoint = startPos.subtract(targetPos);
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld()
                .setColor(color)
                .setFormat(VertexFormats.POSITION_COLOR)
                .setRenderType(RenderLayer.getTranslucent())
                .setAlpha(alpha)
                .renderBeam(matrix4f, startPos, midPoint, 0.4f, b -> b.setColor(color).setAlpha(0f));
    }
}
