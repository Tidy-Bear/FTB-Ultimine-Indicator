/*
 * Copyright (c) 2025 Tidy-Bear
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.ctidy.mcmod.demo.platform.services;

/**
 * IPlatformHelper
 * <br/>
 * A mock API that share common helper method among mod loader platforms and swapped out for the platform specific
 * implementation at runtime, such as ForgePlatformHelper, NeoForgePlatformHelper, FabricPlatformHelper, etc.
 *
 * @author Tidy-Bear
 * @since 2025/4/22
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
