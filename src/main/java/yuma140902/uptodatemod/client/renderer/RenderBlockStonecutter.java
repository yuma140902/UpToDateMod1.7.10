package yuma140902.uptodatemod.client.renderer;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.blocks.BlockStonecutter;
import yuma140902.uptodatemod.util.Stat;

public class RenderBlockStonecutter implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if(modelId != getRenderId()) return;
		
		BlockStonecutter stonecutter = (BlockStonecutter)block;
		IIcon iconTop = stonecutter.getIcon(Stat.SIDE_TOP, metadata);
		IIcon iconSide = stonecutter.getIcon(Stat.SIDE_NORTH, metadata);
		IIcon iconSaw = stonecutter.getIconSaw();
		
		renderInvCuboid(renderer, block, 0.0f, 0.0f, 0.0f, 1.0f, 0.5625f, 1.0f, iconSide, iconTop);
		renderInvCuboid(renderer, block, 0.0f, 9.0f/16, 0.5f, 1.0f, 25.0f/16, 0.5f, iconSaw);
	}
	
	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, IIcon iconSide, IIcon iconTop)
	{
		Tessellator tessellator = Tessellator.instance;
		renderer.setRenderBounds(0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
		renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, iconTop);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, iconTop);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, iconSide);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, iconSide);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, iconSide);
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, iconSide);
		tessellator.draw();
		
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		renderer.setRenderBounds(0.0d, 0.0d, 0.0d, 1.0d, 1.0d, 1.0d);
	}
	
	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, IIcon icon) {
		renderInvCuboid(renderer, block, minX, minY, minZ, maxX, maxY, maxZ, icon, icon);
	}
	
	
	

	@Override
	public boolean 	renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(modelId != getRenderId()) return false;
		
		int meta = world.getBlockMetadata(x, y, z);
		boolean isRotated = (meta == BlockStonecutter.META_EAST || meta == BlockStonecutter.META_WEST);
		if(isRotated) {
			renderer.uvRotateTop = 1;
		}
		
		boolean hasRendered = renderer.renderStandardBlock(block, x, y, z);
		
		renderSawInWorld(world, x, y, z, isRotated, (BlockStonecutter)block, renderer);
		
		renderer.uvRotateTop = 0;
		return hasRendered;
	}
	
	private void renderSawInWorld(IBlockAccess world, int x, int y, int z, boolean isRotated, BlockStonecutter block, RenderBlocks renderer) {
		if(!isRotated) block.setBlockBounds(0.0f, 9.0f/16, 0.5f, 1.0f, 25.0f/16, 0.5f);
		else block.setBlockBounds(0.5f, 9.0f/16, 0.0f, 0.5f, 25.0f/16, 1.0f);
		renderer.setRenderBoundsFromBlock(block);
		
		IIcon iconSaw = block.getIconSaw();
		renderer.setOverrideBlockTexture(iconSaw);
		renderer.renderStandardBlock(block, x, y, z);
		
		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.5625f, 1.0f);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ModUpToDateMod.stonecutterRenderId;
	}
	
}
