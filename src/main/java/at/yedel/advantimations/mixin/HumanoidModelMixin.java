package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import at.yedel.advantimations.utils.EntityRenderInfo;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin {
    @Unique
    private HumanoidModel.ArmPose advantimations$cancelItemUseAnimations(HumanoidArm arm, HumanoidModel.ArmPose original) {
        if (EntityRenderInfo.entity instanceof LivingEntity entity) {
            HumanoidModel.ArmPose defaultPose = !entity.getItemHeldByArm(arm).isEmpty() ? HumanoidModel.ArmPose.ITEM : HumanoidModel.ArmPose.EMPTY;
            return switch (original) {
                case BLOCK -> AdvantimationsConfig.getInstance().cancelBlockingAnimation.getThirdPersonResult(entity, original, defaultPose);
                case BOW_AND_ARROW -> AdvantimationsConfig.getInstance().cancelBowAnimation.getThirdPersonResult(entity, original, defaultPose);
                case THROW_SPEAR -> AdvantimationsConfig.getInstance().cancelSpearAnimation.getThirdPersonResult(entity, original, defaultPose);
                case CROSSBOW_CHARGE -> AdvantimationsConfig.getInstance().cancelCrossbowAnimation.getThirdPersonResult(entity, original, defaultPose);
                case CROSSBOW_HOLD -> AdvantimationsConfig.getInstance().cancelChargedCrossbowAnimation.getThirdPersonResult(entity, original, defaultPose);
                case SPYGLASS -> AdvantimationsConfig.getInstance().cancelSpyglassAnimation.getThirdPersonResult(entity, original, defaultPose);
                case TOOT_HORN -> AdvantimationsConfig.getInstance().cancelHornTootAnimation.getThirdPersonResult(entity, original, defaultPose);
                case BRUSH -> AdvantimationsConfig.getInstance().cancelBrushingAnimation.getThirdPersonResult(entity, original, defaultPose);
                default -> original;
            };
        }
        return original;
    }

    @ModifyReceiver(method = "poseLeftArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/HumanoidModel$ArmPose;ordinal()I"))
    private HumanoidModel.ArmPose advantimations$cancelLeftArmItemUseAnimations(HumanoidModel.ArmPose original) {
        return advantimations$cancelItemUseAnimations(HumanoidArm.LEFT, original);
    }

    @ModifyReceiver(method = "poseRightArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/HumanoidModel$ArmPose;ordinal()I"))
    private HumanoidModel.ArmPose advantimations$cancelRightArmItemUseAnimations(HumanoidModel.ArmPose original) {
        return advantimations$cancelItemUseAnimations(HumanoidArm.RIGHT, original);
    }
}
