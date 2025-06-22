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
        return AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getFirstPersonResult(original, false);
    }

    @ModifyExpressionValue(method = "renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getHandSwingProgress(F)F"))
    private float advantimations$cancelFirstPersonSwings(float original) {
        return AdvantimationsConfig.getInstance().cancelSwings.getFirstPersonResult(original, 0F);
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getUseAction()Lnet/minecraft/item/consume/UseAction;"))
    private UseAction advantimations$cancelUseAnimations(UseAction original) {
        if (switch (original) {
            case EAT -> AdvantimationsConfig.getInstance().cancelEatingAnimation.shouldApplyInFirstPerson();
            case DRINK -> AdvantimationsConfig.getInstance().cancelDrinkingAnimation.shouldApplyInFirstPerson();
            case BLOCK -> AdvantimationsConfig.getInstance().cancelBlockingAnimation.shouldApplyInFirstPerson();
            case BOW -> AdvantimationsConfig.getInstance().cancelBowAnimation.shouldApplyInFirstPerson();
            case SPEAR -> AdvantimationsConfig.getInstance().cancelSpearAnimation.shouldApplyInFirstPerson();
            case BRUSH -> AdvantimationsConfig.getInstance().cancelBrushingAnimation.shouldApplyInFirstPerson();
            case BUNDLE -> AdvantimationsConfig.getInstance().cancelBundleAnimation.shouldApplyInFirstPerson();
            default -> false;
        }) {
            return UseAction.NONE;
        }
        return original;
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingItem()Z", ordinal = 0))
    private boolean advantimations$cancelCrossbowAnimation(boolean original) {
        return AdvantimationsConfig.getInstance().cancelCrossbowAnimation.getFirstPersonResult(original, false);
    }

    @ModifyExpressionValue(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;isCharged(Lnet/minecraft/item/ItemStack;)Z"))
    private boolean advantimations$cancelChargedCrossbowAnimation(boolean original) {
        return AdvantimationsConfig.getInstance().cancelChargedCrossbowAnimation.getFirstPersonResult(original, false);
    }

    @ModifyExpressionValue(method = "updateHeldItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F"))
    private float advantimations$cancelAttackCooldownResets(float original) {
        return AdvantimationsConfig.getInstance().cancelAttackCooldownResets.getFirstPersonResult(original, 1F);
    }

    @Inject(method = "shouldSkipHandAnimationOnSwap", at = @At("HEAD"), cancellable = true)
    private void advantimations$cancelAllItemResets(ItemStack from, ItemStack to, CallbackInfoReturnable<Boolean> cir) {
        if (AdvantimationsConfig.getInstance().cancelSlotSwappingResets.shouldApplyInFirstPerson()) {
            cir.setReturnValue(true);
        }
    }
}
