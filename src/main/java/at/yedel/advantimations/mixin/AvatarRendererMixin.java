/*
    this file has to be really weird
    i have to change a lot of things but i cant comment out the entire thing
    as there are other preprocessor things inside it that use multiline comments
    and you can't do something like the following:
    /* test /* test inside *∕ *∕
    see even there i had to use another type of slash. its a big complicated mess and versioned files would be cool
 */
package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.entity.Avatar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin {
    @ModifyExpressionValue(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is" + /*? >= 26.1 {*/"(Ljava/lang/Object;)Z"/*?} else {*//*"(Lnet/minecraft/world/item/Item;)Z"*//*?}*/))
    private boolean advantimations$cancelSpyglassAnimation(boolean original, @Local(argsOnly = true) Avatar avatar) {
        return AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getThirdPersonResult(avatar.asLivingEntity(), original, false);
    }
}
