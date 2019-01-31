package yuma140902.uptodatemod.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;

@SideOnly(Side.CLIENT)
public class RenderModBoat extends RenderBoat {
	private static final ResourceLocation acaciaBoatTextures = new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/entity/boat_acacia.png");
	private static final ResourceLocation birchBoatTextures = new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/entity/boat_birch.png");
	private static final ResourceLocation darkOakBoatTextures = new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/entity/boat_dark_oak.png");
	private static final ResourceLocation jungleBoatTextures = new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/entity/boat_jungle.png");
	private static final ResourceLocation spruceBoatTextures = new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/entity/boat_spruce.png");
	
	private Type type;
	
	public RenderModBoat(Type type) {
		this.type = type;
		this.shadowSize = 0.5F;
		this.modelBoat = new ModelBoat();
	}
	
	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityBoat p_110775_1_) {
		switch(type) {
			case ACACIA:
				return acaciaBoatTextures;
			case BIRCH:
				return birchBoatTextures;
			case DARK_OAK:
				return darkOakBoatTextures;
			case JUNGLE:
				return jungleBoatTextures;
			case SPRUCE:
				return spruceBoatTextures;
			
			default:
				return acaciaBoatTextures;
		}
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
			Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		this.doRender((EntityModBoatBase) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
}
