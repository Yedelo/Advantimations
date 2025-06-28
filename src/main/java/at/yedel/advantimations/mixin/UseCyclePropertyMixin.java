package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.property.numeric.UseCycleProperty;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(UseCycleProperty.class)
public class UseCyclePropertyMixin {
    @ModifyExpressionValue(method = "getValue", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getItemUseTimeLeft()I"))
    private int advantimations$cancelBrushingAnimation(int original, @Local(argsOnly = true) LivingEntity holder) {
        return AdvantimationsConfig.getInstance().cancelBrushingAnimation.getThirdPersonResult(holder, original, 0);
    }
}
