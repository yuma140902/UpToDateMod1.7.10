package yuma140902.uptodatemod.client.renderer;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.util.UpToDateModConstants;

public class RenderHusk extends RenderZombie {
	protected static final ResourceLocation zombieTextures = new ResourceLocation(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "textures/entity/zombie/husk.png");
	
	@Override
	protected ResourceLocation getEntityTexture(EntityZombie zombie) {
		return zombieTextures;
	}
}
