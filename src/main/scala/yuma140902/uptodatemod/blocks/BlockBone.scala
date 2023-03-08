package yuma140902.uptodatemod.blocks

import yuma140902.uptodatemod.registry.RecipeRegister
import yuma140902.uptodatemod.{ModUpToDateMod, MyBlocks}
import yuma140902.yumalib.api.blocks.CustomSoundType
import yuma140902.yumalib.api.util.Name
import yuma140902.yumalib.api.util.NameExtensions._
import yuma140902.yumalib.api.{IHasRecipes, RegisterableBlock}

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.block.BlockRotatedPillar
import net.minecraft.block.material.{MapColor, Material}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon

// TODO: object
class BlockBone extends BlockRotatedPillar(Material.rock) with RegisterableBlock with IHasRecipes {
  override val name: Name = Name("bone_block")
  private val soundTypeBone = new CustomSoundType(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "bone", 1.0F, 1.0F)

  this.setHardness(2.0F)
  this.setStepSound(soundTypeBone)
  this.setCreativeTab(CreativeTabs.tabBlock)
  this.setBlockTextureName(ModUpToDateMod.name.domainedTexture(this.name))

  override def getMapColor(i: Int): MapColor = MapColor.sandColor

  @SideOnly(Side.CLIENT)
  override def registerBlockIcons(register: IIconRegister): Unit = {
    blockIcon = register.registerIcon(getTextureName + "_side")
    field_150164_N = register.registerIcon(getTextureName + "_top")
  }

  override def registerRecipes(): Unit = {
    RecipeRegister.addShaped(new ItemStack(MyBlocks.boneBlock),
      "###",
      "###",
      "###",
      '#'.asInstanceOf[Object], new ItemStack(Items.dye, 1, 15)) // 15は骨粉
    RecipeRegister.addShapeless(new ItemStack(Items.dye, 9, 15),
      MyBlocks.boneBlock)
  }

  @SideOnly(Side.CLIENT)
  override protected def getSideIcon(p_150163_1_ : Int): IIcon = blockIcon
}
