package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin {
    @ModifyExpressionValue(method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/Avatar;swinging:Z", opcode = Opcodes.GETFIELD))
    private static boolean advantimations$cancelSwings(boolean original, @Local(argsOnly = true) Avatar avatar) {
        return AdvantimationsConfig.getInstance().cancelSwings.getThirdPersonResult(avatar.asLivingEntity(), original, false);
    }

    @ModifyExpressionValue(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean advantimations$cancelSpyglassAnimation(boolean original, @Local(argsOnly = true) Avatar avatar) {
        return AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getThirdPersonResult(avatar.asLivingEntity(), original, false);
    }
}
