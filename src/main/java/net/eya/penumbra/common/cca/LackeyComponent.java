package net.eya.penumbra.common.cca;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

public interface LackeyComponent extends Component {
    boolean isLackey(ServerPlayerEntity player);
    void setLackey(boolean value);
}
