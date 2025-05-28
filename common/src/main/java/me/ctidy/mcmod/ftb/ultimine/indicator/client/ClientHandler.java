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

package me.ctidy.mcmod.ftb.ultimine.indicator.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.ftb.mods.ftbultimine.CooldownTracker;
import dev.ftb.mods.ftbultimine.FTBUltimine;
import dev.ftb.mods.ftbultimine.shape.ShapeRegistry;
import me.ctidy.mcmod.ftb.ultimine.indicator.Constants;
import me.ctidy.mcmod.ftb.ultimine.indicator.config.FTBUltimineIndicatorClientConfig;
import me.ctidy.mcmod.ftb.ultimine.indicator.mixin.FTBUltimineClientAccessor;
import me.ctidy.mcmod.ftb.ultimine.indicator.util.Positioning;
import me.ctidy.mcmod.ftb.ultimine.indicator.util.ShapeNameActiveMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Unit;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.inventory.InventoryMenu;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * ClientHandler
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
public class ClientHandler {

    public static final String SHAPE_ICON_ID = "shape_icon";

    public static final String SHAPE_ICON_PATH = Constants.MOD_ID + "/" + SHAPE_ICON_ID + "/";

    public static final String SHAPE_ICON_MISSING_ID = "missing";

    private static Map<String, TextureAtlasSprite> SHAPE_ICONS = Collections.emptyMap();

    private static final ResourceLocation MISSING_ICON_ID = Constants.id(SHAPE_ICON_PATH + SHAPE_ICON_MISSING_ID);

    private static TextureAtlasSprite missing;

    public static CompletableFuture<Void> reloadResources(
            PreparableReloadListener.PreparationBarrier barrier,
            ResourceManager resourceManager,
            ProfilerFiller preparationProfiler,
            ProfilerFiller reloadProfiler,
            Executor backgroundExecutor,
            Executor gameExecutor
    ) {
        return CompletableFuture.completedFuture(Unit.INSTANCE)
                .thenCompose(barrier::wait)
                .thenRunAsync(ClientHandler::bindIconSprites, gameExecutor);
    }

    public static void bindIconSprites() {
        final var atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);

        final ImmutableMap.Builder<String, TextureAtlasSprite> builder = ImmutableMap.builder();
        builder.put(SHAPE_ICON_MISSING_ID, missing = atlas.getSprite(MISSING_ICON_ID));

        final int count = ShapeRegistry.shapeCount();
        for (int i = 0; i < count; i++) {
            final String name = ShapeRegistry.getShape(i).getName();
            TextureAtlasSprite sprite = atlas.getSprite(Constants.id(name).withPrefix(SHAPE_ICON_PATH));

            //noinspection resource
            if (MissingTextureAtlasSprite.getLocation().equals(sprite.contents().name())) {
                sprite = missing;
            }
            builder.put(name, sprite);
        }

        SHAPE_ICONS = builder.build();
    }

    public static void renderHud(final GuiGraphics guiGraphics, final Window window, final float partialTicks) {
        if (!FTBUltimineIndicatorClientConfig.SHOW_INDICATOR.get()
                || !(FTBUltimine.instance.proxy instanceof FTBUltimineClientAccessor ultimineClient)
                || !ultimineClient.isPressed()) {
            return;
        }

        final Minecraft mc = Minecraft.getInstance();
        final String shapeName = ShapeRegistry.getShape(ultimineClient.getShapeIdx()).getName();

        // put the icon and text on the right of the crosshair, and adjust them to the center
        // final int centerX = window.getGuiScaledWidth()  / 2 + 30;  // left =  22 when width  = 8
        // final int centerY = window.getGuiScaledHeight() / 2 -  3;  // top  = -11 when height = 8

        if (FTBUltimineIndicatorClientConfig.SHOW_SHAPE_ICON.get()) {
            final TextureAtlasSprite sprite = SHAPE_ICONS.getOrDefault(shapeName, missing);
            //noinspection resource
            final int width = sprite.contents().width();
            //noinspection resource
            final int height = sprite.contents().height();

            // final int x = centerX - width / 2;  // 30  - width * 1.0 / 2
            // final int y = centerY - height / 2;  // -3  - height * 1.0 / 2
            final Positioning.ActualPosition pos = FTBUltimineIndicatorClientConfig.SHAPE_ICON_POSITION.get().getPanelPos(
                    window.getGuiScaledWidth(), window.getGuiScaledHeight(),
                    width, height,
                    FTBUltimineIndicatorClientConfig.SHAPE_ICON_INSET_X.get(), FTBUltimineIndicatorClientConfig.SHAPE_ICON_INSET_Y.get(),
                    FTBUltimineIndicatorClientConfig.SHAPE_ICON_ANCHOR_X.get(), FTBUltimineIndicatorClientConfig.SHAPE_ICON_ANCHOR_Y.get()
            );

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1, 1, 1, 0.5F);
            guiGraphics.blit(pos.x(), pos.y(), 0, width, height, sprite);
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }

        final ShapeNameActiveMode mode = FTBUltimineIndicatorClientConfig.showShapeName.get();
        if (ShapeNameActiveMode.ALWAYS == mode
                || (ShapeNameActiveMode.ON_SCROLLABLE == mode && ultimineClient.isSneak())) {
            final Component textShapeName = Component.translatable("ftbultimine.shape." + shapeName);
            // final int x = centerX + 15;  // 45 - mc.font.width(textStatus) * 0.0 / 2
            // final int y = centerY + 2 - mc.font.lineHeight / 2;  // -1 - mc.font.lineHeight * 1.0 / 2
            final Positioning.ActualPosition pos = FTBUltimineIndicatorClientConfig.SHAPE_NAME_POSITION.get().getPanelPos(
                    window.getGuiScaledWidth(), window.getGuiScaledHeight(),
                    mc.font.width(textShapeName), mc.font.lineHeight,
                    FTBUltimineIndicatorClientConfig.SHAPE_NAME_INSET_X.get(), FTBUltimineIndicatorClientConfig.SHAPE_NAME_INSET_Y.get(),
                    FTBUltimineIndicatorClientConfig.SHAPE_NAME_ANCHOR_X.get(), FTBUltimineIndicatorClientConfig.SHAPE_NAME_ANCHOR_Y.get()
            );
            guiGraphics.drawString(mc.font, textShapeName, pos.x(), pos.y(), 0xECEFF4, true);
        }

        if (FTBUltimineIndicatorClientConfig.SHOW_ULTIMINE_STATUS.get()) {
            final Component textStatus;
            if (CooldownTracker.isOnCooldown(mc.player)) {
                textStatus = Component.translatable("ftbultimine.info.cooldown").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xBFBF8C)));
            } else if (ultimineClient.canUltimine() && ultimineClient.getActualBlocks() > 0) {
                textStatus = Component.translatable("ftbultimine.info.blocks", ultimineClient.getActualBlocks()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xA3BE8C)));
            } else {
                textStatus = Component.translatable("ftbultimine.info.not_active").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xBF616A)));
            }
            // final int x = centerX - mc.font.width(textStatus) / 2;  // 30 - mc.font.width(textStatus) * 1.0 / 2
            // final int y = centerY + 12;  // 9 - mc.font.lineHeight * 0.0 / 2
            final Positioning.ActualPosition pos = FTBUltimineIndicatorClientConfig.ULTIMINE_STATUS_POSITION.get().getPanelPos(
                    window.getGuiScaledWidth(), window.getGuiScaledHeight(),
                    mc.font.width(textStatus), mc.font.lineHeight,
                    FTBUltimineIndicatorClientConfig.ULTIMINE_STATUS_INSET_X.get(), FTBUltimineIndicatorClientConfig.ULTIMINE_STATUS_INSET_Y.get(),
                    FTBUltimineIndicatorClientConfig.ULTIMINE_STATUS_ANCHOR_X.get(), FTBUltimineIndicatorClientConfig.ULTIMINE_STATUS_ANCHOR_Y.get()
            );
            guiGraphics.drawString(mc.font, textStatus, pos.x(), pos.y(), 0xECEFF4, true);
        }
    }

}
