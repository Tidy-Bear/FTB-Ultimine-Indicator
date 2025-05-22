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

package me.ctidy.mcmod.ftb.ultimine.indicator.platform;

import me.ctidy.mcmod.ftb.ultimine.indicator.Constants;
import me.ctidy.mcmod.ftb.ultimine.indicator.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

/**
 * Services
 * <br/>
 * Service loaders are a built-in Java feature that allow us to locate implementations of an interface that vary from one
 * environment to another. In the context of MultiLoader we use this feature to access a mock API in the common code that
 * is swapped out for the platform specific implementation at runtime. <br/>
 *
 * @author Tidy-Bear
 * @since 2025/5/22
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
