package net.eya.penumbra.common.datagen;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;

import net.eya.penumbra.foundation.BlockInit;

public class EMIPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        registry.removeEmiStacks(EmiStack.of(BlockInit.OBELISK));
        registry.removeRecipes(r -> r.getOutputs().stream().anyMatch(stack -> stack.isEqual(EmiStack.of(BlockInit.OBELISK))));
    }
}
