package yuma140902.uptodatemod.client.renderer;

import static yuma140902.yumalib.api.McConst.*;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.blocks.BlockLantern;

public class RenderBlockLantern implements ISimpleBlockRenderingHandler {
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if(modelId == this.getRenderId()) {
//			GL11.glTranslatef(-0.5F, -1.0F, -0.5F);
			GL11.glTranslatef(-0.5F, -1.5F, -0.5F);
			GL11.glScalef(1.5f, 1.5f, 1.5f);
			
			IIcon icon = block.getBlockTextureFromSide(SIDE_TOP());
			
			// 本体の側面
			block.setBlockBounds(0.0f/16.0f, 7.0f/16.0f, 0.0f/16.0f, 6.0f/16.0f, 14.0f/16.0f, 6.0f/16.0f);
			renderCuboidInvWithBlockBounds(renderer, block, 5f/16f, 7f/16f, 5f/16f, icon, FACE_NEG_X | FACE_POS_X | FACE_NEG_Z | FACE_POS_Z);
			
			// 本体の下面と上面
			block.setBlockBounds(0.0f/16.0f, 0.0f/16.0f, 9.0f/16.0f, 6.0f/16.0f, 16.0f/16.0f, 15.0f/16.0f);
			renderCuboidInvWithBlockBounds(renderer, block, 5f/16f, 14f/16f, -4f/16f, icon, FACE_NEG_Y);
			renderCuboidInvWithBlockBounds(renderer, block, 5f/16f, 5f/16f, -4f/16f, icon, FACE_POS_Y);
			
			// 本体の上の出っ張りの側面
			block.setBlockBounds(1.0f/16.0f, 14.0f/16.0f, 1.0f/16.0f, 5.0f/16.0f, 16.0f/16.0f, 5.0f/16.0f);
			renderCuboidInvWithBlockBounds(renderer, block, 5f/16f, 7f/16f, 5f/16f, icon, FACE_NEG_X | FACE_POS_X | FACE_NEG_Z | FACE_POS_Z);
			
			// 本体の上の出っ張りの上面
			block.setBlockBounds(1.0f/16.0f, 0.0f/16.0f, 10.0f/16.0f, 5.0f/16.0f, 16.0f/16.0f, 14.0f/16.0f);
			renderCuboidInvWithBlockBounds(renderer, block, 5f/16f, 7f/16f, -4f/16f, icon, FACE_POS_Y);
			
			block.setBlockBoundsForItemRender();
			
			GL11.glScalef(1f/1.5f, 1f/1.5f, 1f/1.5f);
			GL11.glTranslatef(0.5F, 1.5F, 0.5F);
//			GL11.glTranslatef(0.5F, 1.0F, 0.5F);
		}
	}
	
	
	private final int FACE_NEG_X = 0b000001;
	private final int FACE_POS_X = 0b000010;
	private final int FACE_NEG_Y = 0b000100;
	private final int FACE_POS_Y = 0b001000;
	private final int FACE_NEG_Z = 0b010000;
	private final int FACE_POS_Z = 0b100000;
	/**
	 * インベントリ用
	 * @param faces 描画する面を指定するフラグ。{@link FACE_NEG_X}などを使って指定する
	 */
	private void renderCuboidInvWithBlockBounds(RenderBlocks renderer, Block block, double x, double y, double z, IIcon icon, int faces) {
		Tessellator tessellator = Tessellator.instance;
		
		renderer.setRenderBoundsFromBlock(block);
		
		if((faces & FACE_NEG_X) == FACE_NEG_X) {
			tessellator.startDrawingQuads();
			tessellator.setNormal(-1.0F, 0.0F, 0.0F);
			renderer.renderFaceXNeg(block, x, y, z, icon);
			tessellator.draw();
		}
		
		if((faces & FACE_POS_X) == FACE_POS_X) {
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			renderer.renderFaceXPos(block, x, y, z, icon);
			tessellator.draw();
		}
		
		if((faces & FACE_NEG_Y) == FACE_NEG_Y) {
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, -1.0F, 0.0F);
			renderer.renderFaceYNeg(block, x, y, z, icon);
			tessellator.draw();
		}
		
		if((faces & FACE_POS_Y) == FACE_POS_Y) {
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			renderer.renderFaceYPos(block, x, y, z, icon);
			tessellator.draw();
		}
		
		if((faces & FACE_NEG_Z) == FACE_NEG_Z) {
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, -1.0F);
			renderer.renderFaceZNeg(block, x, y, z, icon);
			tessellator.draw();
		}
		
		if((faces & FACE_POS_Z) == FACE_POS_Z) {
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			renderer.renderFaceZPos(block, x, y, z, icon);
			tessellator.draw();
		}
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(modelId == this.getRenderId()) {
//			block.setBlockBounds(0.0f/16.0f, 0.0f/16.0f, 0.0f/16.0f, 16.0f/16.0f, 16.0f/16.0f, 16.0f/16.0f);
//			renderer.setRenderBoundsFromBlock(block);
//			renderer.renderStandardBlock(block, x, y, z);
			
			int meta = world.getBlockMetadata(x, y, z);
			float y_delta = 0.0f/16.0f;
			boolean renderLongChain = false;
			if(meta == BlockLantern.META_HANGING) {
				y_delta = 1.0f/16.0f;
				renderLongChain = true;
			}
			
			Tessellator tessellator = Tessellator.instance;
			tessellator.setColorOpaque_F(0.8f, 0.8f, 0.8f);
			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			
			// 本体の側面
			block.setBlockBounds(0.0f/16.0f, 7.0f/16.0f, 0.0f/16.0f, 6.0f/16.0f, 14.0f/16.0f, 6.0f/16.0f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderFaceXNeg(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z + 5.0f/16.0f, block.getBlockTextureFromSide(SIDE_WEST()));  // WEST: -x
			renderer.renderFaceXPos(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z + 5.0f/16.0f, block.getBlockTextureFromSide(SIDE_EAST()));  // EAST: +x
			renderer.renderFaceZNeg(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z + 5.0f/16.0f, block.getBlockTextureFromSide(SIDE_NORTH()));  // NORTH: -z
			renderer.renderFaceZPos(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z + 5.0f/16.0f, block.getBlockTextureFromSide(SIDE_SOUTH()));  // SOUTH: +z
			
			// 本体の下面と上面
			block.setBlockBounds(0.0f/16.0f, 0.0f/16.0f, 9.0f/16.0f, 6.0f/16.0f, 16.0f/16.0f, 15.0f/16.0f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderFaceYNeg(block, x + 5.0f/16.0f, y - 0.0f/16.0f+y_delta, z - 4.0f/16.0f, block.getBlockTextureFromSide(SIDE_BOTTOM()));
			renderer.renderFaceYPos(block, x + 5.0f/16.0f, y - 9.0f/16.0f+y_delta, z - 4.0f/16.0f, block.getBlockTextureFromSide(SIDE_TOP()));
			
			// 本体の上の出っ張りの側面
			block.setBlockBounds(1.0f/16.0f, 14.0f/16.0f, 1.0f/16.0f, 5.0f/16.0f, 16.0f/16.0f, 5.0f/16.0f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderFaceXNeg(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z + 5.0f/16.0f, block.getBlockTextureFromSide(SIDE_WEST()));  // WEST: -x
			renderer.renderFaceXPos(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z + 5.0f/16.0f, block.getBlockTextureFromSide(SIDE_EAST()));  // EAST: +x
			renderer.renderFaceZNeg(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z + 5.0f/16.0f, block.getBlockTextureFromSide(SIDE_NORTH()));  // NORTH: -z
			renderer.renderFaceZPos(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z + 5.0f/16.0f, block.getBlockTextureFromSide(SIDE_SOUTH()));  // SOUTH: +z
			
			// 本体の上の出っ張りの上面
			block.setBlockBounds(1.0f/16.0f, 0.0f/16.0f, 10.0f/16.0f, 5.0f/16.0f, 16.0f/16.0f, 14.0f/16.0f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderFaceYPos(block, x + 5.0f/16.0f, y - 7.0f/16.0f+y_delta, z - 4.0f/16.0f, block.getBlockTextureFromSide(SIDE_TOP()));
			
			
			IIcon icon = block.getBlockTextureFromSide(SIDE_TOP());
			if(renderLongChain) {
				// 長い鎖
				
				// 一番下の輪
				double u_begin = icon.getInterpolatedU(11);
				double v_begin = icon.getInterpolatedV(10);
				double u_end = icon.getInterpolatedU(14);
				double v_end = icon.getInterpolatedV(12);
				double width = 3f/16f;
				double height = 2f/16f;
				
				double x_base1 = 7f/16f;
				double x_base2 = 6f/16f;
				double y_base = 10f/16f;
				double z_base1 = 9f/16f;
				double z_base2 = 7f/16f;
				
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base2, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base2, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base1, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base1, 	u_begin, v_end);
				
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base1, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base2, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base2, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base1, 	u_begin, v_end);
				
				
				// 真ん中の輪
				u_begin = icon.getInterpolatedU(11);
				v_begin = icon.getInterpolatedV(1);
				u_end = icon.getInterpolatedU(14);
				v_end = icon.getInterpolatedV(5);
				width = 3f/16f;
				height = 4f/16f;
				
				y_base = 11f/16f;
				
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base1, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base1, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base2, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base2, 	u_begin, v_end);
				
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base2, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base1, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base1, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base2, 	u_begin, v_end);
				
				
				// 一番上の輪
				u_begin = icon.getInterpolatedU(11);
				v_begin = icon.getInterpolatedV(6);
				u_end = icon.getInterpolatedU(14);
				v_end = icon.getInterpolatedV(8);
				width = 3f/16f;
				height = 2f/16f;
				
				y_base = 14f/16f;
				
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base2, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base2, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base1, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base1, 	u_begin, v_end);
				
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base1, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base2, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base2, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base1, 	u_begin, v_end);
				
			}
			else {
				// 短い鎖
				double u_begin = icon.getInterpolatedU(11);
				double v_begin = icon.getInterpolatedV(10);
				double u_end = icon.getInterpolatedU(14);
				double v_end = icon.getInterpolatedV(12);
				double width = 3f/16f;
				double height = 2f/16f;
				double x_base1 = 7f/16f;
				double x_base2 = 6f/16f;
				double y_base = 9f/16f;
				double z_base1 = 9f/16f;
				double z_base2 = 7f/16f;
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base2, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base2, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base1, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base1, 	u_begin, v_end);
				
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base1, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base2, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base2, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base1, 	u_begin, v_end);
				
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base1, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base1, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base2, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base2, 	u_begin, v_end);
				
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base+height, 	z+z_base2, 	u_begin, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base+height, 	z+z_base1, 	u_end, v_begin);
				tessellator.addVertexWithUV(x+x_base2+width, 	y+y_base, 				z+z_base1, 	u_end, v_end);
				tessellator.addVertexWithUV(x+x_base1, 				y+y_base, 				z+z_base2, 	u_begin, v_end);
			}
			
			return true;
		}
		return false;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
	
	@Override
	public int getRenderId() {
		return ModUpToDateMod.lanternRenderId;
	}
	
}
