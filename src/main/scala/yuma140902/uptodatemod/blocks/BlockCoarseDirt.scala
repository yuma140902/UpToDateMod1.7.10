package yuma140902.uptodatemod.blocks

import yuma140902.uptodatemod.registry.RecipeRegister
import yuma140902.uptodatemod.{ModUpToDateMod, MyBlocks}
import yuma140902.yumalib.api.util.Name
import yuma140902.yumalib.api.util.NameExtensions._
import yuma140902.yumalib.api.{IHasRecipes, RegisterableBlock}

import cpw.mods.fml.common.eventhandler.Event.Result
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.item.{ItemBlock, ItemStack}
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.IPlantable
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.event.entity.player.UseHoeEvent

// TODO: object
class BlockCoarseDirt extends Block(Material.ground) with RegisterableBlock[ItemBlock] with IHasRecipes {
  override val name: Name = Name("coarse_dirt")

  this.setHardness(0.5F)
  this.setStepSound(Block.soundTypeGravel)
  this.setHarvestLevel("shovel", 0)
  this.setCreativeTab(CreativeTabs.tabBlock)
  this.setBlockTextureName(ModUpToDateMod.name.domainedTexture(name))

  override def canSustainPlant(world: IBlockAccess, x: Int, y: Int, z: Int, direction: ForgeDirection, plantable: IPlantable): Boolean =
    Blocks.dirt.canSustainPlant(world, x, y, z, direction, plantable)

  @SideOnly(Side.CLIENT)
  override def getIcon(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon = {
    val material = world.getBlock(x, y + 1, z).getMaterial
    if ((material eq Material.snow) || (material eq Material.craftedSnow)) return Blocks.grass.getIcon(world, x, y, z, side)
    super.getIcon(world, x, y, z, side)
  }


  override def registerRecipes(): Unit = {

    RecipeRegister.addShaped(
      new ItemStack(MyBlocks.coarseDirt, 4, 0),
      "GD",
      "DG",
      'D'.asInstanceOf[Object], Blocks.dirt,
      'G'.asInstanceOf[Object], Blocks.gravel
    )

    RecipeRegister.addShaped(
      new ItemStack(MyBlocks.coarseDirt, 4, 0),
      "DG",
      "GD",
      'D'.asInstanceOf[Object], Blocks.dirt,
      'G'.asInstanceOf[Object], Blocks.gravel
    )
  }
}

object BlockCoarseDirt {
  def onUseHoeEvent(event: UseHoeEvent): Unit = {
    if (event.world.getBlock(event.x, event.y, event.z) eq MyBlocks.coarseDirt) {
      event.world.setBlock(event.x, event.y, event.z, Blocks.dirt)
      event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, Block.soundTypeGravel.getStepResourcePath, 1.0F, 0.8F)
      event.setResult(Result.ALLOW)
    }
  }
}