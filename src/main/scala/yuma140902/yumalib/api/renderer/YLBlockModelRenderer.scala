package yuma140902.yumalib.api.renderer

import yuma140902.uptodatemod.MyBlocks
import yuma140902.yumalib.ModYumaLib
import yuma140902.yumalib.api.McConst

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.RenderBlocks
import net.minecraft.world.IBlockAccess

object YLBlockModelRenderer extends ISimpleBlockRenderingHandler {
  override def renderInventoryBlock(block: Block, metadata: Int, modelId: Int, renderer: RenderBlocks): Unit = {}

  override def renderWorldBlock(world: IBlockAccess, x: Int, y: Int, z: Int, block: Block, modelId: Int, renderer: RenderBlocks): Boolean = {
    println("Minecraft.isAmbientOcclusionEnabled: " + Minecraft.isAmbientOcclusionEnabled)
    println("block.getLightValue: " + block.getLightValue)
    println("renderer.partialRenderBounds: " + renderer.partialRenderBounds)
    println("render mode: " +
      (if (Minecraft.isAmbientOcclusionEnabled && block.getLightValue == 0) {
        if (renderer.partialRenderBounds) "AmbientOcclusionPartial"
        else "AmbientOcclusion"
      }
      else "ColorMultiplier")
    )
    if (modelId == this.getRenderId) {
      renderer.overrideBlockTexture = MyBlocks.concreteBlock.getIcon(McConst.SIDE_TOP, 0)
      renderer.setRenderBounds(0.2D, 0.2D, 0.2D, 0.8D, 0.8D, 0.8D)
      renderer.renderStandardBlock(block, x, y, z)
      renderer.setRenderBounds(0.4D, 0.4D, 0.0D, 0.6D, 1.0D, 0.6D)
      renderer.renderStandardBlock(block, x, y, z)
      true
    }
    else false
  }

  override def getRenderId: Int = ModYumaLib.ylBlockModelRenderId

  override def shouldRender3DInInventory(modelId: Int): Boolean = false
}
