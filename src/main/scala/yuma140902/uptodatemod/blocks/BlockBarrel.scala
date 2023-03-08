package yuma140902.uptodatemod.blocks

import yuma140902.uptodatemod.registry.RecipeRegister
import yuma140902.uptodatemod.tileentity.TileEntityBarrel
import yuma140902.uptodatemod.util.DirectionUtil
import yuma140902.uptodatemod.{ModUpToDateMod, MyGuis}
import yuma140902.yumalib.api.blocks.mixins.{DroppableTileEntityProvider, VanillaPistonStyleOrientation}
import yuma140902.yumalib.api.util.Name
import yuma140902.yumalib.api.util.NameExtensions._
import yuma140902.yumalib.api.{IHasRecipes, IRegisterable, RegisterableBlock}

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.block.material.Material
import net.minecraft.block.{Block, BlockRotatedPillar}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemBlock, ItemStack}
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IIcon
import net.minecraft.world.World

object BlockBarrel extends BlockRotatedPillar(Material.wood) with VanillaPistonStyleOrientation with RegisterableBlock[ItemBlock] with DroppableTileEntityProvider[TileEntityBarrel] with IRegisterable with IHasRecipes {
  override val name: Name = Name("barrel")
  private val metaForItemRender = 1
  private var iconBottom: Option[IIcon] = None

  this.setCreativeTab(CreativeTabs.tabDecorations)
  this.setStepSound(Block.soundTypeWood)
  this.setHardness(2.5F)
  this.isBlockContainer = true
  this.setBlockTextureName(ModUpToDateMod.name.domainedTexture(name))

  @SideOnly(Side.CLIENT)
  override def getSubBlocks(item: Item, tab: CreativeTabs, list: java.util.List[_]): Unit = {
    list.asInstanceOf[java.util.List[ItemStack]].add(new ItemStack(item, 1, metaForItemRender))
  }

  override def registerRecipes(): Unit = {
    RecipeRegister.addShapedOre(new ItemStack(this, 1, metaForItemRender),
      "#_#",
      "# #",
      "#_#",
      '#'.asInstanceOf[Object], "plankWood",
      '_'.asInstanceOf[Object], "slabWood")
  }

  override def registerBlockIcons(register: IIconRegister): Unit = {
    this.blockIcon = register.registerIcon(getTextureName + "_side")
    this.field_150164_N = register.registerIcon(getTextureName + "_top")
    this.iconBottom = Option(register.registerIcon(getTextureName + "_bottom"))
  }

  override def getIcon(side: Int, meta: Int): IIcon =
    if (meta == DirectionUtil.getBack(side)) iconBottom.orNull
    else if (meta == side) field_150164_N
    else blockIcon

  override def getRenderType: Int = ModUpToDateMod.barrelRenderId

  override def damageDropped(p_149692_1_ : Int): Int = metaForItemRender

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    player.openGui(ModUpToDateMod.INSTANCE, MyGuis.ID_BARREL, world, x, y, z)
    true
  }

  override def createNewTileEntity(p_149915_1_ : World, p_149915_2_ : Int): TileEntity = new TileEntityBarrel

  override protected def getSideIcon(i: Int): IIcon = this.blockIcon
}
