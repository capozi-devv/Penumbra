package net.eya.penumbra.common.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.eya.penumbra.mixin.GameRendererAccessor;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;

public class CustomSkyRenderer implements DimensionRenderingRegistry.SkyRenderer {
    public static boolean enabled = false;

    private static final Identifier CUSTOM_SUN = new Identifier("penumbra", "textures/sky/suan.png");

    @Override
    public void render(WorldRenderContext context) {
        if (!enabled) return;
        GameRendererAccessor accessor = (GameRendererAccessor) MinecraftClient.getInstance().gameRenderer;

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();

        RenderSystem.setShader(() -> accessor.penumbra$getPositionColorProgram());

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        buffer.vertex(-100, 100, -100).color(0, 100, 255, 255).next();
        buffer.vertex(100, 100, -100).color(0, 100, 255, 255).next();
        buffer.vertex(100, 100, 100).color(0, 100, 255, 255).next();
        buffer.vertex(-100, 100, 100).color(0, 100, 255, 255).next();
        BufferRenderer.drawWithGlobalProgram(buffer.end());

        RenderSystem.setShader(() -> accessor.penumbra$getPositionTexProgram());

        RenderSystem.setShaderTexture(0, CUSTOM_SUN);

        float size = 20;
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(-size, size, -100).texture(0, 0).next();
        buffer.vertex(size, size, -100).texture(1, 0).next();
        buffer.vertex(size, -size, -100).texture(1, 1).next();
        buffer.vertex(-size, -size, -100).texture(0, 1).next();
        BufferRenderer.drawWithGlobalProgram(buffer.end());
    }
}