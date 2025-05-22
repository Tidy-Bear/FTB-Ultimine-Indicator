/*
 * Copyright (c) 2025, Tidy-Bear.
 *
 * This file is part of "FTB Ultimine Indicator".
 *
 * "FTB Ultimine Indicator" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "FTB Ultimine Indicator" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "FTB Ultimine Indicator".  If not, see <https://www.gnu.org/licenses/>.
 */

package me.ctidy.mcmod.ftb.ultimine.indicator.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import dev.ftb.mods.ftbultimine.client.FTBUltimineClient;
import me.ctidy.mcmod.ftb.ultimine.indicator.config.FTBUltimineIndicatorClientConfig;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.ToggleKeyMapping;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * FTBUltimineClientMixin
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
@Mixin(value = FTBUltimineClient.class, remap = false)
public class FTBUltimineClientMixin {

    @Inject(method = "renderGameOverlay", at = @At("HEAD"), cancellable = true)
    private void cancelRenderOverlay(GuiGraphics graphics, float tickDelta, CallbackInfo ci) {
        if (FTBUltimineIndicatorClientConfig.showMenu.get()) {
            return;
        }
        ci.cancel();
    }

    @Redirect(method = "<init>", at = @At(value = "NEW", target = "net/minecraft/client/KeyMapping", remap = true))
    private KeyMapping changeToToggleKeyMapping(String name, InputConstants.Type type, int keyCode, String category) {
        return new ToggleKeyMapping(name, keyCode, category, FTBUltimineIndicatorClientConfig.isToggle::get);
    }

}
