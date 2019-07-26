package yuma140902.uptodatemod.gui.util;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationPart implements IResourceLocationPart {

	private ResourceLocation resourceLocation;
	private int u, v, width, height;
	
	public ResourceLocationPart(ResourceLocation res, int u, int v, int width, int height) {
		this.resourceLocation = res;
		this.u = u;
		this.v = v;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int getU() {
		return u;
	}

	@Override
	public int getV() {
		return v;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return resourceLocation;
	}
	
}
