package net.eya.penumbra;

import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;

import net.eya.penumbra.common.cca.EclipseAvatarComponent;
import net.eya.penumbra.common.cca.EclipseAvatarComponentImplementation;
import net.eya.penumbra.common.cca.LackeyComponent;
import net.eya.penumbra.common.cca.LackeyComponentImplementation;

import net.minecraft.util.Identifier;

public class PenumbraComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        Penumbra.ECLIPSE_AVATAR = ComponentRegistry.getOrCreate(new Identifier(Penumbra.MOD_ID, "eclipse_avatar"), EclipseAvatarComponent.class);
        Penumbra.LACKEY = ComponentRegistry.getOrCreate(new Identifier(Penumbra.MOD_ID, "lackey"), LackeyComponent.class);
        entityComponentFactoryRegistry.registerForPlayers(Penumbra.ECLIPSE_AVATAR, player -> (EclipseAvatarComponent) new EclipseAvatarComponentImplementation(), RespawnCopyStrategy.NEVER_COPY);
        entityComponentFactoryRegistry.registerForPlayers(Penumbra.LACKEY, player -> (LackeyComponent) new LackeyComponentImplementation(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
