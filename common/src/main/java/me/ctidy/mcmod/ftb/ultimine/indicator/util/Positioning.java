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

package me.ctidy.mcmod.ftb.ultimine.indicator.util;

import dev.ftb.mods.ftblibrary.config.NameMap;

/**
 * Positioning
 *
 * @author Tidy-Bear
 * @since 2025/5/28
 */
public enum Positioning {
    TOP_LEFT(0, 0),
    TOP(1, 0),
    TOP_RIGHT(2, 0),

    LEFT(0, 1),
    CENTER(1, 1),
    RIGHT(2, 1),

    BOTTOM_LEFT(0, 2),
    BOTTOM(1, 2),
    BOTTOM_RIGHT(2, 2)
    ;

    public static final NameMap<Positioning> NAME_MAP = NameMap.of(TOP_RIGHT, values()).baseNameKey("ftblibrary.panel.position").create();

    private final int posX;
    private final int posY;

    Positioning(final int posX, final int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public ActualPosition getPanelPos(
            final int containerW, final int containerH,
            final int contentW, final int contentH,
            final int insetX, final int insetY,
            final double anchorX, final double anchorY
    ) {
        final int x = switch (posX) {
            case 0 -> insetX;
            case 1 -> containerW / 2 + insetX;
            default -> containerW + insetX;
        };
        final int y = switch (posY) {
            case 0 -> insetY;
            case 1 -> containerH / 2 + insetY;
            default -> containerH + insetY;
        };
        return new ActualPosition((int) (x - contentW * anchorX / 2), (int) (y - contentH * anchorY / 2));
    }

    public ActualPosition getPanelPos(
            final int containerW, final int containerH,
            final int contentW, final int contentH,
            final float insetX, final float insetY,
            final double anchorX, final double anchorY) {
        return getPanelPos(containerW, containerH, contentW, contentH, (int) (containerW * insetX / 2), (int) (containerH * insetY / 2), anchorX, anchorY);
    }

    public record ActualPosition(int x, int y) {}

}
