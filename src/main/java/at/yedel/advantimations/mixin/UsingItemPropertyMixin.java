package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.item.property.bool.UsingItemProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(UsingItemProperty.class)
public abstract class UsingItemPropertyMixin {
    @Unique private ItemStack advantimations$stack;
    @Unique private ItemDisplayContext advantimations$displayContext;

    @Inject(method = "test", at = @At("HEAD"))
    private void advantimations$(ItemStack stack, ClientWorld world, LivingEntity entity, int seed, ItemDisplayContext displayContext, CallbackInfoReturnable<Boolean> cir) {
        this.advantimations$stack = stack;
        this.advantimations$displayContext = displayContext;
    }

    @ModifyExpressionValue(method = "test", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isUsingItem()Z"))
    private boolean advantimations$cancelItemUseCondition(boolean original) {
        Item item = advantimations$stack.getItem();
        if ((advantimations$displayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND || advantimations$displayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND) & (AdvantimationsConfig.getInstance().cancelShieldAnimation && item instanceof ShieldItem) || (AdvantimationsConfig.getInstance().cancelArrowAnimation && item instanceof BowItem)) {
            return false;
        }
        return original;
    }
}
