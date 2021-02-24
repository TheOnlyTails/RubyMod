package com.theonlytails.rubymod.entities

import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.block.BlockState
import net.minecraft.entity.EntityType
import net.minecraft.entity.ILivingEntityData
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.DyeColor
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.crafting.Ingredient
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.IServerWorld
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import javax.annotation.Nonnull

/**
 * The ruby sheep entity class.
 *
 * @author TheOnlyTails
 */
class RubySheepEntity(type: EntityType<out SheepEntity?>, worldIn: World) : SheepEntity(type, worldIn) {
	private lateinit var eatGrassGoal: EatGrassGoal
	private var rubySheepTimer = 0

	override fun registerGoals() {
		super.registerGoals()

		eatGrassGoal = EatGrassGoal(this)

		goalSelector.addGoal(0, SwimGoal(this))
		goalSelector.addGoal(1, PanicGoal(this, 1.25))
		goalSelector.addGoal(2, BreedGoal(this, 1.0))
		goalSelector.addGoal(2, BreedGoal(this, 1.0, SheepEntity::class.java))
		goalSelector.addGoal(3, TemptGoal(this, 1.1, TEMPTATION_ITEMS, false))
		goalSelector.addGoal(4, FollowParentGoal(this, 1.1))
		goalSelector.addGoal(5, this.eatGrassGoal)
		goalSelector.addGoal(6, WaterAvoidingRandomWalkingGoal(this, 1.0))
		goalSelector.addGoal(7, LookAtGoal(this, PlayerEntity::class.java, 6.0f))
		goalSelector.addGoal(8, LookRandomlyGoal(this))
	}

	override fun getAmbientSound(): SoundEvent = SoundEvents.ENTITY_SHEEP_AMBIENT

	override fun getDeathSound(): SoundEvent = SoundEvents.ENTITY_SHEEP_DEATH

	override fun getHurtSound(@Nonnull damageSourceIn: DamageSource): SoundEvent = SoundEvents.ENTITY_SHEEP_HURT

	override fun playStepSound(@Nonnull pos: BlockPos, @Nonnull blockIn: BlockState) =
		playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.1f, 1f)

	override fun updateAITasks() {
		rubySheepTimer = this.eatGrassGoal.eatingGrassTimer
		super.updateAITasks()
	}

	override fun livingTick() {
		if (world.isRemote) {
			rubySheepTimer = 0.coerceAtLeast(rubySheepTimer - 1)
		}
		super.livingTick()
	}

	@OnlyIn(Dist.CLIENT)
	override fun handleStatusUpdate(id: Byte) = if (id.toInt() == 10) {
		rubySheepTimer = 40
	} else {
		super.handleStatusUpdate(id)
	}

	override fun shear(category: SoundCategory) {
		world.playMovingSound(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, category, 1.0f, 1.0f)
		this.sheared = true
		val i = 1 + rand.nextInt(3)
		for (j in 0 until i) {
			val itementity = this.entityDropItem(ItemRegistry.rubyWool, 1)
			if (itementity != null) {
				itementity.motion = itementity.motion.add(((rand.nextFloat() - rand.nextFloat()) * 0.1f).toDouble(),
					(rand.nextFloat() * 0.05f).toDouble(),
					((rand.nextFloat() - rand.nextFloat()) * 0.1f).toDouble())
			}
		}
	}

	override fun onSheared(
		player: PlayerEntity?,
		item: ItemStack,
		world: World,
		pos: BlockPos,
		fortune: Int,
	): List<ItemStack> {
		world.playMovingSound(null,
			this,
			SoundEvents.ENTITY_SHEEP_SHEAR,
			if (player == null) SoundCategory.BLOCKS else SoundCategory.PLAYERS,
			1.0f,
			1.0f)
		if (!world.isRemote) {
			this.sheared = true
			val i = 1 + rand.nextInt(3)
			val items: MutableList<ItemStack> = ArrayList()
			for (j in 0 until i) {
				items.add(ItemStack(ItemRegistry.rubyWool))
			}
			return items
		}
		return emptyList()
	}

	override fun onInitialSpawn(
		worldIn: IServerWorld,
		difficultyIn: DifficultyInstance,
		reason: SpawnReason,
		spawnDataIn: ILivingEntityData?,
		dataTag: CompoundNBT?,
	): ILivingEntityData? {
		val onInitialSpawnResult = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag)
		this.fleeceColor = DyeColor.WHITE
		return onInitialSpawnResult
	}

	override fun getLootTable() = id("entities/ruby_sheep")

	override fun canMateWith(otherAnimal: AnimalEntity) = otherAnimal is SheepEntity && otherAnimal.isInLove()

	companion object {
		val TEMPTATION_ITEMS: Ingredient = Ingredient.fromItems(ItemRegistry.ruby, Items.WHEAT)

		@Nonnull
		fun setCustomAttributes(): MutableAttribute {
			// func_233666_p_() -> registerAttributes()
			return func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 10.0)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23)
		}
	}
}
