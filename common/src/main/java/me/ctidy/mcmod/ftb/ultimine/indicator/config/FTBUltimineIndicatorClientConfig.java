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
import dev.ftb.mods.ftblibrary.snbt.config.DoubleValue;
import dev.ftb.mods.ftblibrary.snbt.config.EnumValue;
import dev.ftb.mods.ftblibrary.snbt.config.IntValue;
import dev.ftb.mods.ftblibrary.snbt.config.SNBTConfig;
import dev.ftb.mods.ftbultimine.config.FTBUltimineClientConfig;
import me.ctidy.mcmod.ftb.ultimine.indicator.util.Positioning;
import me.ctidy.mcmod.ftb.ultimine.indicator.util.ShapeNameActiveMode;

/**
 * FTBUltimineIndicatorClientConfig
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
public interface FTBUltimineIndicatorClientConfig {

    BooleanValue SHOW_OVERLAY = FTBUltimineClientConfig.CONFIG
            .addBoolean("show_overlay", false)
            .comment("Whether to show the overlay, i.e. plain text menu");

    BooleanValue IS_TOGGLE = FTBUltimineClientConfig.CONFIG
            .addBoolean("toggle", false)
            .comment("Whether to change the trigger mode of the Ultimine key to toggle instead of holding");

    SNBTConfig GROUP_INDICATOR = FTBUltimineClientConfig.CONFIG
            .addGroup("indicator")
            .withDisplayOrder(1)
            .comment("Settings of FTB Ultimine Indicator located at the right of cross-hair by default");
    BooleanValue SHOW_INDICATOR = GROUP_INDICATOR
            .addBoolean("enable", true)
            .comment("Whether to display the indicator");

    // ///////////////////////// SHAPE ICON /////////////////////////
    SNBTConfig GROUP_SHAPE_ICON = GROUP_INDICATOR
            .addGroup("shape_icon")
            .withDisplayOrder(1)
            .comment("Settings of the shape icon located at the top-center of the indicator by default");
    BooleanValue SHOW_SHAPE_ICON = GROUP_SHAPE_ICON
            .addBoolean("enable", true)
            .comment("Whether to display the shape icon");
    EnumValue<Positioning> SHAPE_ICON_POSITION = GROUP_SHAPE_ICON
            .addEnum("pos", Positioning.NAME_MAP, Positioning.CENTER)
            .withDisplayOrder(1)
            .comment("Base position of the shape icon");
    IntValue SHAPE_ICON_INSET_X = GROUP_SHAPE_ICON
            .addInt("inset_x", 30)
            .withDisplayOrder(2)
            .comment("X offset of the shape icon");
    IntValue SHAPE_ICON_INSET_Y = GROUP_SHAPE_ICON
            .addInt("inset_y", -3)
            .withDisplayOrder(3)
            .comment("Y offset of the shape icon");
    // DoubleValue SHAPE_ICON_POS_X = GROUP_SHAPE_ICON
    //         .addDouble("pos_x", 0.57, 0, 1)
    //         .withDisplayOrder(2)
    //         .comment("X position of the shape icon, in ratio");
    // DoubleValue SHAPE_ICON_POS_Y = GROUP_SHAPE_ICON
    //         .addDouble("pos_y", 0.49, 0, 1)
    //         .withDisplayOrder(3)
    //         .comment("Y position of the shape icon, in ratio");
    DoubleValue SHAPE_ICON_ANCHOR_X = GROUP_SHAPE_ICON
            .addDouble("anchor_x", 1, 0, 2)
            .withDisplayOrder(4)
            .comment("X anchor of the shape icon, in ratio, 0.0 - left, 1.0 - center, 2.0 - right");
    DoubleValue SHAPE_ICON_ANCHOR_Y = GROUP_SHAPE_ICON
            .addDouble("anchor_y", 1, 0, 2)
            .withDisplayOrder(5)
            .comment("Y anchor of the shape icon, in ratio, 0.0 - left, 1.0 - center, 2.0 - right");

    // ///////////////////////// SHAPE NAME /////////////////////////
    SNBTConfig GROUP_SHAPE_NAME = GROUP_INDICATOR
            .addGroup("shape_name")
            .withDisplayOrder(2)
            .comment("Settings of the shape name located at the right of the shape icon by default");
    EnumValue<ShapeNameActiveMode> showShapeName = GROUP_SHAPE_NAME
            .addEnum("enable", ShapeNameActiveMode.NAME_MAP)
            .comment("When to display the shape name",
                    "on_scrollable - the default mode where the shape name is displayed when player is sneaking or 'require_sneak_for_menu' is false",
                    "always - always display",
                    "never - never display"
            );
    EnumValue<Positioning> SHAPE_NAME_POSITION = GROUP_SHAPE_NAME
            .addEnum("pos", Positioning.NAME_MAP, Positioning.CENTER)
            .withDisplayOrder(1)
            .comment("Base position of the shape name");
    IntValue SHAPE_NAME_INSET_X = GROUP_SHAPE_NAME
            .addInt("inset_x", 45)
            .withDisplayOrder(2)
            .comment("X offset of the shape name");
    IntValue SHAPE_NAME_INSET_Y = GROUP_SHAPE_NAME
            .addInt("inset_y", -1)
            .withDisplayOrder(3)
            .comment("Y offset of the shape name");
    // DoubleValue SHAPE_NAME_POS_X = GROUP_SHAPE_NAME
    //         .addDouble("pos_x", 0.6, 0, 1)
    //         .withDisplayOrder(2)
    //         .comment("X position of the shape name, in ratio");
    // DoubleValue SHAPE_NAME_POS_Y = GROUP_SHAPE_NAME
    //         .addDouble("pos_y", 0.49, 0, 1)
    //         .withDisplayOrder(3)
    //         .comment("Y position of the shape name, in ratio");
    DoubleValue SHAPE_NAME_ANCHOR_X = GROUP_SHAPE_NAME
            .addDouble("anchor_x", 0, 0, 2)
            .withDisplayOrder(4)
            .comment("X anchor of the shape name, in ratio, 0.0 - left, 1.0 - center, 2.0 - right");
    DoubleValue SHAPE_NAME_ANCHOR_Y = GROUP_SHAPE_NAME
            .addDouble("anchor_y", 1, 0, 2)
            .withDisplayOrder(5)
            .comment("Y anchor of the shape name, in ratio, 0.0 - left, 1.0 - center, 2.0 - right");

    // ///////////////////////// ULTIMINE STATUS /////////////////////////
    SNBTConfig GROUP_ULTIMINE_STATUS = GROUP_INDICATOR
            .addGroup("ultimine_status")
            .withDisplayOrder(3)
            .comment("Settings of the ultimine status below the shape icon by default (Mining 64 blocks, on cooldown, etc)");
    BooleanValue SHOW_ULTIMINE_STATUS = GROUP_ULTIMINE_STATUS
            .addBoolean("enable", true)
            .comment("Whether to display the ultimine status");
    EnumValue<Positioning> ULTIMINE_STATUS_POSITION = GROUP_ULTIMINE_STATUS
            .addEnum("pos", Positioning.NAME_MAP, Positioning.CENTER)
            .withDisplayOrder(1)
            .comment("Base position of the ultimine status");
    IntValue ULTIMINE_STATUS_INSET_X = GROUP_ULTIMINE_STATUS
            .addInt("inset_x", 30)
            .withDisplayOrder(2)
            .comment("X offset of the ultimine status");
    IntValue ULTIMINE_STATUS_INSET_Y = GROUP_ULTIMINE_STATUS
            .addInt("inset_y", 9)
            .withDisplayOrder(3)
            .comment("Y offset of the ultimine status");
    // DoubleValue ULTIMINE_STATUS_POS_X = GROUP_ULTIMINE_STATUS
    //         .addDouble("pos_x", 0.57, 0, 1)
    //         .withDisplayOrder(2)
    //         .comment("X position of the ultimine status, in ratio");
    // DoubleValue ULTIMINE_STATUS_POS_Y = GROUP_ULTIMINE_STATUS
    //         .addDouble("pos_y", 0.54, 0, 1)
    //         .withDisplayOrder(3)
    //         .comment("Y position of the ultimine status, in ratio");
    DoubleValue ULTIMINE_STATUS_ANCHOR_X = GROUP_ULTIMINE_STATUS
            .addDouble("anchor_x", 1, 0, 2)
            .withDisplayOrder(4)
            .comment("X anchor of the ultimine status, in ratio, 0.0 - left, 1.0 - center, 2.0 - right");
    DoubleValue ULTIMINE_STATUS_ANCHOR_Y = GROUP_ULTIMINE_STATUS
            .addDouble("anchor_y", 0, 0, 2)
            .withDisplayOrder(5)
            .comment("Y anchor of the ultimine status, in ratio, 0.0 - left, 1.0 - center, 2.0 - right");

    static void init() {}

}
