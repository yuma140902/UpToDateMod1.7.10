package yuma140902.uptodatemod.blocks

import yuma140902.uptodatemod.ModUpToDateMod
import yuma140902.uptodatemod.util.ColorUtil
import yuma140902.yumalib.api.RegisterableBlock
import yuma140902.yumalib.api.items.ItemBlockColored
import yuma140902.yumalib.api.util.Name

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.block.Block
import net.minecraft.block.material.{MapColor, Material}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.IIcon

// TODO: object
class BlockConcrete extends Block(Material.rock) with RegisterableBlock[ItemBlockColored] {

  override val name: Name = Name("concrete")
  override val cls: Class[ItemBlockColored] = classOf[ItemBlockColored]
  private val iicons: Array[IIcon] = new Array[IIcon](BlockConcrete.META_MAX + 1)

  this.setHardness(1.8F)
  this.setStepSound(Block.soundTypeStone)
  this.setHarvestLevel("pickaxe", 0)
  this.setCreativeTab(CreativeTabs.tabBlock)

  @SideOnly(Side.CLIENT)
  override def registerBlockIcons(register: IIconRegister): Unit = {
    for (meta <- 0 to BlockConcrete.META_MAX) {
      iicons(meta) = register.registerIcon(ModUpToDateMod.name.domainedTexture(Name("concrete_" + ColorUtil.metaToString(meta))).str)
    }
  }

  @SideOnly(Side.CLIENT)
  override def getIcon(side: Int, meta: Int): IIcon = iicons(meta % (BlockConcrete.META_MAX + 1))

  @SideOnly(Side.CLIENT)
  override def getSubBlocks(item: Item, creativeTab: CreativeTabs, list: java.util.List[_]): Unit = {
    for (meta <- 0 to BlockConcrete.META_MAX) {
      list.asInstanceOf[java.util.List[ItemStack]].add(new ItemStack(item, 1, meta))
    }
  }

  override def damageDropped(meta: Int): Int = meta

  override def getMapColor(meta: Int): MapColor = ColorUtil.metaToMapColor(meta)

}

object BlockConcrete {
  val META_MAX: Int = 15
}