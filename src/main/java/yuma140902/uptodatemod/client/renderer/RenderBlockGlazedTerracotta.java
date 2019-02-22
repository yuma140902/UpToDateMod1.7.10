package yuma140902.uptodatemod.client.renderer;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.blocks.BlockGlazedTerracotta;

public class RenderBlockGlazedTerracotta implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if(modelId == this.getRenderId()) {
			Tessellator tessellator = Tessellator.instance;
			
      renderer.setRenderBoundsFromBlock(block);
//      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, -1.0F, 0.0F);
      renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
      tessellator.draw();

      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 1.0F, 0.0F);
      renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
      tessellator.draw();

      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, -1.0F);
      renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
      tessellator.draw();
      
      tessellator.startDrawingQuads();
      tessellator.setNormal(0.0F, 0.0F, 1.0F);
      renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
      tessellator.draw();
      
      tessellator.startDrawingQuads();
      tessellator.setNormal(-1.0F, 0.0F, 0.0F);
      renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
      tessellator.draw();
      
      tessellator.startDrawingQuads();
      tessellator.setNormal(1.0F, 0.0F, 0.0F);
      renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
      tessellator.draw();
      
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}

	@Override
	public boolean 	renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(modelId == this.getRenderId()) {
			int meta = world.getBlockMetadata(x, y, z);
			
			
			//0:↑とすると, 1:→, 2:←, 3:↓
			
			if(meta == BlockGlazedTerracotta.META_NORTH) {
				renderer.uvRotateTop = 0;
				renderer.uvRotateBottom = 3;
				
				renderer.uvRotateNorth = 0;
				renderer.uvRotateEast = 1;
				renderer.uvRotateSouth = 3;
				renderer.uvRotateWest = 2;
			}
			else if(meta == BlockGlazedTerracotta.META_EAST) {
				renderer.uvRotateTop = 1;
				renderer.uvRotateBottom = 2;
				
				renderer.uvRotateNorth = 2;
				renderer.uvRotateEast = 0;
				renderer.uvRotateSouth = 1;
				renderer.uvRotateWest = 3;
			}
			else if(meta == BlockGlazedTerracotta.META_SOUTH) {
				renderer.uvRotateTop = 3;
				renderer.uvRotateBottom = 0;
				
				renderer.uvRotateNorth = 3;
				renderer.uvRotateEast = 2;
				renderer.uvRotateSouth = 0;
				renderer.uvRotateWest = 1;
			}
			else {
				renderer.uvRotateTop = 2;
				renderer.uvRotateBottom = 1;
				
				renderer.uvRotateNorth = 1;
				renderer.uvRotateEast = 3;
				renderer.uvRotateSouth = 2;
				renderer.uvRotateWest = 0;
			}
			
			
			boolean flag = renderer.renderStandardBlock(block, x, y, z);
			renderer.uvRotateTop = 0;
			renderer.uvRotateBottom = 0;
			renderer.uvRotateNorth = 0;
			renderer.uvRotateEast = 0;
			renderer.uvRotateSouth = 0;
			renderer.uvRotateWest = 0;
			return flag;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ModUpToDateMod.glazedTerracottaRenderId;
	}
	
}
