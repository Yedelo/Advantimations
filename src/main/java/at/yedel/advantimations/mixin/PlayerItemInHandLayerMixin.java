package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(PlayerItemInHandLayer.class)
public abstract class PlayerItemInHandLayerMixin {
    @ModifyExpressionValue(method = "renderArmWithItem(Lnet/minecraft/client/renderer/entity/state/PlayerRenderState;Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/state/PlayerRenderState;isUsingItem:Z", opcode = Opcodes.GETFIELD))
    private boolean advantimations$cancelSpyglassAnimation(boolean original, @Local(argsOnly = true) PlayerRenderState state) {
        return AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getThirdPersonResult(state, original, false);
    }
}
