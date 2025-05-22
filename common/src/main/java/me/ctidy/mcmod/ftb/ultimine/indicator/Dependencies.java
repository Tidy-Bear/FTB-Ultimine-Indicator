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

package me.ctidy.mcmod.ftb.ultimine.indicator;

import me.ctidy.mcmod.ftb.ultimine.indicator.platform.Services;
import org.jetbrains.annotations.Unmodifiable;

/**
 * Dependencies
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
public enum Dependencies implements IModDependency {

    FORGE("forge"),
    FABRIC("fabric");

    @Unmodifiable
    public static final Dependencies[] VALUES = values();

    public final String modid;

    private Boolean loaded;

    Dependencies(String modid) {
        this.modid = modid;
    }

    @Override
    public String modid() {
        return modid;
    }

    @Override
    public boolean loaded() {
        if (loaded == null) {
            loaded = Services.PLATFORM.modLoaded(modid);
        }
        return loaded;
    }

}
