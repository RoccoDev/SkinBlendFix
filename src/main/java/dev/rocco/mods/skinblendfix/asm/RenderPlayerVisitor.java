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

public class RenderPlayerVisitor extends MethodVisitor {
    public RenderPlayerVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        if(opcode == Opcodes.INVOKESPECIAL) {
            if ("(Lnet/minecraft/client/entity/AbstractClientPlayer;)V".equals(desc) || "(Lbet;)V".equals(desc)) {
                System.out.println("[SkinBlendFix] Found call to setModelVisibilities, hooking.");
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "dev/rocco/mods/skinblendfix/ASMAccess", "preBlend", "()V", false);
            }
            else if("func_76986_a".equals(name) || "a".equals(name)) {
                System.out.println("[SkinBlendFix] Found call to doRender, hooking.");
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "dev/rocco/mods/skinblendfix/ASMAccess", "postBlend", "()V", false);
            }
        }
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }
}
