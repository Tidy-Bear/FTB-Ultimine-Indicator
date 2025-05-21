# CTidy's Forge Demo （Forge Mod 开发模板）

CTidy（熊老师）自己的 X-Plat（跨平台） Mod 开发模板。具体开发时，请将 `mod_id`、`version`、`author`、`license` 等内容修改为实际值。

## 预置组件
### 开发环境
- ParchmentMC 映射
- SpongePowered Mixin
- MinecraftForge (通过 net.neoforged.moddev.legacyforge 构建，基于 ModDevGradle 的旧版本支持)
- Fabric

### Mod 依赖
可以按需要自由增删。
- JEI (Just Enough Items) / REI (Roughly Enough Items) / EMI
- Jade / WTHIT (What The Hell Is That?) / TOP (The One Probe)
- Catalogue (仅 forge 端)
- Configured (仅 forge 端)
- Mod Menu (仅 fabric 端)
- Searchables (Controlling 前置)
- Controlling
- Spark
- Xaero's Minimap
- Xaero's World Map

## 备注
推荐将 Mod 本身的代码（如 api）、依赖于原版 MC 的代码 以及 依赖于指定平台（如 Forge、Fabric 等）的代码相分离。

当实现某个功能用例时，尽量优先使用原版 MC 的代码，再考虑指定平台的 API，除非原版代码具有过于显著的缺陷，或者你准备好在每个平台上都进行一次实现。

## 开源协议
模板项目基于 MIT 协议 (LICENSE) 开源。

实现者可自由选择 `license-alternatives` 目录中的任意一份协议，或者其他协议，作为实际项目的开源协议。
