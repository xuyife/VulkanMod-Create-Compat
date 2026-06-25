package net.xuyifei.vulkanmod_create_compat.mixin.vulkan_mod;

import net.vulkanmod.gl.GlFramebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GlFramebuffer.class)
public class GlFramebufferMixin {
    @ModifyVariable(method = "framebufferTexture2D", at = @At("HEAD"), argsOnly = true, name = "attachment")
    private static int redirectStencilAttachment(int attachment) {
        // GL_STENCIL_ATTACHMENT = 36128
        // GL_DEPTH_STENCIL_ATTACHMENT = 33306
        if (attachment == 36128 || attachment == 33306) {
            return 36096;
        }
        return attachment;

        // TODO: support stencil attachment
    }
}
