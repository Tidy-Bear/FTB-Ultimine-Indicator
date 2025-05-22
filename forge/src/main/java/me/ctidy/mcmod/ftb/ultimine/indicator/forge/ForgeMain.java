/*
 * Copyright (c) 2024-2025, Tidy-Bear.
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

package me.ctidy.mcmod.ftb.ultimine.indicator.forge;

import me.ctidy.mcmod.ftb.ultimine.indicator.Constants;
import me.ctidy.mcmod.ftb.ultimine.indicator.client.ClientHandler;
import me.ctidy.mcmod.ftb.ultimine.indicator.config.FTBUltimineIndicatorClientConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

/**
 * ForgeMain
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
@Mod(Constants.MOD_ID)
public final class ForgeMain {

    @SuppressWarnings("removal")
    public ForgeMain() {
        ModLoadingContext.get().registerDisplayTest(IExtensionPoint.DisplayTest.IGNORE_ALL_VERSION);
        // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onReloadListenerRegister);
        if (Dist.CLIENT == FMLEnvironment.dist) {
            FTBUltimineIndicatorClientConfig.init();
            MinecraftForge.EVENT_BUS.addListener(this::onHudRender);
        }
    }

    private void onReloadListenerRegister(final RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(ClientHandler::reload);
    }

    private void onHudRender(final RenderGuiOverlayEvent.Post event) {
        if (VanillaGuiOverlay.CROSSHAIR.type() != event.getOverlay()) {
            return;
        }
        ClientHandler.renderHud(event.getGuiGraphics(), event.getWindow(), event.getPartialTick());
    }

}
