package net.eya.penumbra.mixin;

import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameRenderer.class)
public interface GameRendererAccessor {
    @Accessor("positionColorProgram")
    ShaderProgram penumbra$getPositionColorProgram();

    @Accessor("positionTexProgram")
    ShaderProgram penumbra$getPositionTexProgram();
}