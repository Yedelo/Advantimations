package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(BipedEntityRenderer.class)
public abstract class BipedEntityRendererMixin {
    @Unique
    private static Entity advantimations$bipedEntity;

    @Inject(method = "updateBipedRenderState", at = @At("HEAD"))
    private static void advantimations$storeBipedEntity(LivingEntity entity, BipedEntityRenderState state, float tickProgress, ItemModelManager itemModelResolver, CallbackInfo ci) {
        advantimations$bipedEntity = entity;
    }

    @ModifyExpressionValue(method = "updateBipedRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getHandSwingProgress(F)F"))
    private static float advantimations$cancelSwings(float original) {
        AdvantimationsConfig.EntityApplicableOption cancelSwings = AdvantimationsConfig.getInstance().cancelSwings;
        if (cancelSwings.enabled) {
            if (cancelSwings.enabledOnSelf && advantimations$bipedEntity instanceof ClientPlayerEntity) {
                return 0;
            }
            if (cancelSwings.enabledOnOtherPlayers && advantimations$bipedEntity instanceof PlayerEntity player && !player.isMainPlayer()) {
                return 0;
            }
            if (cancelSwings.enabledOnOtherEntities && !(advantimations$bipedEntity instanceof PlayerEntity)) {
                return 0;
            }
            return original;
        }
        return original;
    }
}
