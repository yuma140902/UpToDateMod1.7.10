package yuma140902.yumalib.api.math;

import net.minecraft.util.Vec3;

import java.util.Objects;

public class Vector3 {
	private double x;
	private double y;
	private double z;
	
	public Vector3(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vec3 mcVec3){
		this.x = mcVec3.xCoord;
		this.y = mcVec3.yCoord;
		this.z = mcVec3.zCoord;
	}
	
	public Vector3(double[] array){
		if(array.length != 3) throw new IllegalArgumentException("array length should be 3");
		this.x = array[0];
		this.y = array[1];
		this.z = array[2];
	}
	
	public Vector3 add(double x, double y, double z){
		return new Vector3(this.x + x, this.y + y, this.z + z);
	}
	
	public Vector3 addSelf(double x, double y, double z){
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public Vector3 newMultiplied16th(){
		return new Vector3(this.x/16, this.y/16, this.z/16);
	}
	
	public double x(){return this.x;}
	public double y(){return this.y;}
	public double z(){return this.z;}
	
	@Override public String toString() {
		return String.format("(%.2f %.2f %.2f)", x, y, z);
	}
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vector3)) return false;
		Vector3 vector3 = (Vector3) o;
		return Double.compare(vector3.x, x) == 0 &&
						Double.compare(vector3.y, y) == 0 &&
						Double.compare(vector3.z, z) == 0;
	}
	
	@Override public int hashCode() {
		return Objects.hash(x, y, z);
	}
}
