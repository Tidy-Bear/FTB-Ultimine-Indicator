# CTidy's X-Plat Demo (Forge Mod Dev Template)

CTidy's own X-Plat (cross-platform) mod development template. Implementers should change `mod_id`, `version`, `author`, `license`, etc., to actual values.

## Built-ins
### Env
- ParchmentMC Mappings
- SpongePowered Mixin
- MinecraftForge (build by net.neoforged.moddev.legacyforge, legacy support from ModDevGradle)
- Fabric

### Mod dependencies
For free to add / remove.
- JEI (Just Enough Items) / REI (Roughly Enough Items) / EMI
- Jade / WTHIT (What The Hell Is That?) / TOP (The One Probe)
- Catalogue (forge only)
- Configured (forge only)
- Mod Menu (fabric only)
- Searchables (Controlling's dependency)
- Controlling
- Spark
- Xaero's Minimap
- Xaero's World Map

## Notes
It's recommended to separate codes of mod itself (such as api), codes depending on Vanilla MC and codes depending on a specific platform (such as Forge, Fabric, etc.).

When implementing a certain use case, use codes from Vanilla MC instead of platform-specific APIs, unless there are striking defects on Vanilla, or you are ready to coding on each platform.

## LICENSE
The template project is released under the MIT license (LICENSE).

Implementers are free to choose an appropriate license from `license-alternatives` directory or other license for the actual project.
