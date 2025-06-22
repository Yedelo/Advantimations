package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @ModifyExpressionValue(method = "renderMiscOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingSpyglass()Z"))
    private boolean advantimations$cancelSpyglassAnimation(boolean original) {
        if (AdvantimationsConfig.getInstance().cancelSpyglassAnimation.isEnabledInFirstPerson()) {
            return false;
        }
        return original;
    }
}
