package net.xuyifei.vulkanmod_create_compat.mixin.create;

import com.simibubi.create.content.schematics.client.SchematicHandler;
import com.simibubi.create.content.schematics.client.SchematicRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SchematicHandler.class, remap = false)
public class SchematicHandlerMixin {
    @Shadow
    @Final
    private SchematicRenderer[] renderers;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void guardNullRenderers(CallbackInfo ci) {
        if (this.renderers == null) {
            ci.cancel();
            return;
        }
        for (Object r : this.renderers) {
            if (r == null) {
                ci.cancel();
                return;
            }
        }
    }
}
