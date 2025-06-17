package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @ModifyExpressionValue(method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getHandSwingProgress(F)F"))
    private float advantimations$cancelFirstPersonSwings(float original) {
        if (AdvantimationsConfig.getInstance().cancelFirstPersonSwings) {
            return 0;
        }
        else {
            return original;
        }
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/item/consume/UseAction;"))
    private UseAction advantimations$cancelUseAnimations(UseAction original) {
        if ((AdvantimationsConfig.getInstance().cancelEatAnimation && original == UseAction.EAT) || (AdvantimationsConfig.getInstance().cancelDrinkAnimation && original == UseAction.DRINK) || (AdvantimationsConfig.getInstance().cancelBlockAnimation && original == UseAction.BLOCK) || (AdvantimationsConfig.getInstance().cancelBowAnimation && original == UseAction.BOW) || (AdvantimationsConfig.getInstance().cancelSpearAnimation && original == UseAction.SPEAR) || (AdvantimationsConfig.getInstance().cancelBrushAnimation && original == UseAction.BRUSH) || (AdvantimationsConfig.getInstance().cancelBundleAnimation && original == UseAction.BUNDLE)) {
            return UseAction.NONE;
        }
        return original;
    }

    @Inject(method = "shouldSkipHandAnimationOnSwap", at = @At("HEAD"), cancellable = true)
    private void advantimations$alwaysSkipHandAnimationOnSwap(ItemStack from, ItemStack to, CallbackInfoReturnable<Boolean> cir) {
        if (AdvantimationsConfig.getInstance().alwaysSkipHandAnimationOnSwap) {
            cir.setReturnValue(true);
        }
    }
}
