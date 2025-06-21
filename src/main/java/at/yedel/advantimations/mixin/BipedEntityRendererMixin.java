package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(BipedEntityRenderer.class)
public abstract class BipedEntityRendererMixin {
    @Inject(method = "updateBipedRenderState", at = @At("HEAD"))
    private static void advantimations$storeEntity(LivingEntity entity, BipedEntityRenderState state, float tickProgress, ItemModelManager itemModelResolver, CallbackInfo ci, @Share("entity") LocalRef<LivingEntity> entityRef) {
        entityRef.set(entity);
    }

    @ModifyExpressionValue(method = "updateBipedRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getHandSwingProgress(F)F"))
    private static float advantimations$cancelSwings(float original, @Share("entity") LocalRef<LivingEntity> entityRef) {
        return AdvantimationsConfig.getInstance().cancelSwings.getResult(entityRef.get(), original, 0F);
    }

    @ModifyExpressionValue(method = "updateBipedRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInSneakingPose()Z"))
    private static boolean advantimations$cancelSneaking(boolean original, @Share("entity") LocalRef<LivingEntity> entityRef) {
        return AdvantimationsConfig.getInstance().cancelSneaking.getResult(entityRef.get(), original, false);
    }
}
