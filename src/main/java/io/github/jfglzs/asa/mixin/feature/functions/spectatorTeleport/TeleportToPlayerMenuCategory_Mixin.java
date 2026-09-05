package io.github.jfglzs.asa.mixin.feature.functions.spectatorTeleport;

import io.github.jfglzs.asa.config.Configs;
import net.minecraft.client.gui.spectator.SpectatorMenuItem;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayerMenuCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(TeleportToPlayerMenuCategory.class)
public abstract class TeleportToPlayerMenuCategory_Mixin {
    // 都怪 TweakerMore 讓它那麼複雜。
    @Inject(method = "getItems", at = @At("RETURN"), cancellable = true)
    private void filterBotPrefix(CallbackInfoReturnable<List<SpectatorMenuItem>> cir) {
        if (Configs.Functions.SPECTATOR_TELEPORT_BOT_FILTER.getBooleanValue()) {
            cir.setReturnValue(cir.getReturnValue().stream()
                    .filter(item -> ! item.getName().getString().startsWith("bot_"))
                    .toList());
        }
    }
}
