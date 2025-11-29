package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {
    @ModifyExpressionValue(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isAutoSpinAttack()Z"))
    private boolean advantimations$cancelRiptideAnimation(boolean original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().cancelRiptideAnimation.getThirdPersonResult(entity, original, false);
    }

    @ModifyExpressionValue(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/WalkAnimationState;speed(F)F"))
    private float advantimations$cancelLimbMovements(float original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().cancelLimbMovements.getThirdPersonResult(entity, original, 0F);
    }

    @ModifyExpressionValue(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/WalkAnimationState;position(F)F"))
    private float advantimations$weirderLimbMovements(float original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().weirderLimbMovements.getThirdPersonResult(entity, original, 0F);
    }
}
