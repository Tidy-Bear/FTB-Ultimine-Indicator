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

package me.ctidy.mcmod.demo.platform;

import me.ctidy.mcmod.demo.Constants;
import me.ctidy.mcmod.demo.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

/**
 * Services
 * <br/>
 * Service loaders are a built-in Java feature that allow us to locate implementations of an interface that vary from one
 * environment to another. In the context of MultiLoader we use this feature to access a mock API in the common code that
 * is swapped out for the platform specific implementation at runtime. <br/>
 *
 * @author Tidy-Bear
 * @since 2025/4/22
 */
public final class Services {

    /**
     * In this example we provide a platform helper which provides information about what platform the mod is running on.
     * For example this can be used to check if the code is running on Forge vs Fabric, or to ask the mod loader if another
     * mod is loaded.
     */
    public static final IPlatformHelper PLATFORM = load();

    /**
     * Load a service for the current environment.<br/>
     * <br/>
     * Your implementation of the service must be defined manually by including a text file in META-INF/services named with
     * the fully qualified class name of the service. Inside the file you should write the fully qualified class name of
     * the implementation to load for the platform. For example our file on Forge points to ForgePlatformHelper while
     * Fabric points to FabricPlatformHelper.
     */
    private static IPlatformHelper load() {
        final var clazz = IPlatformHelper.class;
        final var serviceLoaded = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOGGER.debug("Loaded {} for service {}", serviceLoaded, clazz);
        return serviceLoaded;
    }

    private Services() { }

}
