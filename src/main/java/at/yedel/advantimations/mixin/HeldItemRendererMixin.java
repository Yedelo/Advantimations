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
    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingSpyglass()Z"))
    private boolean advantimations$cancelSpyglassAnimation(boolean original) {
        if (AdvantimationsConfig.getInstance().cancelSpyglassAnimation.isEnabledInFirstPerson()) {
            return false;
        }
        return original;
    }

    @ModifyExpressionValue(method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getHandSwingProgress(F)F"))
    private float advantimations$cancelFirstPersonSwings(float original) {
        if (AdvantimationsConfig.getInstance().cancelSwings.isEnabledInFirstPerson()) {
            return 0;
        }
        else {
            return original;
        }
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/item/consume/UseAction;"))
    private UseAction advantimations$cancelUseAnimations(UseAction original) {
        if (switch (original) {
            case EAT -> AdvantimationsConfig.getInstance().cancelEatingAnimation;
            case DRINK -> AdvantimationsConfig.getInstance().cancelDrinkingAnimation;
            case BLOCK -> AdvantimationsConfig.getInstance().cancelBlockingAnimation.isEnabledInFirstPerson();
            case BOW -> AdvantimationsConfig.getInstance().cancelBowAnimation.isEnabledInFirstPerson();
            case SPEAR -> AdvantimationsConfig.getInstance().cancelSpearAnimation.isEnabledInFirstPerson();
            case BRUSH -> AdvantimationsConfig.getInstance().cancelBrushingAnimation.isEnabledInFirstPerson();
            case BUNDLE -> AdvantimationsConfig.getInstance().cancelBundleAnimation.isEnabledInFirstPerson();
            default -> false;
        }) {
            return UseAction.NONE;
        }
        return original;
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingItem()Z", ordinal = 0))
    private boolean advantimations$cancelCrossbowAnimation(boolean original) {
        if (AdvantimationsConfig.getInstance().cancelCrossbowAnimation.isEnabledInFirstPerson()) {
            return false;
        }
        return original;
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;isCharged(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean advantimations$cancelChargedCrossbowAnimation(boolean original) {
        if (AdvantimationsConfig.getInstance().cancelChargedCrossbowAnimation.isEnabledInFirstPerson()) {
            return false;
        }
        return original;
    }

    @Inject(method = "shouldSkipHandAnimationOnSwap", at = @At("HEAD"), cancellable = true)
    private void advantimations$cancelAllItemResets(ItemStack from, ItemStack to, CallbackInfoReturnable<Boolean> cir) {
        if (AdvantimationsConfig.getInstance().cancelSlotSwappingResets) {
            cir.setReturnValue(true);
        }
    }
}
