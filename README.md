# RubyMod

![License](https://img.shields.io/badge/License-MIT-brightgreen)
[![Language](https://img.shields.io/badge/language-kotlin-blue?logo=kotlin)](https://kotl.in/)
[![Minecraft 1.16.5](https://img.shields.io/badge/minecraft-1.16.5-green.svg)](https://minecraft.net/)
![GitHub stars](https://img.shields.io/github/stars/TheOnlyTails/RubyMod?style=social)
[![Java CI with Gradle](https://github.com/TheOnlyTails/RubyMod/actions/workflows/gradle.yml/badge.svg)](https://github.com/TheOnlyTails/RubyMod/actions/workflows/gradle.yml)
[![CodeFactor](https://www.codefactor.io/repository/github/theonlytails/rubymod/badge)](https://www.codefactor.io/repository/github/theonlytails/rubymod)
[![CurseForge Downloads](http://cf.way2muchnoise.eu/full_421370_downloads.svg)](https://minecraft.curseforge.com/projects/421370)
[![Modrinth](https://img.shields.io/badge/modrinth-rubymod-green.svg)](https://modrinth.com/mod/rubymod)

## What is RubyMod?

RubyMod is a Minecraft Forge (see https://github.com/theonlytails/rubymodfabric/ for a fabric version) mod for Minecraft 1.16.4/1.16.5.

RubyMod is an open-source mod, which prioritizes sharing its code to help other modders learn.

---

## Features

### [Ruby](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/registries/ItemRegistry.kt#L39)

- The ruby is a new gem with multiple purposes:

- Can be used to craft poisoned apples.

- Can be used to craft red dye.

- Can be used to make ruby tools and armor (pickaxe, sword, axe, shovel, hoe, helmet, chestplate, leggings and boots).

- Obtained from mining or smelting ruby ore.

---

### [Ruby Block](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/registries/BlockRegistry.kt#L24)

- It's a block made out of rubies.

- Comes in a slab, stair, wall, button and pressure plate versions.

---

### [Ruby Ore](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/registries/BlockRegistry.kt#L54)

- Found in the nether, at the lava lakes' floor.

- Drops a ruby when broken (effected by fortune), or the ore itself with silk touch.

- Can be smelted (or blasted in a blast furnace) to get 1 ruby.

---

### [Ruby Tools](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/util/enums/RubyItemTier.kt)

- Better than iron but worse than diamond.

---

### [Ruby Armor](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/util/enums/RubyArmorMaterial.kt)

- Give the same amount of armor points as iron armor, but also give +5 knockback resistance (5 times more than
  netherite!).

- When mining with a ruby pickaxe and full ruby armor, you get haste 1 (Enough to insta-mine stone! No need for a
  beacon!).

---

### [Poisoned Apple](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/items/PoisonedAppleItem.kt)

- Gives you 7 points of hunger and 14.4 saturation points (as much as a golden carrot).

- Effects you with these effects -

  - Nausea 2 for 7 seconds (100%).

  - Poison 2 for 9 seconds (100%).

  - Glowing 1 for 10 seconds (100%).

  - Hunger 3 for 3 seconds (10%).

  - Blindness 3 for 5 seconds (5%).

  - Luck (!) 1 for 1 second (50%).

- Can always be eaten (even at full hunger).

---

### [Ruby Sheep](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/entities/RubySheepEntity.kt)

- A special type of sheep.

- Will follow a player holding wheat or rubies.

- Has 2 more health points than a regular sheep.

- Drops special ruby wool and mutton on death.

- Drops ruby wool when sheared.

- Can be dyed with a ruby.

- Has a spawn egg (Creative Mode only!).

- [Model](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/client/render/RubySheepRenderer.kt#L16), [Renderer](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/client/render/RubySheepRenderer.kt)

---

### [Ruby Wool](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/registries/BlockRegistry.kt#L73)

- Wool that comes from ruby sheep.

- Drops from ruby sheep.

---

### [Ruby Carpet](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/registries/BlockRegistry.kt#L80)

- A carpet made of ruby wool.

---

### [Ghost Water](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/registries/FluidRegistry.kt#L36)

- Water, but red. It's like it isn't even there!

- Will turn to water when it interacts with it.

- Can be picked up in a bucket!

---

### [Stinger Enchantment](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/enchantments/StingerEnchantment.kt)

- When you enchant your sword with this enchantment, it will cause the sword to be poisonous, and every mob you hit will
  be poisoned for 5 seconds.

- Comes in 2 levels.

- Mutually exclusive with Mending and Sharpness.

- Can't be obtained from villagers.

---

### [Ruby Barrel](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/blocks/RubyBarrelBlock.kt)

- The ruby barrel is a barrel with 5 rows of storage space.

- [Tile Entity](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/tileentities/RubyBarrelTileEntity.kt), [Container](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/containers/RubyBarrelContainer.kt), [Screen](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/client/gui/RubyBarrelScreen.kt)

---

### [Ruby Hills](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/world/BiomeMaker.kt#L20)

- A new biome with magical red grass.

- Creepers don't spawn in here!

- Mules naturally spawn here. How? Good question.

- The home for the lovely ruby sheep!

---

### [Potion of Motivation and Laziness](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/registries/PotionRegistry.kt)

- The Potion of Motivation can be brewed with a ruby, and it will give you a boost of speed and jump height.

- The Potion of Laziness is an inverted Potion of Motivation, and it will affect you with slowness and nausea.

---

### [Jeweler](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/registries/VillagerProfessionsRegistry.kt)

- This villager will trade you some valuable stuff for some rubies. Definitely worth a check.

- Works at a ruby barrel.

---

### [Logic Gate](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/blocks/LogicGateBlock.kt)

- A redstone component that acts as 2 logic gates in 1!

- Has 2 modes: "And" mode and "Or" mode.

- When in Or mode, the logic gates takes 2 inputs from its sides and outputs a signal if one of the inputs is on.

- When in And mode, the logic gates takes 2 inputs from its sides and outputs a signal if both are on.

---

### [Data Generators](https://github.com/TheOnlyTails/RubyMod/tree/master/src/main/kotlin/com/theonlytails/rubymod/datagen)

- [Block](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/datagen/BlockLootTablesGenerator.kt) loot tables.

- [Entity](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/datagen/EntityLootTablesGenerator.kt) loot tables.

- [Villager Gifts](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/datagen/GiftLootTablesGenerator.kt) loot tables.

- [Item](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/datagen/ItemTagGenerator.kt) and [Block](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/datagen/BlockTagDataGenerator.kt) Tags.

- [Item Models](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/datagen/ItemModelsGenerator.kt)

- [Lang Files](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/datagen/LangGenerator.kt)

- [Recipes of all kinds](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/theonlytails/rubymod/datagen/RecipesGenerator.kt)

---

## Credits

Thanks to:

- [The Forge Team](https://github.com/minecraftforge), for providing the [modding framework](https://github.com/minecraftforge/minecraftforge),
- [thedarkcolor](https://github.com/thedarkcolour), for creating the [KotlinForForge](https://github.com/thedarkcolour/kotlinforforge) library for Forge modding in Kotlin,
- [TechnoVision](https://github.com/technovisiondev) and [TurtyWurty](https://github.com/darealturtywurty), for creating the tutorial series that inspired the mod,
- Jelly Dash, for creating the beautiful textures for the Ruby sheep and the ruby wool,
- [xf8b](https://github.com/xf8b), for helping a lot with the mod,
- and a lot of other people.

---
