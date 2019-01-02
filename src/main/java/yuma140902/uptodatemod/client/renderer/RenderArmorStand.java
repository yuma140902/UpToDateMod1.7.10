package yuma140902.uptodatemod.client.renderer;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.client.model.ModelArmorStand;
import yuma140902.uptodatemod.entity.item.EntityArmorStand;

public class RenderArmorStand extends RendererLivingEntity {
	public RenderArmorStand() {
		super(new ModelArmorStand(), 0.5F);
	}

	private ResourceLocation textureArmorStand = new ResourceLocation(ModUpToDateMod.MOD_ID, "textures/entity/armor_stand.png");
	
	public void doRender(EntityArmorStand entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindTexture(textureArmorStand);
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
    
    double d3 = y - (double)entity.yOffset;

    if (entity.isSneaking())
    {
        d3 -= 0.125D;
    }

    super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.doRender((EntityArmorStand)entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	public void doRender(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.doRender((EntityArmorStand)entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return textureArmorStand;
	}
	
}
