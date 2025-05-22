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

package me.ctidy.mcmod.ftb.ultimine.indicator.forge.config;

import com.electronwill.nightconfig.core.Config;
import me.ctidy.mcmod.ftb.ultimine.indicator.Constants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * ClientConfig
 *
 * @author Tidy-Bear
 * @since 2025/5/22
 */
@OnlyIn(Dist.CLIENT)
public class ClientConfig {

    public static final ForgeConfigSpec SPEC;
    public static final ClientConfig INSTANCE;

    // public final ForgeConfigSpec.BooleanValue aBool;

    static {
        Config.setInsertionOrderPreserved(true);
        Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        SPEC = specPair.getRight();
        INSTANCE = specPair.getLeft();
    }

    private static String nameTKey(final String key) {
        return Constants.key("config", key);
    }

    private static String sectionTKey(final String key) {
        return Constants.key("config", "section", key);
    }

    private static String commentTKey(final String key) {
        return Constants.key("config", key, "tooltip");
    }

    public ClientConfig(ForgeConfigSpec.Builder builder) {
        // builder.push("section_name").translation(sectionTKey("section_name"));

        // aBool = builder.comment("Describe the config entry.",
        //                 "[Default: true]")
        //         .translation(nameTKey("config_name"))
        //         .define("config_name", true);

        // builder.pop();
    }

}
