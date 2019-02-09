package yuma140902.uptodatemod.client.renderer;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.entity.item.EntityFallingConcretePowderBlock;
import yuma140902.uptodatemod.util.ColorUtil;

@SideOnly(Side.CLIENT)
public class RenderFallingConcretePowder extends Render {
	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(0) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(1) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(2) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(3) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(4) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(5) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(6) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(7) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(8) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(9) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(10) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(11) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(12) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(13) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(14) + ".png"),
		new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/blocks/concrete_powder_" + ColorUtil.metaToString(15) + ".png"),
	};
	
	private final RenderBlocks renderBlocks = new RenderBlocks();
	
	public RenderFallingConcretePowder() {
		this.shadowSize = 0.5F;
	}
	
	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all
	 * probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void
	 * func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(
			EntityFallingConcretePowderBlock entity, double p_76986_2_, double p_76986_4_, double p_76986_6_,
			float p_76986_8_, float p_76986_9_) {
		World world = entity.func_145807_e();
		Block block = entity.func_145805_f();
		int i = MathHelper.floor_double(entity.posX);
		int j = MathHelper.floor_double(entity.posY);
		int k = MathHelper.floor_double(entity.posZ);
		
		if (block != null && block != world.getBlock(i, j, k)) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) p_76986_2_, (float) p_76986_4_, (float) p_76986_6_);
			this.bindEntityTexture(entity);
			GL11.glDisable(GL11.GL_LIGHTING);
			Tessellator tessellator;
			
			if (block instanceof BlockAnvil) {
				this.renderBlocks.blockAccess = world;
				tessellator = Tessellator.instance;
				tessellator.startDrawingQuads();
				tessellator.setTranslation(
						(double) ((float) (-i) - 0.5F), (double) ((float) (-j) - 0.5F), (double) ((float) (-k) - 0.5F));
				this.renderBlocks.renderBlockAnvilMetadata((BlockAnvil) block, i, j, k, entity.getMetadata());
				tessellator.setTranslation(0.0D, 0.0D, 0.0D);
				tessellator.draw();
			}
			else if (block instanceof BlockDragonEgg) {
				this.renderBlocks.blockAccess = world;
				tessellator = Tessellator.instance;
				tessellator.startDrawingQuads();
				tessellator.setTranslation(
						(double) ((float) (-i) - 0.5F), (double) ((float) (-j) - 0.5F), (double) ((float) (-k) - 0.5F));
				this.renderBlocks.renderBlockDragonEgg((BlockDragonEgg) block, i, j, k);
				tessellator.setTranslation(0.0D, 0.0D, 0.0D);
				tessellator.draw();
			}
			else {
				// this.field_147920_a.setRenderBoundsFromBlock(block);
				this.renderConcretePowderWithMetadata(block, world, i, j, k, entity.getMetadata());
			}
			
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
		}
	}
	
	// RenderBlocks.renderBlockSandFallingではメタデータ込のブロック描画ができないようなので自前のメソッドを用意
	// see: https://forum.minecraftuser.jp/viewtopic.php?f=21&t=4195#
	// RenderBlocks.renderBlockSandFallingをコピペして改変
	public void renderConcretePowderWithMetadata(
			Block block, World world, int p_147749_3_, int p_147749_4_, int p_147749_5_, int meta) {
		System.out.println("metadata : " + meta);
		this.renderBlocks.setRenderBoundsFromBlock(block);
		float f = 0.5F;
		float f1 = 1.0F;
		float f2 = 0.8F;
		float f3 = 0.6F;
		IIcon icon = renderBlocks.getBlockIconFromSideAndMetadata(block, 0, meta);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setBrightness(block.getMixedBrightnessForBlock(world, p_147749_3_, p_147749_4_, p_147749_5_));
		tessellator.setColorOpaque_F(f, f, f);
		renderBlocks.renderFaceYNeg(block, -0.5D, -0.5D, -0.5D, icon);
		tessellator.setColorOpaque_F(f1, f1, f1);
		renderBlocks.renderFaceYPos(block, -0.5D, -0.5D, -0.5D, icon);
		tessellator.setColorOpaque_F(f2, f2, f2);
		renderBlocks.renderFaceZNeg(block, -0.5D, -0.5D, -0.5D, icon);
		tessellator.setColorOpaque_F(f2, f2, f2);
		renderBlocks.renderFaceZPos(block, -0.5D, -0.5D, -0.5D, icon);
		tessellator.setColorOpaque_F(f3, f3, f3);
		renderBlocks.renderFaceXNeg(block, -0.5D, -0.5D, -0.5D, icon);
		tessellator.setColorOpaque_F(f3, f3, f3);
		renderBlocks.renderFaceXPos(block, -0.5D, -0.5D, -0.5D, icon);
		tessellator.draw();
	}
	
	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityFallingConcretePowderBlock entity) {
		// return TextureMap.locationBlocksTexture;
		System.out.println("getEntityTextre : " + ColorUtil.metaToString(entity.getMetadata()));
		return TEXTURES[entity.getMetadata()];
	}
	
	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityFallingConcretePowderBlock) entity);
	}
	
	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all
	 * probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void
	 * func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(
			Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender(
				(EntityFallingConcretePowderBlock) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
}
