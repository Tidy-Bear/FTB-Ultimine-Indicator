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

package me.ctidy.mcmod.ftb.ultimine.indicator.forge.platform;

import me.ctidy.mcmod.ftb.ultimine.indicator.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

/**
 * ForgePlatformHelper
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
public final class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String name() {
        return "Forge";
    }

    @Override
    public boolean modLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean inDevEnv() {
        return !FMLLoader.isProduction();
    }

}
