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

package dev.rocco.mods.skinblendfix.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TileEntitySkullRendererVisitor extends MethodVisitor {
    public TileEntitySkullRendererVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if(opcode == Opcodes.INVOKESTATIC) {
            if ("func_179141_d".equals(name) || ("bfl".equals(owner) && "d".equals(name))) { // GlStateManager.enableAlpha
                System.out.println("[SkinBlendFix] Found call to enableAlpha, hooking.");
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "dev/rocco/mods/skinblendfix/ASMAccess", "preBlend", "()V", false);
                super.visitMethodInsn(opcode, owner, name, desc, itf);
                return;
            }
            // We don't need postBlend here as it pops the matrix anyway
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }
}
