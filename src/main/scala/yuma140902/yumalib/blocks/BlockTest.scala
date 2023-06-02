package yuma140902.yumalib.blocks

import yuma140902.yumalib.YumaLibCreativeTab
import yuma140902.yumalib.api.blocks.mixins.BlockWithYLBlockModel
import yuma140902.yumalib.api.model._
import yuma140902.yumalib.api.registry.Contexts
import yuma140902.yumalib.api.util.Name
import yuma140902.yumalib.api.{McConst, RegisterableBlock}

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.item.ItemBlock
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess

object BlockTest extends Block(Material.circuits) with RegisterableBlock[ItemBlock] with BlockWithYLBlockModel {
  override val name: Name = Name("test")

  this.setBlockTextureName(Contexts.DEFAULT.nameProvider.domainedTexture(name).str)
  YumaLibCreativeTab.setToTab(this)

  private var testIcon: Option[IIcon] = None
  private var north: Option[IIcon] = None
  private var south: Option[IIcon] = None
  private var west: Option[IIcon] = None
  private var east: Option[IIcon] = None
  private var top: Option[IIcon] = None
  private var bottom: Option[IIcon] = None

  override def registerBlockIcons(register: IIconRegister): Unit = {
    super.registerBlockIcons(register)
    this.testIcon = Option(register.registerIcon(this.getTextureName))
    this.north = Option(register.registerIcon(this.getTextureName + "_n"))
    this.south = Option(register.registerIcon(this.getTextureName + "_s"))
    this.west = Option(register.registerIcon(this.getTextureName + "_w"))
    this.east = Option(register.registerIcon(this.getTextureName + "_e"))
    this.top = Option(register.registerIcon(this.getTextureName + "_top"))
    this.bottom = Option(register.registerIcon(this.getTextureName + "_bottom"))
  }

  override def isOpaqueCube: Boolean = false

  override def getIcon(side: Int, meta: Int): IIcon = side match {
    case McConst.SIDE_NORTH => north.orNull
    case McConst.SIDE_SOUTH => south.orNull
    case McConst.SIDE_WEST => west.orNull
    case McConst.SIDE_EAST => east.orNull
    case McConst.SIDE_TOP => top.orNull
    case McConst.SIDE_BOTTOM => bottom.orNull
    case _ => testIcon.orNull
  }

  override def colorMultiplier(world: IBlockAccess, x: Int, y: Int, z: Int): Int = {
    val r = (x % 8) * 32
    val g = (y % 8) * 32
    val b = (z % 8) * 32
    r * 256 * 256 + g * 256 + b
  }

  override def getYLBlockModel(world: IBlockAccess, x: Int, y: Int, z: Int): YLBlockModel = YLBlockModel(
    textures = Map(
      TextureId("#bottom") -> bottom.orNull,
      TextureId("#top") -> top.orNull,
      TextureId("#north") -> north.orNull,
      TextureId("#south") -> south.orNull,
      TextureId("#west") -> west.orNull,
      TextureId("#east") -> east.orNull
    ),
    ambientOcclusion = true,
    elements = Seq(
      Element(
        cuboid = Cuboid(Point(0, 0, 0), Point(16, 16, 8)),
        down = Face(
          texture = TextureId("#bottom"),
          uv = UvRange(0, 0, 16, 16)
        ),
        up = Face(
          texture = TextureId("#top"),
          uv = UvRange(0, 0, 16, 16)
        ),
        north = Face(
          texture = TextureId("#north"),
          uv = UvRange(0, 0, 16, 16)
        ),
        south = Face(
          texture = TextureId("#south"),
          uv = UvRange(0, 0, 16, 16)
        ),
        west = Face(
          texture = TextureId("#west"),
          uv = UvRange(0, 0, 16, 16)
        ),
        east = Face(
          texture = TextureId("#east"),
          uv = UvRange(0, 0, 16, 16)
        )
      )
    )
  )
}
