package yuma140902.yumalib.client.renderer;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.client.model.IBlockWithYLBlockModel;
import yuma140902.yumalib.api.client.model.YLBlockModel;
import yuma140902.yumalib.api.util.BlockPos;
import yuma140902.yumalib.api.util.WorldUtils;

public class RenderYLBlockModel implements ISimpleBlockRenderingHandler {
	@Override public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
	
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(modelId != this.getRenderId()) return false;
		if(world.getBlockMetadata(x, y, z) == 1){
			renderer.renderStandardBlock(block, x, y, z);
			return true;
		}
		
		if(!(block instanceof IBlockWithYLBlockModel)) return false;
		
		YLBlockModel model = ((IBlockWithYLBlockModel) block).getYLBlockModelInWorld(world, x, y, z);
		renderYLBlockModel(block, x, y, z, model, renderer);
		
		return true;
	}
	
	private void renderYLBlockModel(Block block, int x, int y, int z, YLBlockModel model, RenderBlocks renderer){
		for(final YLBlockModel.Element element : model.getElements()){
			renderElement(block, x, y, z, element, renderer);
		}
	}
	
	private void renderElement(Block block, int x, int y, int z, YLBlockModel.Element element, RenderBlocks renderer){
		int[] from = element.getFrom();
		int[] to = element.getTo();
		renderer.setRenderBounds(from[0]/16f, from[1]/16f, from[2]/16f, to[0]/16f, to[1]/16f, to[2]/16f);
		
		if(Minecraft.isAmbientOcclusionEnabled() && block.getLightValue() == 0){
			if(renderer.partialRenderBounds)
				this.renderFacesWithAmbientOcclusionPartial(block, x, y, z, element, renderer);
			else
				this.renderFacesWithAmbientOcclusion(block, x, y, z, element, renderer);
		}
		else{
			this.renderFacesWithColorMultiplier(block, x, y, z, element, renderer);
		}
	}
	
	private void renderFacesWithAmbientOcclusion(Block block, int x, int y, int z, YLBlockModel.Element element, RenderBlocks renderer) {
		int colorMultiplier = block.colorMultiplier(renderer.blockAccess, x, y, z);
		float colorR = (float) (colorMultiplier >> 16 & 255) / 255.0F;
		float colorG = (float) (colorMultiplier >> 8 & 255) / 255.0F;
		float colorB = (float) (colorMultiplier & 255) / 255.0F;
		
		if (EntityRenderer.anaglyphEnable) {
			float colorRCnv = (colorR * 30.0F + colorG * 59.0F + colorB * 11.0F) / 100.0F;
			float colorGCnv = (colorR * 30.0F + colorG * 70.0F) / 100.0F;
			float colorBCnv = (colorR * 30.0F + colorB * 70.0F) / 100.0F;
			colorR = colorRCnv;
			colorG = colorGCnv;
			colorB = colorBCnv;
		}
		
		renderer.enableAO = true;
		float f3 = 0.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		boolean isNotGrassTop = true;
		int mixedBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(983055);
		
		if (renderer.getBlockIcon(block).getIconName().equals("grass_top")) {
			isNotGrassTop = false;
		}
		
		boolean flag2;
		boolean flag3;
		boolean flag4;
		boolean flag5;
		int i1;
		float f7;
		
		BlockPos pos = new BlockPos(x, y, z);
		IBlockWithYLBlockModel blockWithModel = (IBlockWithYLBlockModel)block;
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_BOTTOM), pos, renderer.blockAccess)) {
			if (element.getFromY() <= 0) {
				--y;
			}
			
			renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			renderer.aoLightValueScratchXYNN = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPN = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			boolean opaqueXYPN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
			boolean opaqueXYNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
			boolean opaqueZYPN = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
			boolean opaqueZYNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();
			
			if (!opaqueZYNN && !opaqueXYNN) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
			}
			
			if (!opaqueZYPN && !opaqueXYNN) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
			}
			
			if (!opaqueZYNN && !opaqueXYPN) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
			}
			
			if (!opaqueZYPN && !opaqueXYPN) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
			}
			
			if (element.getFromY() <= 0.0D) {
				++y;
			}
			
			int mixedBrightnessForBottom = mixedBrightness;
			
			if (element.getFromY() <= 0.0D || !renderer.blockAccess.getBlock(x, y - 1, z).isOpaqueCube()) {
				mixedBrightnessForBottom = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			}
			
			float atLightValueBottom = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			f3 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchYZNP + atLightValueBottom) / 4.0F;
			f6 = (renderer.aoLightValueScratchYZNP + atLightValueBottom + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
			f5 = (atLightValueBottom + renderer.aoLightValueScratchYZNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
			f4 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNN + atLightValueBottom + renderer.aoLightValueScratchYZNN) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN, renderer.aoBrightnessYZNP, mixedBrightnessForBottom);
			renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXYPN, mixedBrightnessForBottom);
			renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNN, mixedBrightnessForBottom);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN, renderer.aoBrightnessYZNN, mixedBrightnessForBottom);
			
			if (isNotGrassTop) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.5F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.5F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.5F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			this.renderFaceBottom(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_BOTTOM), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_TOP), pos, renderer.blockAccess)) {
			if (element.getTo()[1] >= 16) {
				++y;
			}
			
			renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			flag2 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
			
			if (!flag5 && !flag3) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
			}
			
			if (!flag5 && !flag2) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
			}
			
			if (!flag4 && !flag3) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
			}
			
			if (!flag4 && !flag2) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
			}
			
			if (element.getTo()[1] >= 16) {
				--y;
			}
			
			i1 = mixedBrightness;
			
			if (element.getTo()[1] >= 16 || !renderer.blockAccess.getBlock(x, y + 1, z).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			}
			
			f7 = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
			f3 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
			f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
			f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
			renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
			renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB;
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			this.renderFaceTop(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_TOP), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_NORTH), pos, renderer.blockAccess)) {
			if (element.getFromZ() <= 0) {
				--z;
			}
			
			renderer.aoLightValueScratchXZNN = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPN = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			flag2 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();
			
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
			}
			
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
			}
			
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
			}
			
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
			}
			
			if (element.getFromZ() <= 0) {
				++z;
			}
			
			i1 = mixedBrightness;
			
			if (element.getFromZ() <= 0 || !renderer.blockAccess.getBlock(x, y, z - 1).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			}
			
			f7 = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			f3 = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
			f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
			f5 = (renderer.aoLightValueScratchYZNN + f7 + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
			f6 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchYZNN + f7) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, i1);
			renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, i1);
			
			if (isNotGrassTop) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.8F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.8F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.8F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			this.renderFaceNorth(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_NORTH), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_SOUTH), pos, renderer.blockAccess)) {
			if (element.getTo()[2] >= 16) {
				++z;
			}
			
			renderer.aoLightValueScratchXZNP = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPP = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			flag2 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
			
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
			}
			
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
			}
			
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
			}
			
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
			}
			
			if (element.getTo()[2] >= 16) {
				--z;
			}
			
			i1 = mixedBrightness;
			
			if (element.getTo()[2] >= 16 || !renderer.blockAccess.getBlock(x, y, z + 1).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			}
			
			f7 = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			f3 = (renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
			f6 = (f7 + renderer.aoLightValueScratchYZPP + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
			f5 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
			f4 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, i1);
			renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, i1);
			
			if (isNotGrassTop) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.8F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.8F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.8F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			this.renderFaceSouth(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_SOUTH), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_WEST), pos, renderer.blockAccess)) {
			if (element.getFromX() <= 0) {
				--x;
			}
			
			renderer.aoLightValueScratchXYNN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZNN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZNP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			flag2 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
			
			if (!flag4 && !flag3) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
			}
			
			if (!flag5 && !flag3) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
			}
			
			if (!flag4 && !flag2) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
			}
			
			if (!flag5 && !flag2) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
			}
			
			if (element.getFromX() <= 0) {
				++x;
			}
			
			i1 = mixedBrightness;
			
			if (element.getFromX() <= 0 || !renderer.blockAccess.getBlock(x - 1, y, z).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			}
			
			f7 = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			f6 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
			f3 = (f7 + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
			f4 = (renderer.aoLightValueScratchXZNN + f7 + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
			f5 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXZNN + f7) / 4.0F;
			renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, i1);
			renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, i1);
			
			if (isNotGrassTop) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.6F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.6F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.6F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			this.renderFaceWest(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_WEST), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_EAST), pos, renderer.blockAccess)) {
			if (element.getTo()[0] >= 16) {
				++x;
			}
			
			renderer.aoLightValueScratchXYPN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			flag2 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
			
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
			}
			
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
			}
			
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
			}
			
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
			}
			
			if (element.getTo()[0] >= 16) {
				--x;
			}
			
			i1 = mixedBrightness;
			
			if (element.getTo()[0] >= 16 || !renderer.blockAccess.getBlock(x + 1, y, z).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			}
			
			f7 = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			f3 = (renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
			f4 = (renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXZPN + f7) / 4.0F;
			f5 = (renderer.aoLightValueScratchXZPN + f7 + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
			f6 = (f7 + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
			renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, i1);
			
			if (isNotGrassTop) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.6F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.6F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.6F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			this.renderFaceEast(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_EAST), renderer);
		}
		
		renderer.enableAO = false;
	}
	
	private void renderFacesWithAmbientOcclusionPartial(Block block, int x, int y, int z, YLBlockModel.Element element, RenderBlocks renderer) {
		int colorMultiplier = block.colorMultiplier(renderer.blockAccess, x, y, z);
		float colorR = (float) (colorMultiplier >> 16 & 255) / 255.0F;
		float colorG = (float) (colorMultiplier >> 8 & 255) / 255.0F;
		float colorB = (float) (colorMultiplier & 255) / 255.0F;
		
		if (EntityRenderer.anaglyphEnable) {
			float colorRCnv = (colorR * 30.0F + colorG * 59.0F + colorB * 11.0F) / 100.0F;
			float colorGCnv = (colorR * 30.0F + colorG * 70.0F) / 100.0F;
			float colorBCnv = (colorR * 30.0F + colorB * 70.0F) / 100.0F;
			colorR = colorRCnv;
			colorG = colorGCnv;
			colorB = colorBCnv;
		}
		
		renderer.enableAO = true;
		float f3 = 0.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		boolean flag1 = true;
		int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(983055);
		
		if (renderer.getBlockIcon(block).getIconName().equals("grass_top")) {
			flag1 = false;
		} else if (renderer.hasOverrideBlockTexture()) {
			flag1 = false;
		}
		
		boolean flag2;
		boolean flag3;
		boolean flag4;
		boolean flag5;
		int i1;
		float f7;
		
		BlockPos pos = new BlockPos(x, y, z);
		IBlockWithYLBlockModel blockWithModel = (IBlockWithYLBlockModel)block;
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_BOTTOM), pos, renderer.blockAccess)) {
			if (element.getFromY() <= 0) {
				--y;
			}
			
			renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			renderer.aoLightValueScratchXYNN = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPN = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			flag2 = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();
			
			if (!flag5 && !flag3) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
			}
			
			if (!flag4 && !flag3) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
			}
			
			if (!flag5 && !flag2) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
			}
			
			if (!flag4 && !flag2) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
			}
			
			if (element.getFromY() <= 0) {
				++y;
			}
			
			i1 = l;
			
			if (element.getFromY() <= 0 || !renderer.blockAccess.getBlock(x, y - 1, z).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			}
			
			f7 = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			f3 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
			f6 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
			f5 = (f7 + renderer.aoLightValueScratchYZNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
			f4 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNN + f7 + renderer.aoLightValueScratchYZNN) / 4.0F;
			renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN, renderer.aoBrightnessYZNP, i1);
			renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXYPN, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNN, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN, renderer.aoBrightnessYZNN, i1);
			
			if (flag1) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.5F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.5F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.5F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			this.renderFaceBottom(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_BOTTOM), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_TOP), pos, renderer.blockAccess)) {
			if ((element.getToY()/16f) >= 1.0D) {
				++y;
			}
			
			renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			flag2 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
			
			if (!flag5 && !flag3) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
			}
			
			if (!flag5 && !flag2) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
			}
			
			if (!flag4 && !flag3) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
			}
			
			if (!flag4 && !flag2) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
			}
			
			if ((element.getToY()/16f) >= 1.0D) {
				--y;
			}
			
			i1 = l;
			
			if ((element.getToY()/16f) >= 1.0D || !renderer.blockAccess.getBlock(x, y + 1, z).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			}
			
			f7 = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
			f3 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
			f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
			f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
			renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
			renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
			renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
			renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB;
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			this.renderFaceTop(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_TOP), renderer);
		}
		
		float f8;
		float f9;
		float f10;
		float f11;
		int j1;
		int k1;
		int l1;
		int i2;
		IIcon iicon;
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_NORTH), pos, renderer.blockAccess)) {
			if (element.getFromZ() <= 0) {
				--z;
			}
			
			renderer.aoLightValueScratchXZNN = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPN = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			flag2 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();
			
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
			}
			
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
			}
			
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
			}
			
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
			}
			
			if (element.getFromZ() <= 0) {
				++z;
			}
			
			i1 = l;
			
			if (element.getFromZ() <= 0 || !renderer.blockAccess.getBlock(x, y, z - 1).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			}
			
			f7 = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			f8 = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
			f9 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
			f10 = (renderer.aoLightValueScratchYZNN + f7 + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
			f11 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchYZNN + f7) / 4.0F;
			f3 = (float) ((double) f8 * (element.getToY()/16f) * (1.0D - (element.getFromX()/16f)) + (double) f9 * (element.getToY()/16f) * (element.getFromX()/16f) + (double) f10 * (1.0D - (element.getToY()/16f)) * (element.getFromX()/16f) + (double) f11 * (1.0D - (element.getToY()/16f)) * (1.0D - (element.getFromX()/16f)));
			f4 = (float) ((double) f8 * (element.getToY()/16f) * (1.0D - (element.getToX()/16f)) + (double) f9 * (element.getToY()/16f) * (element.getToX()/16f) + (double) f10 * (1.0D - (element.getToY()/16f)) * (element.getToX()/16f) + (double) f11 * (1.0D - (element.getToY()/16f)) * (1.0D - (element.getToX()/16f)));
			f5 = (float) ((double) f8 * (element.getFromY()/16f) * (1.0D - (element.getToX()/16f)) + (double) f9 * (element.getFromY()/16f) * (element.getToX()/16f) + (double) f10 * (1.0D - (element.getFromY()/16f)) * (element.getToX()/16f) + (double) f11 * (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getToX()/16f)));
			f6 = (float) ((double) f8 * (element.getFromY()/16f) * (1.0D - (element.getFromX()/16f)) + (double) f9 * (element.getFromY()/16f) * (element.getFromX()/16f) + (double) f10 * (1.0D - (element.getFromY()/16f)) * (element.getFromX()/16f) + (double) f11 * (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getFromX()/16f)));
			j1 = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
			k1 = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, i1);
			l1 = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, i1);
			i2 = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, i1);
			renderer.brightnessTopLeft = renderer.mixAoBrightness(j1, k1, l1, i2, (element.getToY()/16f) * (1.0D - (element.getFromX()/16f)), (element.getToY()/16f) * (element.getFromX()/16f), (1.0D - (element.getToY()/16f)) * (element.getFromX()/16f), (1.0D - (element.getToY()/16f)) * (1.0D - (element.getFromX()/16f)));
			renderer.brightnessBottomLeft = renderer.mixAoBrightness(j1, k1, l1, i2, (element.getToY()/16f) * (1.0D - (element.getToX()/16f)), (element.getToY()/16f) * (element.getToX()/16f), (1.0D - (element.getToY()/16f)) * (element.getToX()/16f), (1.0D - (element.getToY()/16f)) * (1.0D - (element.getToX()/16f)));
			renderer.brightnessBottomRight = renderer.mixAoBrightness(j1, k1, l1, i2, (element.getFromY()/16f) * (1.0D - (element.getToX()/16f)), (element.getFromY()/16f) * (element.getToX()/16f), (1.0D - (element.getFromY()/16f)) * (element.getToX()/16f), (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getToX()/16f)));
			renderer.brightnessTopRight = renderer.mixAoBrightness(j1, k1, l1, i2, (element.getFromY()/16f) * (1.0D - (element.getFromX()/16f)), (element.getFromY()/16f) * (element.getFromX()/16f), (1.0D - (element.getFromY()/16f)) * (element.getFromX()/16f), (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getFromX()/16f)));
			
			if (flag1) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.8F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.8F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.8F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			iicon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 2);
			this.renderFaceNorth(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_NORTH), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_SOUTH), pos, renderer.blockAccess)) {
			if (element.getToZ() >= 16) {
				++z;
			}
			
			renderer.aoLightValueScratchXZNP = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPP = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			flag2 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
			
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
			}
			
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
			}
			
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
			}
			
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
			}
			
			if (element.getToZ() >= 16) {
				--z;
			}
			
			i1 = l;
			
			if (element.getToZ() >= 16 || !renderer.blockAccess.getBlock(x, y, z + 1).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			}
			
			f7 = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			f8 = (renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
			f9 = (f7 + renderer.aoLightValueScratchYZPP + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
			f10 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
			f11 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
			f3 = (float) ((double) f8 * (element.getToY()/16f) * (1.0D - (element.getFromX()/16f)) + (double) f9 * (element.getToY()/16f) * (element.getFromX()/16f) + (double) f10 * (1.0D - (element.getToY()/16f)) * (element.getFromX()/16f) + (double) f11 * (1.0D - (element.getToY()/16f)) * (1.0D - (element.getFromX()/16f)));
			f4 = (float) ((double) f8 * (element.getFromY()/16f) * (1.0D - (element.getFromX()/16f)) + (double) f9 * (element.getFromY()/16f) * (element.getFromX()/16f) + (double) f10 * (1.0D - (element.getFromY()/16f)) * (element.getFromX()/16f) + (double) f11 * (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getFromX()/16f)));
			f5 = (float) ((double) f8 * (element.getFromY()/16f) * (1.0D - (element.getToX()/16f)) + (double) f9 * (element.getFromY()/16f) * (element.getToX()/16f) + (double) f10 * (1.0D - (element.getFromY()/16f)) * (element.getToX()/16f) + (double) f11 * (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getToX()/16f)));
			f6 = (float) ((double) f8 * (element.getToY()/16f) * (1.0D - (element.getToX()/16f)) + (double) f9 * (element.getToY()/16f) * (element.getToX()/16f) + (double) f10 * (1.0D - (element.getToY()/16f)) * (element.getToX()/16f) + (double) f11 * (1.0D - (element.getToY()/16f)) * (1.0D - (element.getToX()/16f)));
			j1 = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, i1);
			k1 = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, i1);
			l1 = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
			i2 = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, i1);
			renderer.brightnessTopLeft = renderer.mixAoBrightness(j1, i2, l1, k1, (element.getToY()/16f) * (1.0D - (element.getFromX()/16f)), (1.0D - (element.getToY()/16f)) * (1.0D - (element.getFromX()/16f)), (1.0D - (element.getToY()/16f)) * (element.getFromX()/16f), (element.getToY()/16f) * (element.getFromX()/16f));
			renderer.brightnessBottomLeft = renderer.mixAoBrightness(j1, i2, l1, k1, (element.getFromY()/16f) * (1.0D - (element.getFromX()/16f)), (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getFromX()/16f)), (1.0D - (element.getFromY()/16f)) * (element.getFromX()/16f), (element.getFromY()/16f) * (element.getFromX()/16f));
			renderer.brightnessBottomRight = renderer.mixAoBrightness(j1, i2, l1, k1, (element.getFromY()/16f) * (1.0D - (element.getToX()/16f)), (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getToX()/16f)), (1.0D - (element.getFromY()/16f)) * (element.getToX()/16f), (element.getFromY()/16f) * (element.getToX()/16f));
			renderer.brightnessTopRight = renderer.mixAoBrightness(j1, i2, l1, k1, (element.getToY()/16f) * (1.0D - (element.getToX()/16f)), (1.0D - (element.getToY()/16f)) * (1.0D - (element.getToX()/16f)), (1.0D - (element.getToY()/16f)) * (element.getToX()/16f), (element.getToY()/16f) * (element.getToX()/16f));
			
			if (flag1) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.8F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.8F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.8F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			iicon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 3);
			this.renderFaceSouth(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_SOUTH), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_WEST), pos, renderer.blockAccess)) {
			if (element.getFromX() <= 0) {
				--x;
			}
			
			renderer.aoLightValueScratchXYNN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZNN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZNP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			flag2 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
			
			if (!flag4 && !flag3) {
				renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
			}
			
			if (!flag5 && !flag3) {
				renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
			}
			
			if (!flag4 && !flag2) {
				renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
				renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
			} else {
				renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
			}
			
			if (!flag5 && !flag2) {
				renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
				renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
			} else {
				renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
			}
			
			if (element.getFromX() <= 0) {
				++x;
			}
			
			i1 = l;
			
			if (element.getFromX() <= 0 || !renderer.blockAccess.getBlock(x - 1, y, z).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
			}
			
			f7 = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
			f8 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
			f9 = (f7 + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
			f10 = (renderer.aoLightValueScratchXZNN + f7 + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
			f11 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXZNN + f7) / 4.0F;
			f3 = (float) ((double) f9 * (element.getToY()/16f) * (element.getToZ()/16f) + (double) f10 * (element.getToY()/16f) * (1.0D - (element.getToZ()/16f)) + (double) f11 * (1.0D - (element.getToY()/16f)) * (1.0D - (element.getToZ()/16f)) + (double) f8 * (1.0D - (element.getToY()/16f)) * (element.getToZ()/16f));
			f4 = (float) ((double) f9 * (element.getToY()/16f) * (element.getFromZ()/16f) + (double) f10 * (element.getToY()/16f) * (1.0D - (element.getFromZ()/16f)) + (double) f11 * (1.0D - (element.getToY()/16f)) * (1.0D - (element.getFromZ()/16f)) + (double) f8 * (1.0D - (element.getToY()/16f)) * (element.getFromZ()/16f));
			f5 = (float) ((double) f9 * (element.getFromY()/16f) * (element.getFromZ()/16f) + (double) f10 * (element.getFromY()/16f) * (1.0D - (element.getFromZ()/16f)) + (double) f11 * (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getFromZ()/16f)) + (double) f8 * (1.0D - (element.getFromY()/16f)) * (element.getFromZ()/16f));
			f6 = (float) ((double) f9 * (element.getFromY()/16f) * (element.getToZ()/16f) + (double) f10 * (element.getFromY()/16f) * (1.0D - (element.getToZ()/16f)) + (double) f11 * (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getToZ()/16f)) + (double) f8 * (1.0D - (element.getFromY()/16f)) * (element.getToZ()/16f));
			j1 = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, i1);
			k1 = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, i1);
			l1 = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, i1);
			i2 = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, i1);
			renderer.brightnessTopLeft = renderer.mixAoBrightness(k1, l1, i2, j1, (element.getToY()/16f) * (element.getToZ()/16f), (element.getToY()/16f) * (1.0D - (element.getToZ()/16f)), (1.0D - (element.getToY()/16f)) * (1.0D - (element.getToZ()/16f)), (1.0D - (element.getToY()/16f)) * (element.getToZ()/16f));
			renderer.brightnessBottomLeft = renderer.mixAoBrightness(k1, l1, i2, j1, (element.getToY()/16f) * (element.getFromZ()/16f), (element.getToY()/16f) * (1.0D - (element.getFromZ()/16f)), (1.0D - (element.getToY()/16f)) * (1.0D - (element.getFromZ()/16f)), (1.0D - (element.getToY()/16f)) * (element.getFromZ()/16f));
			renderer.brightnessBottomRight = renderer.mixAoBrightness(k1, l1, i2, j1, (element.getFromY()/16f) * (element.getFromZ()/16f), (element.getFromY()/16f) * (1.0D - (element.getFromZ()/16f)), (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getFromZ()/16f)), (1.0D - (element.getFromY()/16f)) * (element.getFromZ()/16f));
			renderer.brightnessTopRight = renderer.mixAoBrightness(k1, l1, i2, j1, (element.getFromY()/16f) * (element.getToZ()/16f), (element.getFromY()/16f) * (1.0D - (element.getToZ()/16f)), (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getToZ()/16f)), (1.0D - (element.getFromY()/16f)) * (element.getToZ()/16f));
			
			if (flag1) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.6F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.6F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.6F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			iicon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 4);
			this.renderFaceWest(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_WEST), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_EAST), pos, renderer.blockAccess)) {
			if (element.getToX() >= 16) {
				++x;
			}
			
			renderer.aoLightValueScratchXYPN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXZPP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
			renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
			renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
			renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
			renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
			renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
			flag2 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
			flag3 = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
			flag4 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
			flag5 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
			
			if (!flag3 && !flag5) {
				renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
			}
			
			if (!flag3 && !flag4) {
				renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
			}
			
			if (!flag2 && !flag5) {
				renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
				renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
			} else {
				renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
			}
			
			if (!flag2 && !flag4) {
				renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
				renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
			} else {
				renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
				renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
			}
			
			if (element.getToX() >= 16) {
				--x;
			}
			
			i1 = l;
			
			if (element.getToX() >= 16 || !renderer.blockAccess.getBlock(x + 1, y, z).isOpaqueCube()) {
				i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
			}
			
			f7 = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
			f8 = (renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
			f9 = (renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXZPN + f7) / 4.0F;
			f10 = (renderer.aoLightValueScratchXZPN + f7 + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
			f11 = (f7 + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
			f3 = (float) ((double) f8 * (1.0D - (element.getFromY()/16f)) * (element.getToZ()/16f) + (double) f9 * (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getToZ()/16f)) + (double) f10 * (element.getFromY()/16f) * (1.0D - (element.getToZ()/16f)) + (double) f11 * (element.getFromY()/16f) * (element.getToZ()/16f));
			f4 = (float) ((double) f8 * (1.0D - (element.getFromY()/16f)) * (element.getFromZ()/16f) + (double) f9 * (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getFromZ()/16f)) + (double) f10 * (element.getFromY()/16f) * (1.0D - (element.getFromZ()/16f)) + (double) f11 * (element.getFromY()/16f) * (element.getFromZ()/16f));
			f5 = (float) ((double) f8 * (1.0D - (element.getToY()/16f)) * (element.getFromZ()/16f) + (double) f9 * (1.0D - (element.getToY()/16f)) * (1.0D - (element.getFromZ()/16f)) + (double) f10 * (element.getToY()/16f) * (1.0D - (element.getFromZ()/16f)) + (double) f11 * (element.getToY()/16f) * (element.getFromZ()/16f));
			f6 = (float) ((double) f8 * (1.0D - (element.getToY()/16f)) * (element.getToZ()/16f) + (double) f9 * (1.0D - (element.getToY()/16f)) * (1.0D - (element.getToZ()/16f)) + (double) f10 * (element.getToY()/16f) * (1.0D - (element.getToZ()/16f)) + (double) f11 * (element.getToY()/16f) * (element.getToZ()/16f));
			j1 = renderer.getAoBrightness(renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
			k1 = renderer.getAoBrightness(renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, i1);
			l1 = renderer.getAoBrightness(renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, i1);
			i2 = renderer.getAoBrightness(renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, i1);
			renderer.brightnessTopLeft = renderer.mixAoBrightness(j1, i2, l1, k1, (1.0D - (element.getFromY()/16f)) * (element.getToZ()/16f), (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getToZ()/16f)), (element.getFromY()/16f) * (1.0D - (element.getToZ()/16f)), (element.getFromY()/16f) * (element.getToZ()/16f));
			renderer.brightnessBottomLeft = renderer.mixAoBrightness(j1, i2, l1, k1, (1.0D - (element.getFromY()/16f)) * (element.getFromZ()/16f), (1.0D - (element.getFromY()/16f)) * (1.0D - (element.getFromZ()/16f)), (element.getFromY()/16f) * (1.0D - (element.getFromZ()/16f)), (element.getFromY()/16f) * (element.getFromZ()/16f));
			renderer.brightnessBottomRight = renderer.mixAoBrightness(j1, i2, l1, k1, (1.0D - (element.getToY()/16f)) * (element.getFromZ()/16f), (1.0D - (element.getToY()/16f)) * (1.0D - (element.getFromZ()/16f)), (element.getToY()/16f) * (1.0D - (element.getFromZ()/16f)), (element.getToY()/16f) * (element.getFromZ()/16f));
			renderer.brightnessTopRight = renderer.mixAoBrightness(j1, i2, l1, k1, (1.0D - (element.getToY()/16f)) * (element.getToZ()/16f), (1.0D - (element.getToY()/16f)) * (1.0D - (element.getToZ()/16f)), (element.getToY()/16f) * (1.0D - (element.getToZ()/16f)), (element.getToY()/16f) * (element.getToZ()/16f));
			
			if (flag1) {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.6F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.6F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.6F;
			} else {
				renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
				renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
				renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
			}
			
			renderer.colorRedTopLeft *= f3;
			renderer.colorGreenTopLeft *= f3;
			renderer.colorBlueTopLeft *= f3;
			renderer.colorRedBottomLeft *= f4;
			renderer.colorGreenBottomLeft *= f4;
			renderer.colorBlueBottomLeft *= f4;
			renderer.colorRedBottomRight *= f5;
			renderer.colorGreenBottomRight *= f5;
			renderer.colorBlueBottomRight *= f5;
			renderer.colorRedTopRight *= f6;
			renderer.colorGreenTopRight *= f6;
			renderer.colorBlueTopRight *= f6;
			iicon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 5);
			this.renderFaceEast(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_EAST), renderer);
		}
		
		renderer.enableAO = false;
	}
	
	private void renderFacesWithColorMultiplier(Block block, int x, int y, int z, YLBlockModel.Element element, RenderBlocks renderer) {
		int colorMultiplier = block.colorMultiplier(renderer.blockAccess, x, y, z);
		float colorR = (float) (colorMultiplier >> 16 & 255) / 255.0F;
		float colorG = (float) (colorMultiplier >> 8 & 255) / 255.0F;
		float colorB = (float) (colorMultiplier & 255) / 255.0F;
		
		if (EntityRenderer.anaglyphEnable) {
			float colorRCnv = (colorR * 30.0F + colorG * 59.0F + colorB * 11.0F) / 100.0F;
			float colorGCnv = (colorR * 30.0F + colorG * 70.0F) / 100.0F;
			float colorBCnv = (colorR * 30.0F + colorB * 70.0F) / 100.0F;
			colorR = colorRCnv;
			colorG = colorGCnv;
			colorB = colorBCnv;
		}
		
		renderer.enableAO = false;
		Tessellator tessellator = Tessellator.instance;
		float multBottom = 0.5F;
		float multTop = 1.0F;
		float multNS = 0.8F;
		float multWE = 0.6F;
		float topR = multTop * colorR;
		float topG = multTop * colorG;
		float topB = multTop * colorB;
		float bottomR = multBottom * colorR;
		float bottomG = multBottom * colorG;
		float bottomB = multBottom * colorB;
		float nsR = multNS * colorR;
		float nsG = multNS * colorG;
		float nsB = multNS * colorB;
		float weR = multWE * colorR;
		float weG = multWE * colorG;
		float weB = multWE * colorB;
		
		BlockPos pos = new BlockPos(x, y, z);
		IBlockWithYLBlockModel blockWithModel = (IBlockWithYLBlockModel)block;
		
		int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_BOTTOM), pos, renderer.blockAccess)) {
			tessellator.setBrightness(element.getFromY() > 0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z));
			tessellator.setColorOpaque_F(bottomR, bottomG, bottomB);
			this.renderFaceBottom(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_BOTTOM), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_TOP), pos, renderer.blockAccess)) {
			tessellator.setBrightness(element.getToY() < 16 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z));
			tessellator.setColorOpaque_F(topR, topG, topB);
			this.renderFaceTop(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_TOP), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_NORTH), pos, renderer.blockAccess)) {
			tessellator.setBrightness(element.getFromZ() > 0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1));
			tessellator.setColorOpaque_F(nsR, nsG, nsB);
			this.renderFaceNorth(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_NORTH), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_SOUTH), pos, renderer.blockAccess)) {
			tessellator.setBrightness(element.getToZ() < 16 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1));
			tessellator.setColorOpaque_F(nsR, nsG, nsB);
			this.renderFaceSouth(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_SOUTH), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_WEST), pos, renderer.blockAccess)) {
			tessellator.setBrightness(element.getFromX() > 0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z));
			tessellator.setColorOpaque_F(weR, weG, weB);
			this.renderFaceWest(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_WEST), renderer);
		}
		
		if (this.shouldRenderFace(element.getFace(McConst.SIDE_EAST), pos, renderer.blockAccess)) {
			tessellator.setBrightness(element.getToX() < 16 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z));
			tessellator.setColorOpaque_F(weR, weG, weB);
			this.renderFaceEast(blockWithModel, x, y, z, element, element.getFace(McConst.SIDE_EAST), renderer);
		}
	}
	
	
	private boolean shouldRenderFace(YLBlockModel.Face face, BlockPos pos, IBlockAccess world){
		if(face == null) return false;
		if(face.getCullFace() == ForgeDirection.UNKNOWN) return true;
		return !WorldUtils.getBlock(world, pos.offset(face.getCullFace())).isOpaqueCube();
	}
	
	// -Y
	private void renderFaceBottom(IBlockWithYLBlockModel block, int x, int y, int z, YLBlockModel.Element element, YLBlockModel.Face face, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		IIcon icon = block.getFaceTexture(face.getTextureName());
		
		double uMin = icon.getInterpolatedU(face.getUMin());
		double uMax = icon.getInterpolatedU(face.getUMax());
		double vMin = icon.getInterpolatedV(face.getVMin());
		double vMax = icon.getInterpolatedV(face.getVMax());
		
		double xFrom = x + element.getFromX() / 16f;
		double xTo = x + element.getToX() / 16f;
		double yFrom = y + element.getFromY() / 16f;
		double zFrom = z + element.getFromZ() / 16f;
		double zTo = z + element.getToZ() / 16f;
		
		if (renderer.enableAO) {
			tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(xFrom, yFrom, zTo, uMin, vMax);
			tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(xFrom, yFrom, zFrom, uMin, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(xTo, yFrom, zFrom, uMax, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(xTo, yFrom, zTo, uMax, vMax);
		} else {
			tessellator.addVertexWithUV(xFrom, yFrom, zTo, uMin, vMax);
			tessellator.addVertexWithUV(xFrom, yFrom, zFrom, uMin, vMin);
			tessellator.addVertexWithUV(xTo, yFrom, zFrom, uMax, vMin);
			tessellator.addVertexWithUV(xTo, yFrom, zTo, uMax, vMax);
		}
	}
	
	// +Y
	public void renderFaceTop(IBlockWithYLBlockModel block, int x, int y, int z, YLBlockModel.Element element, YLBlockModel.Face face, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		IIcon icon = block.getFaceTexture(face.getTextureName());
		
		double uMin = icon.getInterpolatedU(face.getUMin());
		double uMax = icon.getInterpolatedU(face.getUMax());
		double vMin = icon.getInterpolatedV(face.getVMin());
		double vMax = icon.getInterpolatedV(face.getVMax());
		
		double xFrom = x + element.getFromX() / 16f;
		double xTo = x + element.getToX() / 16f;
		double yTo = y + element.getToY() / 16f;
		double zFrom = z + element.getFromZ() / 16f;
		double zTo = z + element.getToZ() / 16f;
		
		if (renderer.enableAO) {
			tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(xTo, yTo, zTo, uMax, vMax);
			tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(xTo, yTo, zFrom, uMax, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(xFrom, yTo, zFrom, uMin, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(xFrom, yTo, zTo, uMin, vMax);
		} else {
			tessellator.addVertexWithUV(xTo, yTo, zTo, uMax, vMax);
			tessellator.addVertexWithUV(xTo, yTo, zFrom, uMax, vMin);
			tessellator.addVertexWithUV(xFrom, yTo, zFrom, uMin, vMin);
			tessellator.addVertexWithUV(xFrom, yTo, zTo, uMin, vMax);
		}
	}
	
	// -Z
	private void renderFaceNorth(IBlockWithYLBlockModel block, int x, int y, int z, YLBlockModel.Element element, YLBlockModel.Face face, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		IIcon icon = block.getFaceTexture(face.getTextureName());
		
		double uMin = icon.getInterpolatedU(face.getUMin());
		double uMax = icon.getInterpolatedU(face.getUMax());
		double vMin = icon.getInterpolatedV(face.getVMin());
		double vMax = icon.getInterpolatedV(face.getVMax());
		
		double xFrom = x + element.getFromX() / 16f;
		double xTo = x + element.getToX() / 16f;
		double yFrom = y + element.getFromY() / 16f;
		double yTo = y + element.getToY() / 16f;
		double zFrom = z + element.getFromZ() / 16f;
		
		if (renderer.enableAO) {
			tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(xFrom, yTo, zFrom, uMax, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(xTo, yTo, zFrom, uMin, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(xTo, yFrom, zFrom, uMin, vMax);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(xFrom, yFrom, zFrom, uMax, vMax);
		} else {
			tessellator.addVertexWithUV(xFrom, yTo, zFrom, uMax, vMin);
			tessellator.addVertexWithUV(xTo, yTo, zFrom, uMin, vMin);
			tessellator.addVertexWithUV(xTo, yFrom, zFrom, uMin, vMax);
			tessellator.addVertexWithUV(xFrom, yFrom, zFrom, uMax, vMax);
		}
	}
	
	// +Z
	public void renderFaceSouth(IBlockWithYLBlockModel block, int x, int y, int z, YLBlockModel.Element element, YLBlockModel.Face face, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		IIcon icon = block.getFaceTexture(face.getTextureName());
		
		double uMin = icon.getInterpolatedU(face.getUMin());
		double uMax = icon.getInterpolatedU(face.getUMax());
		double vMin = icon.getInterpolatedV(face.getVMin());
		double vMax = icon.getInterpolatedV(face.getVMax());
		
		double xFrom = x + element.getFromX() / 16f;
		double xTo = x + element.getToX() / 16f;
		double yFrom = y + element.getFromY() / 16f;
		double yTo = y + element.getToY() / 16f;
		double zTo = z + element.getToZ() / 16f;
		
		if (renderer.enableAO) {
			tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(xFrom, yTo, zTo, uMin, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(xFrom, yFrom, zTo, uMin, vMax);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(xTo, yFrom, zTo, uMax, vMax);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(xTo, yTo, zTo, uMax, vMin);
		} else {
			tessellator.addVertexWithUV(xFrom, yTo, zTo, uMin, vMin);
			tessellator.addVertexWithUV(xFrom, yFrom, zTo, uMin, vMax);
			tessellator.addVertexWithUV(xTo, yFrom, zTo, uMax, vMax);
			tessellator.addVertexWithUV(xTo, yTo, zTo, uMax, vMin);
		}
	}
	
	// -X
	private void renderFaceWest(IBlockWithYLBlockModel block, int x, int y, int z, YLBlockModel.Element element, YLBlockModel.Face face, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		IIcon icon = block.getFaceTexture(face.getTextureName());
		
		double uMin = icon.getInterpolatedU(face.getUMin());
		double uMax = icon.getInterpolatedU(face.getUMax());
		double vMin = icon.getInterpolatedV(face.getVMin());
		double vMax = icon.getInterpolatedV(face.getVMax());
		
		double xFrom = x + element.getFromX() / 16f;
		double yFrom = y + element.getFromY() / 16f;
		double yTo = y + element.getToY() / 16f;
		double zFrom = z + element.getFromZ() / 16f;
		double zTo = z + element.getToZ() / 16f;
		
		if (renderer.enableAO) {
			tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(xFrom, yTo, zTo, uMax, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(xFrom, yTo, zFrom, uMin, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(xFrom, yFrom, zFrom, uMin, vMax);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(xFrom, yFrom, zTo, uMax, vMax);
		} else {
			tessellator.addVertexWithUV(xFrom, yTo, zTo, uMax, vMin);
			tessellator.addVertexWithUV(xFrom, yTo, zFrom, uMin, vMin);
			tessellator.addVertexWithUV(xFrom, yFrom, zFrom, uMin, vMax);
			tessellator.addVertexWithUV(xFrom, yFrom, zTo, uMax, vMax);
		}
	}
	
	// +X
	public void renderFaceEast(IBlockWithYLBlockModel block, int x, int y, int z, YLBlockModel.Element element, YLBlockModel.Face face, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		IIcon icon = block.getFaceTexture(face.getTextureName());
		
		double uMin = icon.getInterpolatedU(face.getUMin());
		double uMax = icon.getInterpolatedU(face.getUMax());
		double vMin = icon.getInterpolatedV(face.getVMin());
		double vMax = icon.getInterpolatedV(face.getVMax());
		
		double xTo = x + element.getToX() / 16f;
		double yFrom = y + element.getFromY() / 16f;
		double yTo = y + element.getToY() / 16f;
		double zFrom = z + element.getFromZ() / 16f;
		double zTo = z + element.getToZ() / 16f;
		
		if (renderer.enableAO) {
			tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
			tessellator.setBrightness(renderer.brightnessTopLeft);
			tessellator.addVertexWithUV(xTo, yFrom, zTo, uMin, vMax);
			tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
			tessellator.setBrightness(renderer.brightnessBottomLeft);
			tessellator.addVertexWithUV(xTo, yFrom, zFrom, uMax, vMax);
			tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
			tessellator.setBrightness(renderer.brightnessBottomRight);
			tessellator.addVertexWithUV(xTo, yTo, zFrom, uMax, vMin);
			tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
			tessellator.setBrightness(renderer.brightnessTopRight);
			tessellator.addVertexWithUV(xTo, yTo, zTo, uMin, vMin);
		} else {
			tessellator.addVertexWithUV(xTo, yFrom, zTo, uMin, vMax);
			tessellator.addVertexWithUV(xTo, yFrom, zFrom, uMax, vMax);
			tessellator.addVertexWithUV(xTo, yTo, zFrom, uMax, vMin);
			tessellator.addVertexWithUV(xTo, yTo, zTo, uMin, vMin);
		}
	}
	
	@Override public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}
	
	@Override public int getRenderId() {
		return YLBlockModel.renderId();
	}
}
