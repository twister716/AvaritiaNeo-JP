package net.byAqua3.avaritia.mixin.iris;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.byAqua3.avaritia.Avaritia;
import net.irisshaders.iris.mixinterface.ShaderInstanceInterface;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

@Mixin({ ShaderInstance.class })
public abstract class MixinShaderInstance implements ShaderInstanceInterface {
	
	@Shadow
	@Final
	private String name;
	
	@Inject(method = { "iris$shouldSkipThis" }, at = { @At("HEAD") }, cancellable = true)
	public void iris$shouldSkipThis(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
		if (ResourceLocation.parse(this.name).getNamespace().equals(Avaritia.MODID)) {
			callbackInfoReturnable.setReturnValue(false);
			callbackInfoReturnable.cancel();
		}
	}
}
