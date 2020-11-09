# How the mod works (for mod developers)
The mod uses [ASM](https://asm.ow2.io/) to manipulate the bytecode of the `net.minecraft.client.renderer.entity.RenderPlayer`
class (`bln` in the 1.8.9 jar).

The method we want to manipulate is `doRender`.

Before rendering the actual model, the mod enables blending and runs `GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)`.
After rendering it disables blending.

We do the same thing for `TileEntitySkullRenderer`.