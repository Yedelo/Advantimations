package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(BipedEntityRenderer.class)
public abstract class BipedEntityRendererMixin {
    @ModifyExpressionValue(method = "updateBipedRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getHandSwingProgress(F)F"))
    private static float advantimations$cancelSwings(float original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().cancelSwings.getThirdPersonResult(entity, original, 0F);
    }

    @ModifyExpressionValue(method = "updateBipedRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInSneakingPose()Z"))
    private static boolean advantimations$cancelSneaking(boolean original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().cancelSneaking.getThirdPersonResult(entity, original, false);
    }
}
