package at.yedel.advantimations.mixin;



import at.yedel.advantimations.config.AdvantimationsConfig;
import at.yedel.advantimations.utils.BipedEntityInfo;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;



@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin {
    @Unique
    private BipedEntityModel.ArmPose advantimations$cancelItemUseAnimations(Arm arm, BipedEntityModel.ArmPose original) {
        LivingEntity entity = (LivingEntity) BipedEntityInfo.entity;
        BipedEntityModel.ArmPose defaultPose = !entity.getStackInArm(arm).isEmpty() ? BipedEntityModel.ArmPose.ITEM : BipedEntityModel.ArmPose.EMPTY;
        return switch (original) {
            case BLOCK -> AdvantimationsConfig.getInstance().cancelBlockingAnimation.getResult(entity, original, defaultPose);
            case BOW_AND_ARROW -> AdvantimationsConfig.getInstance().cancelBowAnimation.getResult(entity, original, defaultPose);
            case THROW_SPEAR -> AdvantimationsConfig.getInstance().cancelSpearAnimation.getResult(entity, original, defaultPose);
            case CROSSBOW_CHARGE -> AdvantimationsConfig.getInstance().cancelCrossbowAnimation.getResult(entity, original, defaultPose);
            case CROSSBOW_HOLD -> AdvantimationsConfig.getInstance().cancelChargedCrossbowAnimation.getResult(entity, original, defaultPose);
            case BRUSH -> AdvantimationsConfig.getInstance().cancelBrushingAnimation.getResult(entity, original, defaultPose);
            default -> original;
        };
    }

    @ModifyReceiver(method = "positionLeftArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;ordinal()I"))
    private BipedEntityModel.ArmPose advantimations$cancelLeftArmItemUseAnimations(BipedEntityModel.ArmPose original) {
        return advantimations$cancelItemUseAnimations(Arm.LEFT, original);
    }

    @ModifyReceiver(method = "positionRightArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;ordinal()I"))
    private BipedEntityModel.ArmPose advantimations$cancelRightArmItemUseAnimations(BipedEntityModel.ArmPose original) {
        return advantimations$cancelItemUseAnimations(Arm.RIGHT, original);
    }
}
