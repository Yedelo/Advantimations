package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import at.yedel.advantimations.config.EntityOption;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.client.render.item.property.bool.UsingItemProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(UsingItemProperty.class)
public abstract class UsingItemPropertyMixin {
    @Inject(method = "test", at = @At("HEAD"))
    private void advantimations$storeStackAndDisplayContext(ItemStack stack, ClientWorld world, LivingEntity entity, int seed, ItemDisplayContext displayContext, CallbackInfoReturnable<Boolean> cir, @Share("stack") LocalRef<ItemStack> stackRef, @Share("entity") LocalRef<LivingEntity> entityRef, @Share("displayContext") LocalRef<ItemDisplayContext> displayContextRef) {
        stackRef.set(stack);
        entityRef.set(entity);
        displayContextRef.set(displayContext);
    }

    @ModifyExpressionValue(method = "test", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isUsingItem()Z"))
    private boolean advantimations$cancelItemUseConditions(boolean original, @Share("stack") LocalRef<ItemStack> stackRef, @Share("entity") LocalRef<LivingEntity> entityRef, @Share("displayContext") LocalRef<ItemDisplayContext> displayContextRef) {
        Item item = stackRef.get().getItem();
        LivingEntity entity = entityRef.get();
        ItemDisplayContext displayContext = displayContextRef.get();
        boolean firstPerson = displayContext.isFirstPerson();
        boolean thirdPerson = !firstPerson;
        boolean shouldCancel = switch (item) {
            case ShieldItem ignored: {
                EntityOption option = AdvantimationsConfig.getInstance().cancelShieldAnimation;
                yield (option.isEnabledInFirstPerson() && firstPerson) || (option.getThirdPersonResult(entity, !original, true) && thirdPerson);
            }
            case BowItem ignored: {
                EntityOption option = AdvantimationsConfig.getInstance().cancelBowArrowModel;
                yield (option.isEnabledInFirstPerson() && firstPerson) || (option.getThirdPersonResult(entity, !original, true) && thirdPerson);
            }
            case CrossbowItem ignored: {
                EntityOption option = AdvantimationsConfig.getInstance().cancelCrossbowArrowModel;
                yield (option.isEnabledInFirstPerson() && firstPerson) || (option.getThirdPersonResult(entity, !original, true) && thirdPerson);
            }
            default: {
                yield !original;
            }
        };
        return !shouldCancel;
    }
}
