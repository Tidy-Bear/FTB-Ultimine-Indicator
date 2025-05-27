# FTB Ultimine Indicator

<img alt="logo.png" height="256" src="logo.png"/>

A more intuitive indicator for FTB Ultimine.

## About: FTB Ultimine

FTB Ultimine, made by [FTB Team](https://github.com/FTBTeam), is a mod that provides effective modes for harvesting multiple blocks at once.

[official website](https://feed-the-beast.com/)
| [github](https://github.com/FTBTeam/FTB-Ultimine)
| [curseforge (forge)](https://www.curseforge.com/minecraft/mc-mods/ftb-ultimine-forge)
| [curseforge (fabric)](https://www.curseforge.com/minecraft/mc-mods/ftb-ultimine-fabric)

## Features
### # Ultimine Indicator

**Client side only**

The **core** functionality of the mod.

After pressing Ultimine key (Default: `~`), the untimine information, including the name, shape and status of current shape, is shown at the right of the cross-hair instead of original plain text menu.

**Will hide the original plain text menu by default.**

#### \*\* Resourcepacs support

You can customize shape icons below the path `assets/<namespace>/textures/ftbultimine_indicator/shape_icon`.
The filename is the internal id of each shape, listed as below:

| Shape                                             | Id            |
|---------------------------------------------------|---------------|
| Shapeless                                         | shapeless     |
| Small Tunne                                       | small_tunnel  |
| Small Squar (3x3)                                 | small_square  |
| Large Tunnel (3x3)                                | large_tunnel  |
| Mining Tunnel                                     | mining_tunnel |
| Escape Tunnel                                     | escape_tunnel |
| Layer (A custom shape added by a certain modpack) | layer         |

### # Configuration

Adds a few config to the existing config file `ftbultimine-client.snbt`, detailed as below.

The config file is located at `local` directory (**not in commonly `config` directory**)
and will be auto generated once the game is started (you can also create it manually ahead of time).
The modification will be applied **just by re-entering the save or the server** instead of restarting the game process.

```
{
	# Original config by FTB Ultimine.
	# When holding the Ultimine key, must the player also be sneaking to show the shapes menu?
	# Default: true
	# Recommended to set to false with my mod.
	require_sneak_for_menu: false
	
	# Whether show the plain text menu
	# Default: false
	show_plain_text_menu: false
	
	# Whether to change the trigger mode of the Ultimine key to toggle instead of holding
	# Default: false
	# I prefer setting to true _(:з)∠)_
	toggle: false
	
	# Indicator settings by FTB Ultimine Indicator
	indicator: {
		# Whether to enable the indicator at the right of crosshair
		# Default: true
		enable: true
		
		# Whether to show the shape name at the right of the indicator icon (require be sneaking or require_sneak_for_menu to be false)
		# Default: true
		show_shape_name: true
		
		# Whether to show the current status (64 blocks, on cooldown, etc) below the indicator icon
		# Default: true
		show_status: true
	}
}
```

## Future plan

- Configurable X, Y of the anchor and offset
- ↑ also applied to original plain text menu, and adjust the expansion direction of the menu (up/down, be adapted to placing the menu at the bottom of the screen)
