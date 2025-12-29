package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import at.yedel.advantimations.utils.EntityRenderInfo;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;



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

    @ModifyVariable(method = "poseLeftArm", at = @At("HEAD"), argsOnly = true)
    private HumanoidModel.ArmPose advantimations$cancelLeftArmItemUseAnimations(HumanoidModel.ArmPose original) {
        return advantimations$cancelItemUseAnimations(HumanoidArm.LEFT, original);
    }

    @ModifyVariable(method = "poseLeftArm", at = @At("HEAD"), argsOnly = true)
    private HumanoidModel.ArmPose advantimations$cancelRightArmItemUseAnimations(HumanoidModel.ArmPose original) {
        return advantimations$cancelItemUseAnimations(HumanoidArm.RIGHT, original);
    }

}
