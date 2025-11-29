package at.yedel.advantimations.mixin;




import at.yedel.advantimations.utils.EntityRenderInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {
    @Inject(method = "render(Lnet/minecraft/world/entity/Entity;DDDFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/EntityRenderer;)V", at = @At("HEAD"))
    private <E extends Entity, S extends EntityRenderState> void advantimations$storeEntity(E entity, double x, double y, double z, float tickProgress, PoseStack matrices, MultiBufferSource vertexConsumers, int light, EntityRenderer<? super E, S> renderer, CallbackInfo ci) {
        EntityRenderInfo.entity = entity;
    }
}
