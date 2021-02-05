# RubyMod

![License](https://img.shields.io/badge/License-MIT-brightgreen)
![Language](https://img.shields.io/badge/language-java-blue?logo=java)
![GitHub stars](https://img.shields.io/github/stars/TheOnlyTails/RubyMod?style=social)
![GitHub last commit](https://img.shields.io/github/last-commit/TheOnlyTails/RubyMod)
[![CodeFactor](https://www.codefactor.io/repository/github/theonlytails/rubymod/badge)](https://www.codefactor.io/repository/github/theonlytails/rubymod)

## What is RubyMod?

RubyMod is a Minecraft Forge mod for Minecraft 1.16/1.16.1 (1.16.2 support coming soon!).

Its purpose is for me to learn the Forge Modding API (using mainly the modding tutorial series by TechnoVision on YT).

---

## Features

### [Ruby](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/registries/ItemRegistry.kt#L39)

- The ruby is a new gem with multiple purposes:

- Can be used to craft poisoned apples.

- Can be used to craft red dye.

- Can be used to make ruby tools and armor (pickaxe, sword, axe, shovel, hoe, helmet, chestplate, leggings and boots).

- Obtained from mining or smelting ruby ore.

---

### [Ruby Block](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/registries/BlockRegistry.kt#L24)

- It's a block made out of rubies.

- Comes in a slab, stair, wall, button and pressure plate versions.

---

### [Ruby Ore](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/registries/BlockRegistry.kt#L54)

- Found in the nether, at the lava lakes' floor.

- Drops a ruby when broken (effected by fortune), or the ore itself with silk touch.

- Can be smelted (or blasted in a blast furnace) to get 1 ruby.

---

### [Ruby Tools](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/util/enums/RubyItemTier.kt)

- Better than iron but worse than diamond.

---

### [Ruby Armor](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/util/enums/RubyArmorMaterial.kt)

- Give the same amount of armor points as iron armor, but also give +5 knockback resistance (5 times more than
  netherite!).

- When mining with a ruby pickaxe and full ruby armor, you get haste 1 (Enough to insta-mine stone! No need for a
  beacon!).

---

### [Poisoned Apple](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/items/PoisonedAppleItem.kt)

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

### [Ruby Sheep](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/entities/RubySheepEntity.kt)

- A special type of sheep.

- Will follow a player holding wheat or rubies.

- Has 2 more health points than a regular sheep.

- Drops special ruby wool and mutton on death.

- Drops ruby wool when sheared.

- Can be dyed with a ruby.

- Has a spawn egg (Creative Mode only!).

- [Model](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/client/render/RubySheepRenderer.kt#L16), [Renderer](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/client/render/RubySheepRenderer.kt)

---

### [Ruby Wool](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/registries/BlockRegistry.kt#L73)

- Wool that comes from ruby sheep.

- Drops from ruby sheep.

---

### [Ruby Carpet](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/registries/BlockRegistry.kt#L80)

- A carpet made of ruby wool.

---

### [Ghost Water](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/registries/FluidRegistry.kt#L36)

- Water, but red. It's like it isn't even there!

- Will turn to water when it interacts with it.

- Can be picked up in a bucket!

---

### [Stinger Enchantment](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/enchantments/StingerEnchantment.kt)

- When you enchant your sword with this enchantment, it will cause the sword to be poisonous, and every mob you hit will
  be poisoned for 5 seconds.

- Comes in 2 levels.

- Mutually exclusive with Mending and Sharpness.

- Can't be obtained from villagers.

---

### [Ruby Barrel](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/blocks/RubyBarrelBlock.kt)

- The ruby barrel is a barrel with 5 rows of storage space.

- [Tile Entity](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/tileentities/RubyBarrelTileEntity.kt), [Container](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/containers/RubyBarrelContainer.kt), [Screen](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/client/gui/RubyBarrelScreen.kt)

---

### [Ruby Hills](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/world/BiomeMaker.kt#L20)

- A new biome with magical red grass.

- Creepers don't spawn in here!

- Mules naturally spawn here. How? Good question.

- The home for the lovely ruby sheep!

---

### [Potion of Motivation and Laziness](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/registries/PotionRegistry.kt)

- The Potion of Motivation can be brewed with a ruby, and it will give you a boost of speed and jump height.

- The Potion of Laziness is an inverted Potion of Motivation, and it will affect you with slowness and nausea.

---

### [Jeweler](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/registries/VillagerProfessionsRegistry.kt)

- This villager will trade you some valuable stuff for some rubies. Definitely worth a check.

- Works at a ruby barrel.

---

### [Logic Gate](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/blocks/LogicGateBlock.kt)

- A redstone component that acts as 2 logic gates in 1!

- Has 2 modes: "And" mode and "Or" mode.

- When in Or mode, the logic gates takes 2 inputs from its sides and outputs a signal if one of the inputs is on.

- When in And mode, the logic gates takes 2 inputs from its sides and outputs a signal if both are on.

---

### [Data Generators](https://github.com/TheOnlyTails/RubyMod/tree/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen)

- [Block](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen/BlockLootTablesGenerator.kt) loot tables.

- [Entity](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen/EntityLootTablesGenerator.kt) loot tables.

- [Villager Gifts](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen/GiftLootTablesGenerator.kt) loot tables.

- [Item](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen/ItemTagGenerator.kt) and [Block](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen/BlockTagDataGenerator.kt) Tags.

- [Item Models](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen/ItemModelsGenerator.kt)

- [Lang Files](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen/LangGenerator.kt)

- [Recipes of all kinds](https://github.com/TheOnlyTails/RubyMod/blob/master/src/main/kotlin/com/github/theonlytails/rubymod/datagen/RecipesGenerator.kt)

## Planned Features

- [ ] **Centrifuge Block:** Takes "Ruby Items" and returns the purified item (e.g. ruby wool -> wool, poisoned apple ->
  apple).

- **And More To Come!**

---

## Credits

Thanks to:

- The Forge Team, for providing the modding framework,
- thedarkcolor, for creating the KotlinForForge library for Forge modding in Kotlin,
- TechnoVision and TurtyWurty, for creating the tutorial series that helped me create it,
- Jelly Dash, for creating the beautiful textures for the Ruby sheep and the ruby wool,
- xf8b, for helping a lot with the mod, and much more people.

---
