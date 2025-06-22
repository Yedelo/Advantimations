package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {
    @ModifyExpressionValue(method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LimbAnimator;getAmplitude(F)F"))
    private float advantimations$cancelLimbMovements(float original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().cancelLimbMovements.getThirdPersonResult(entity, original, 0F);
    }

    @ModifyExpressionValue(method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LimbAnimator;getAnimationProgress(F)F"))
    private float advantimations$weirderLimbMovements(float original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().weirderLimbMovements.getThirdPersonResult(entity, original, 0F);
    }
}
