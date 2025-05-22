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

package me.ctidy.mcmod.ftb.ultimine.indicator.config;

import dev.ftb.mods.ftblibrary.snbt.config.BooleanValue;
import dev.ftb.mods.ftblibrary.snbt.config.SNBTConfig;
import dev.ftb.mods.ftbultimine.config.FTBUltimineClientConfig;

/**
 * FTBUltimineIndicatorClientConfig
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
public interface FTBUltimineIndicatorClientConfig {

    BooleanValue showMenu = FTBUltimineClientConfig.CONFIG
            .addBoolean("show_plain_text_menu", false)
            .comment("Whether show the plain text menu?");

    BooleanValue isToggle = FTBUltimineClientConfig.CONFIG
            .addBoolean("toggle", false)
            .comment("Whether to change the trigger mode of the Ultimine key to toggle instead of holding.");

    SNBTConfig groupIndicator = FTBUltimineClientConfig.CONFIG.addGroup("indicator")
            .comment("Indicator settings by FTB Ultimine Indicator");
    BooleanValue enableIndicator = groupIndicator.addBoolean("enable", true)
            .comment("Whether to enable the indicator at the right of crosshair");
    BooleanValue showIndicatorStatus = groupIndicator.addBoolean("show_status", true)
            .comment("Whether to show the current status (64 blocks, on cooldown, etc) below the indicator icon");
    BooleanValue showShapeName = groupIndicator.addBoolean("show_shape_name", true)
            .comment("Whether to show the shape name at the right of the indicator icon");

    static void init() {}

}
