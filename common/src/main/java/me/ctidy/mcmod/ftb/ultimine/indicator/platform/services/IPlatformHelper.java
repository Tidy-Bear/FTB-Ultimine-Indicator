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

package me.ctidy.mcmod.ftb.ultimine.indicator.platform.services;

/**
 * IPlatformHelper
 * <br/>
 * A mock API that share common helper method among mod loader platforms and swapped out for the platform specific
 * implementation at runtime, such as ForgePlatformHelper, NeoForgePlatformHelper, FabricPlatformHelper, etc.
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform, such as forge, neoforge, fabric, etc.
     */
    String name();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modid The mod to check if it is loaded.
     * @return {@code true} if the mod is loaded, {@code false} otherwise.
     */
    boolean modLoaded(String modid);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return {@code true} if in a development environment, {@code false} otherwise.
     */
    boolean inDevEnv();

    /**
     * Gets the full name of the environment type as a string.
     *
     * @return The full name of the environment type, either 'development' or 'production'.
     */
    default String envFullName() {
        return inDevEnv() ? "development" : "production";
    }

    /**
     * Gets the short name of the environment type as a string.
     *
     * @return The short name of the environment type, either 'dev' (for development) or 'prod' (for production).
     */
    default String envShortName() {
        return inDevEnv() ? "dev" : "prod";
    }

}
