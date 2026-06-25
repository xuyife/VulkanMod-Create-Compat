## VulkanMod Create Compat

让 VulkanMod 与 Create 模组兼容的轻量补丁。

### 修复
- 启动时 `GL_DEPTH24_STENCIL8` 格式映射缺失导致的崩溃
- Framebuffer 绑定 stencil attachment 时抛出 `UnsupportedOperationException`
- 蓝图渲染器空指针导致的世界渲染崩溃

### 已知取舍
- 蓝图手持预览（第一阶段）不显示半透明投影，部署后（第二阶段）及放置后（第三阶段）渲染正常
- 工程图、蓝图 UI 的模板裁剪边框可能不生效

### 支持版本
- 1.20.1（当前）
- 1.19.2（计划中）
- 1.18.2（计划中）