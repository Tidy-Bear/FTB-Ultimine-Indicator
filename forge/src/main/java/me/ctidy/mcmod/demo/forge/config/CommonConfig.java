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

package me.ctidy.mcmod.demo.forge.config;

import com.electronwill.nightconfig.core.Config;
import me.ctidy.mcmod.demo.Constants;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * CommonConfig
 *
 * @author Tidy-Bear
 * @since 2025/4/22
 */
public class CommonConfig {

    public static final ForgeConfigSpec SPEC;
    public static final CommonConfig INSTANCE;

    // public final ForgeConfigSpec.BooleanValue aBool;

    static {
        Config.setInsertionOrderPreserved(true);
        Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
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

    public CommonConfig(ForgeConfigSpec.Builder builder) {
        // builder.push("section_name").translation(sectionTKey("section_name"));

        // aBool = builder.comment("Describe the config entry.",
        //                 "[Default: true]")
        //         .translation(nameTKey("config_name"))
        //         .define("config_name", true);

        // builder.pop();
    }

}
