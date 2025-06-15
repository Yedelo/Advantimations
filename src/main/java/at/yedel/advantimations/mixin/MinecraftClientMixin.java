package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @WrapWithCondition(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;resetEquipProgress(Lnet/minecraft/util/Hand;)V", ordinal = 0))
    private boolean advantimations$cancelBlockInteractReset(HeldItemRenderer instance, Hand hand) {
        return !AdvantimationsConfig.getInstance().cancelBlockInteractReset;
    }

    @WrapWithCondition(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;resetEquipProgress(Lnet/minecraft/util/Hand;)V", ordinal = 1))
    private boolean advantimations$cancelItemInteractReset(HeldItemRenderer instance, Hand hand) {
        return !AdvantimationsConfig.getInstance().cancelItemInteractReset;
    }
}
