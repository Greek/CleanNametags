package com.apap04.cleannametags.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityRenderer.class)
public class RenderNametagMixin {
    @ModifyVariable(method = "renderLabelIfPresent", at = @At("STORE"), ordinal = 1)
    private float injected(float g) {
        return MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.0f);
    }

    @ModifyArg(method = "renderLabelIfPresent", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/Text;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZII)I"),
            index = 4)
    private boolean injected(boolean shadow) {
        return true;
    }

}
