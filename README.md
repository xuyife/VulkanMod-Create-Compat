## VulkanMod Create Compat

[简体中文](README_ZH.md)

A lightweight patch that makes VulkanMod compatible with the Create mod.

### Fixes
- Crash on startup caused by missing `GL_DEPTH24_STENCIL8` format mapping
- `UnsupportedOperationException` when binding stencil attachment in Framebuffer
- `NullPointerException` in schematic renderer causing world rendering crash

### Known Trade-offs
- Blueprint hand-held preview (phase 1) does not render; preview after deployment (phase 2) and placed blocks (phase 3) work normally
- Schematic/blueprint UI stencil clipping borders may not display

### Supported Versions
- 1.20.1 (current)
- 1.19.2 (planned)
- 1.18.2 (planned)