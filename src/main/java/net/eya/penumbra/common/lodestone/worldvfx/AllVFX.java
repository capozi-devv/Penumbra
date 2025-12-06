package net.eya.penumbra.common.lodestone.worldvfx;

import net.eya.penumbra.Penumbra;
import net.eya.penumbra.common.block.EclipseObeliskBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.LodestoneRenderType;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

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
    private static RenderTypeToken getRenderTypeToken() {
        return RenderTypeToken.createToken(new Identifier(Penumbra.MOD_ID, "textures/entity/beam2.png"));
    }
    private static final LodestoneRenderType RENDER_LAYER = LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE.applyAndCache(getRenderTypeToken());
    public static void renderObelisk(MatrixStack matrixStack, Vec3d pos) {
        matrixStack.scale(1f, 1f, 1f);
        VFXBuilders.WorldVFXBuilder builder = VFXBuilders.createWorld();
        if (EclipseObeliskBlock.shouldRenderBeam) {
            matrixStack.push();
            matrixStack.translate(-pos.getX(), -pos.getY(), -pos.getZ());
            Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
            builder.replaceBufferSource(RenderHandler.DELAYED_RENDER.getTarget())
                    .setRenderType(RENDER_LAYER)
                    .setColor(new Color(255, 255, 255))
                    .setAlpha(1.0f)
                    .renderBeam(matrix4f, pos, pos.add(300,300,0), 8);
            matrixStack.pop();
        }
    }
}
