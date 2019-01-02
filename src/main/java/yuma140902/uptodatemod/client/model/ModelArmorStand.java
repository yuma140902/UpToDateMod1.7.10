package yuma140902.uptodatemod.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArmorStand extends ModelBiped {
	
	public ModelRenderer bipedHead;
  public ModelRenderer bipedRightArm;
  public ModelRenderer bipedLeftLeg;
  public ModelRenderer bipedBody;
  public ModelRenderer bipedLeftArm;
  public ModelRenderer bipedRightLeg;
  public ModelRenderer standBase;
  public ModelRenderer standWaist;
  public ModelRenderer standRightSide;
  public ModelRenderer standLeftSide;
  public ModelRenderer shape14;
	
	public ModelArmorStand() {
		this.textureWidth = 64;
    this.textureHeight = 64;
    
    this.bipedHead = new ModelRenderer(this, 0, 0);
    this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.bipedHead.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2, 0.5F);
    
    this.bipedBody = new ModelRenderer(this, 0, 26);
    this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.bipedBody.addBox(-6.0F, 0.0F, -1.5F, 12, 3, 3, 0.0F);
    
    this.bipedRightArm = new ModelRenderer(this, 24, 0);
    this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
    this.bipedRightArm.addBox(-2.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
    
    this.bipedLeftArm = new ModelRenderer(this, 32, 16);
    this.bipedLeftArm.mirror = true;
    this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
    this.bipedLeftArm.addBox(0.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
    
    this.bipedRightLeg = new ModelRenderer(this, 8, 0);
    this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
    this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);

    
    this.bipedLeftLeg = new ModelRenderer(this, 40, 16);
    this.bipedLeftLeg.mirror = true;
    this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
    this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
    
    this.standRightSide = new ModelRenderer(this, 16, 0);
    this.standRightSide.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.standRightSide.addBox(-3.0F, 3.0F, -1.0F, 2, 7, 2, 0.0F);
    
    this.standLeftSide = new ModelRenderer(this, 48, 16);
    this.standLeftSide.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.standLeftSide.addBox(1.0F, 3.0F, -1.0F, 2, 7, 2, 0.0F);
    
    this.standWaist = new ModelRenderer(this, 0, 48);
    this.standWaist.setRotationPoint(0.0F, 12.0F, 0.0F);
    this.standWaist.addBox(-4.0F, 10.0F, -1.0F, 8, 2, 2, 0.0F);
    
    this.standBase = new ModelRenderer(this, 0, 32);
    this.standBase.setRotationPoint(0.0F, 12.0F, 0.0F);
    this.standBase.addBox(-6.0F, 11.0F, -6.0F, 12, 1, 12, 0.0F);
    
    this.shape14 = new ModelRenderer(this, 0, 16);
    this.shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.shape14.addBox(-4.0F, 10.0F, -1.5F, 8, 2, 3, 0.0F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.bipedHead.render(f5);
		this.bipedBody.render(f5);
		this.bipedRightArm.render(f5);
		this.bipedLeftArm.render(f5);
    this.bipedRightLeg.render(f5);
    this.bipedLeftLeg.render(f5);
    this.standRightSide.render(f5);
    this.standLeftSide.render(f5);
    this.standWaist.render(f5);
    this.standBase.render(f5);
    this.shape14.render(f5);
	}
	
	/**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}
