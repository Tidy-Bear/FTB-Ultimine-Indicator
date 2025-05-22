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

package me.ctidy.mcmod.ftb.ultimine.indicator.mixin;

import dev.ftb.mods.ftbultimine.client.FTBUltimineClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * FTBUltimineClientMixin
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
@Mixin(value = FTBUltimineClient.class, remap = false)
public interface FTBUltimineClientAccessor {

    @Accessor
    boolean isPressed();

    @Invoker("sneak")
    boolean isSneak();

    @Accessor("canUltimine")
    boolean canUltimine();

    @Accessor
    int getActualBlocks();

    @Accessor
    int getShapeIdx();

}
