package yuma140902.uptodatemod.client.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
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
		if(meta == BlockStonecutter.META_EAST || meta == BlockStonecutter.META_WEST) {
			renderer.uvRotateTop = 1;
		}
		
		boolean hasRendered = renderer.renderStandardBlock(block, x, y, z);
		
		renderer.uvRotateTop = 0;
		return hasRendered;
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
