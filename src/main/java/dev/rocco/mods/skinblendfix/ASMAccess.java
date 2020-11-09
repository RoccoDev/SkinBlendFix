/*
 * Copyright 2020 RoccoDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.rocco.mods.skinblendfix;

import net.minecraft.client.renderer.GlStateManager;

public class ASMAccess {
    public static void preBlend() {
        GlStateManager.enableBlend();
        // SRC_ALPHA, ONE_MINUS_SRC_ALPHA, 1, 0
        // Essentially "converts" the input alpha (which would be instead interpreted as 1.0) to a normal one.
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    }

    public static void postBlend() {
        GlStateManager.disableBlend();
    }
}
