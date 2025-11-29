package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.player.Player;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {
    @ModifyExpressionValue(method = "getArmPose(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;swinging:Z", opcode = Opcodes.GETFIELD))
    private static boolean advantimations$cancelSwings(boolean original, @Local(argsOnly = true) Player player) {
        return AdvantimationsConfig.getInstance().cancelSwings.getThirdPersonResult(player, original, false);
    }

    @ModifyExpressionValue(method = "extractRenderState(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/client/renderer/entity/state/PlayerRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean advantimations$cancelSpyglassAnimation(boolean original, @Local(argsOnly = true) AbstractClientPlayer player) {
        return AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getThirdPersonResult(player, original, false);
    }
}
