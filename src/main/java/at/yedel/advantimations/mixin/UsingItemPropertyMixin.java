package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
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
    private void advantimations$storePredicateInformation(ItemStack stack, ClientWorld world, LivingEntity entity, int seed, ItemDisplayContext displayContext, CallbackInfoReturnable<Boolean> cir, @Share("stack") LocalRef<ItemStack> stackRef, @Share("displayContext") LocalRef<ItemDisplayContext> displayContextRef) {
        stackRef.set(stack);
        displayContextRef.set(displayContext);
    }

    @ModifyExpressionValue(method = "test", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isUsingItem()Z"))
    private boolean advantimations$cancelItemUseCondition(boolean original, @Share("stack") LocalRef<ItemStack> stackRef, @Share("displayContext") LocalRef<ItemDisplayContext> displayContextRef) {
        Item item = stackRef.get().getItem();
        ItemDisplayContext displayContext = displayContextRef.get();
        if ((displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND || displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND) & (AdvantimationsConfig.getInstance().cancelShieldAnimation && item instanceof ShieldItem) || (AdvantimationsConfig.getInstance().cancelArrowAnimation && item instanceof BowItem)) {
            return false;
        }
        return original;
    }
}
