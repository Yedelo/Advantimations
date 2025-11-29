package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(Gui.class)
public abstract class GuiMixin {
    @ModifyExpressionValue(method = "renderCameraOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isScoping()Z"))
    private boolean advantimations$cancelSpyglassAnimation(boolean original) {
        return AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getFirstPersonResult(original, false);
    }
}
