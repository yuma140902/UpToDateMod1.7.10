package yuma140902.uptodatemod.client.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.blocks.BlockStonecutter;

public class RenderBlockStonecutter implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if(modelId != getRenderId()) return;
		
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
		return false;
	}

	@Override
	public int getRenderId() {
		return ModUpToDateMod.stonecutterRenderId;
	}
	
}
