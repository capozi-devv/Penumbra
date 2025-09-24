package net.eya.penumbra.common.cca;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface EclipseAvatarComponent extends Component {
    boolean isAvatar(ServerPlayerEntity player);
    void setAvatarValue(boolean value);
    void tickAvatarValue(PlayerEntity player);
}
