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

    private static Map<String, TextureAtlasSprite> SHAPE_ICONS = Collections.emptyMap();

    private static final ResourceLocation MISSING_ICON_ID = Constants.id("ftbultimine_indicator/shape_icon/missing");

    private static TextureAtlasSprite missing;

    public static CompletableFuture<Void> reload(
            PreparableReloadListener.PreparationBarrier barrier,
            ResourceManager resourceManager,
            ProfilerFiller preparationProfiler,
            ProfilerFiller reloadProfiler,
            Executor backgroundExecutor,
            Executor gameExecutor
    ) {
        return CompletableFuture.completedFuture(Unit.INSTANCE)
                .thenCompose(barrier::wait)
                .thenRunAsync(ClientHandler::reload, gameExecutor);
    }

    public static void reload() {
        final var atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);

        final ImmutableMap.Builder<String, TextureAtlasSprite> builder = ImmutableMap.builder();
        builder.put("missing", missing = atlas.getSprite(MISSING_ICON_ID));

        final int count = ShapeRegistry.shapeCount();
        for (int i = 0; i < count; i++) {
            final String name = ShapeRegistry.getShape(i).getName();
            TextureAtlasSprite sprite = atlas.getSprite(Constants.id(name).withPrefix("ftbultimine_indicator/shape_icon/"));

            //noinspection resource
            if (MissingTextureAtlasSprite.getLocation().equals(sprite.contents().name())) {
                sprite = missing;
            }
            builder.put(name, sprite);
        }

        SHAPE_ICONS = builder.build();
    }

    public static void renderHud(final GuiGraphics guiGraphics, final Window window, final float partialTicks) {
        if (!FTBUltimineIndicatorClientConfig.enableIndicator.get()
                || !(FTBUltimine.instance.proxy instanceof FTBUltimineClientAccessor client)
                || !client.isPressed()) {
            return;
        }

        final Minecraft mc = Minecraft.getInstance();
        final String shapeName = ShapeRegistry.getShape(client.getShapeIdx()).getName();

        // put the icon and text on the right of the crosshair, and adjust them to the center
        final int centerX = window.getGuiScaledWidth()  / 2 + 30;  // left =  22
        final int centerY = window.getGuiScaledHeight() / 2 -  3;  // top  = -11

        if (FTBUltimineIndicatorClientConfig.showIndicatorStatus.get()) {
            final Component textStatus;
            if (CooldownTracker.isOnCooldown(mc.player)) {
                textStatus = Component.translatable("ftbultimine.info.cooldown").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(12566412)));
            } else if (client.canUltimine() && client.getActualBlocks() > 0) {
                textStatus = Component.translatable("ftbultimine.info.blocks", client.getActualBlocks()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(10731148)));
            } else {
                textStatus = Component.translatable("ftbultimine.info.not_active").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(12542314)));
            }
            guiGraphics.drawString(mc.font, textStatus, centerX - mc.font.width(textStatus) / 2, centerY + 12, 15527924, true);
        }

        if (FTBUltimineIndicatorClientConfig.showShapeName.get() && client.isSneak()) {
            final Component textShapeName = Component.translatable("ftbultimine.shape." + shapeName);
            guiGraphics.drawString(mc.font, textShapeName, centerX + 15, centerY + 2 - mc.font.lineHeight / 2, 15527924, true);
        }

        final TextureAtlasSprite sprite = SHAPE_ICONS.getOrDefault(shapeName, missing);
        //noinspection resource
        final int width = sprite.contents().width();
        //noinspection resource
        final int height = sprite.contents().height();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1, 1, 1, 0.5F);
        guiGraphics.blit(centerX - width / 2, centerY - height / 2, 0, width, height, sprite);
    }

}
