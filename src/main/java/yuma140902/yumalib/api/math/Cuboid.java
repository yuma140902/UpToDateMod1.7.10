package yuma140902.yumalib.api.math;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.AxisAlignedBB;

public class Cuboid {
	public final Vector3 from;
	public final Vector3 to;
	
	public Cuboid(Vector3 from, Vector3 to){
		this.from = from;
		this.to = to;
	}
	
	public void setRenderBounds(RenderBlocks renderer){
		Vector3 from16 = from.newMultiplied16th();
		Vector3 to16 = to.newMultiplied16th();
		renderer.setRenderBounds(from16.x(), from16.y(), from16.z(), to16.x(), to16.y(), to16.z());
	}
	
	public AxisAlignedBB toAABB(){
		return AxisAlignedBB.getBoundingBox(this.from.x(), this.from.y(), this.from.z(), this.to.x(), this.to.y(), this.to.z());
	}
}
