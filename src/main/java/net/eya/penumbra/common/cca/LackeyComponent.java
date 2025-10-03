package net.eya.penumbra.common.cca;

import dev.onyxstudios.cca.api.v3.component.Component;

import net.minecraft.entity.player.PlayerEntity;

public interface LackeyComponent extends Component {
    boolean isLackey(PlayerEntity player);
    void setLackey(boolean value);
}
