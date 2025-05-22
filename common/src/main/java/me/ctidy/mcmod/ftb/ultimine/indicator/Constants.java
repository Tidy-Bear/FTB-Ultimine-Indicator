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

package me.ctidy.mcmod.ftb.ultimine.indicator;

import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Constants
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
public final class Constants {

    public static final String MOD_ID = "ftbultimine_indicator";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static ResourceLocation id(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static String key(String... nodes) {
        String[] elements = new String[nodes.length + 2];
        elements[0] = MOD_ID;
        System.arraycopy(nodes, 0, elements, 2, nodes.length);
        return String.join(".", elements);
    }

    private Constants() { }

}
