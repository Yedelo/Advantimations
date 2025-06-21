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
        if ((AdvantimationsConfig.getInstance().cancelEatingAnimation && original == UseAction.EAT) || (AdvantimationsConfig.getInstance().cancelDrinkingAnimation && original == UseAction.DRINK) || (AdvantimationsConfig.getInstance().cancelBlockingAnimation && original == UseAction.BLOCK) || (AdvantimationsConfig.getInstance().cancelBowingAnimation && original == UseAction.BOW) || (AdvantimationsConfig.getInstance().cancelSpearAnimation && original == UseAction.SPEAR) || (AdvantimationsConfig.getInstance().cancelBrushingAnimation && original == UseAction.BRUSH) || (AdvantimationsConfig.getInstance().cancelBundleAnimation && original == UseAction.BUNDLE)) {
            return UseAction.NONE;
        }
        return original;
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingItem()Z", ordinal = 0))
    private boolean advantimations$cancelCrossbowingAnimation(boolean original) {
        if (AdvantimationsConfig.getInstance().cancelCrossbowingAnimation) {
            return false;
        }
        return original;
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;isCharged(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean advantimations$cancelChargedCrossbowAnimation(boolean original) {
        if (AdvantimationsConfig.getInstance().cancelChargedCrossbowAnimation) {
            return false;
        }
        return original;
    }

    @Inject(method = "shouldSkipHandAnimationOnSwap", at = @At("HEAD"), cancellable = true)
    private void advantimations$cancelAllItemResets(ItemStack from, ItemStack to, CallbackInfoReturnable<Boolean> cir) {
        if (AdvantimationsConfig.getInstance().cancelAllItemResets) {
            cir.setReturnValue(true);
        }
    }
}
