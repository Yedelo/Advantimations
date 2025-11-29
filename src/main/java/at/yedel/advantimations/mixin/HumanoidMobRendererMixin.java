package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(HumanoidMobRenderer.class)
public abstract class HumanoidMobRendererMixin {
    @ModifyExpressionValue(method = "extractHumanoidRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getAttackAnim(F)F"))
    private static float advantimations$cancelSwings(float original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().cancelSwings.getThirdPersonResult(entity, original, 0F);
    }

    @ModifyExpressionValue(method = "extractHumanoidRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isCrouching()Z"))
    private static boolean advantimations$cancelSneaking(boolean original, @Local(argsOnly = true) LivingEntity entity) {
        return AdvantimationsConfig.getInstance().cancelSneaking.getThirdPersonResult(entity, original, false);
    }
}
