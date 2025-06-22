package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import at.yedel.advantimations.config.EntityOption;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.property.bool.UsingItemProperty;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(UsingItemProperty.class)
public abstract class UsingItemPropertyMixin {
    @ModifyExpressionValue(method = "test", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isUsingItem()Z"))
    private boolean advantimations$cancelItemUseConditions(boolean original, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) LivingEntity entity, @Local(argsOnly = true) ItemDisplayContext displayContext) {
        boolean firstPerson = displayContext.isFirstPerson();
        boolean thirdPerson = !firstPerson;
        boolean shouldCancel = switch (stack.getItem()) {
            case ShieldItem ignored: {
                EntityOption option = AdvantimationsConfig.getInstance().cancelShieldAnimation;
                yield (option.shouldApplyInFirstPerson() && firstPerson) || (option.getThirdPersonResult(entity, !original, true) && thirdPerson);
            }
            case BowItem ignored: {
                EntityOption option = AdvantimationsConfig.getInstance().cancelBowArrowModel;
                yield (option.shouldApplyInFirstPerson() && firstPerson) || (option.getThirdPersonResult(entity, !original, true) && thirdPerson);
            }
            case CrossbowItem ignored: {
                EntityOption option = AdvantimationsConfig.getInstance().cancelCrossbowArrowModel;
                yield (option.shouldApplyInFirstPerson() && firstPerson) || (option.getThirdPersonResult(entity, !original, true) && thirdPerson);
            }
            case GoatHornItem ignored: {
                EntityOption option = AdvantimationsConfig.getInstance().cancelHornTootAnimation;
                yield (option.shouldApplyInFirstPerson() && firstPerson) || (option.getThirdPersonResult(entity, !original, true) && thirdPerson);
            }
            default: {
                yield !original;
            }
        };
        return !shouldCancel;
    }
}
