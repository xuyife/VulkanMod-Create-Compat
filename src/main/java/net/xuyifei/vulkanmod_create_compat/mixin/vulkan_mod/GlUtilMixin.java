package net.xuyifei.vulkanmod_create_compat.mixin.vulkan_mod;

import net.vulkanmod.gl.GlUtil;
import net.vulkanmod.vulkan.Vulkan;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static org.lwjgl.vulkan.VK10.*;
import static org.lwjgl.vulkan.VK10.VK_FORMAT_B8G8R8A8_UNORM;
import static org.lwjgl.vulkan.VK10.VK_FORMAT_R8G8B8A8_UNORM;
import static org.lwjgl.vulkan.VK10.VK_FORMAT_R8_UNORM;

@Mixin(GlUtil.class)
public class GlUtilMixin {
    /**
     * @author Xuyifei
     * @reason Add GL_DEPTH32F_STENCIL8 to vulkanFormat
     */
    @Overwrite
    public static int vulkanFormat(int glFormat, int type) {
        return switch (glFormat) {
            case GL11.GL_RGBA ->
                    switch (type) {
                        case GL11.GL_UNSIGNED_BYTE -> VK_FORMAT_R8G8B8A8_UNORM;
                        case GL11.GL_BYTE -> VK_FORMAT_R8G8B8A8_UNORM;
                        default -> throw new IllegalStateException("Unexpected type: " + type);
                    };
            case GL30.GL_BGRA ->
                    switch (type) {
                        case GL11.GL_UNSIGNED_BYTE -> VK_FORMAT_B8G8R8A8_UNORM;
                        case GL11.GL_BYTE -> VK_FORMAT_B8G8R8A8_UNORM;
                        case GL30.GL_UNSIGNED_INT_8_8_8_8, GL30.GL_UNSIGNED_INT_8_8_8_8_REV -> VK_FORMAT_R8G8B8A8_UNORM;
                        default -> throw new IllegalStateException("Unexpected type: " + type);
                    };
            case GL30.GL_UNSIGNED_INT_8_8_8_8_REV ->
                    switch (type) {
                        case GL11.GL_UNSIGNED_BYTE -> VK_FORMAT_R8G8B8A8_UINT;
                        case GL11.GL_BYTE -> VK_FORMAT_R8G8B8A8_UNORM;
                        default -> throw new IllegalStateException("Unexpected type: " + type);
                    };
            case GL11.GL_RED ->
                    switch (type) {
                        case GL11.GL_UNSIGNED_BYTE -> VK_FORMAT_R8_UNORM;
                        default -> throw new IllegalStateException("Unexpected type: " + type);
                    };
            case GL11.GL_DEPTH_COMPONENT, GL30.GL_DEPTH32F_STENCIL8, 32856 ->
                    Vulkan.getDefaultDepthFormat();

            default -> throw new IllegalStateException("Unexpected format: " + glFormat);
        };
    }
}
