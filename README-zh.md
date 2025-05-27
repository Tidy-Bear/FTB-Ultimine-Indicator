# FTB 连锁破坏指示器 （FTB Ultimine Indicator）

<img alt="logo.png" height="256" src="logo.png"/>

为 FTB 连锁破坏添加了更直观的指示器。

## 关于：FTB 连锁破坏（FTB Ultimine）

FTB 连锁破坏，由 [FTB 团队](https://github.com/FTBTeam) 制作，提供了同时收获多个方块的有效模式。

[官网](https://feed-the-beast.com/)
| [github](https://github.com/FTBTeam/FTB-Ultimine)
| [curseforge (forge)](https://www.curseforge.com/minecraft/mc-mods/ftb-ultimine-forge)
| [curseforge (fabric)](https://www.curseforge.com/minecraft/mc-mods/ftb-ultimine-fabric)

## 特性
### # 连锁指示器

**仅客户端**

本 mod 的**核心功能**。

按下 **连锁键** （默认：`~`）后，在十字准星右侧会显示连锁破环的信息，包括当前形状的名称、图标、连锁状态，以替代原有的纯文本菜单。

**会默认隐藏原先的纯文本菜单。**

#### \*\* 资源包修改支持

可以在 `assets/<命名空间>/textures/ftbultimine_indicator/shape_icon` 路径下自定义形状图标，文件名为每个形状的内部代码 id，具体如下：

| 形状            | id            |
|---------------|---------------|
| 不定形           | shapeless     |
| 小隧道           | small_tunnel  |
| 小方形 (3x3)     | small_square  |
| 大隧道 (3x3)     | large_tunnel  |
| 挖矿隧道          | mining_tunnel |
| 逃生隧道          | escape_tunnel |
| 层级（某整合包自增的形状） | layer         |

### # 自定义配置

为既有配置文件 `ftbultimine-client.snbt` 新加了几项配置，详细内容见下。

该配置文件位于 `local` 文件夹底下（**不在寻常的 `config` 里**），启动一次游戏后自动生成（也可以提前手动创建），修改后**重进存档或者服务器即可生效**，无需重启整个游戏进程。

```
{
	# 连锁破坏原生配置项，设为 false 后，不必按下潜行键（默认：Shift）也能用鼠标滚轮切换破坏形状。
	# 默认值：true
	# 配合本 mod，改为 false 使用体验更佳
	require_sneak_for_menu: true
	
	# 是否显示原先的纯文本菜单
	# 默认值：false
	show_plain_text_menu: false
	
	# 是否将连锁键的触发模式改为切换式，替代原先的按住（参照原版高版本对疾跑/潜行的可选修改）
	# 默认值：false
	# 作者本人更喜欢改为 true _(:з)∠)_
	toggle: false
	
	# 指示器设置
	indicator: {
		# 是否在十字准星右侧启用指示器
		# 默认值：true
		enable: true
		
		# 是否在图标右侧显示形状名称（需要按住潜行键（默认：Shift），或者 require_sneak_for_menu 改为 false）
		# 默认值：true
		show_shape_name: true
		
		# 是否在图标下侧显示当前状态 （如，破坏64方块，冷却中，等字样）
		# 默认值：true
		show_status: true
	}
}
```

## 未来计划

- 可配置的 X、Y 锚点与相对偏移
- ↑ 同时应用在原先的纯文本菜单上，另外还能调整菜单的展开方向（向上/向下，方便将菜单放在屏幕靠下位置）
