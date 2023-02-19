package yuma140902.yumalib.api.client.model;

import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class YLBlockModelRayTracer {
	/*private static void traceSide(int side, Vec3 start, Vec3 end, Cuboid6 cuboid)
	{
		Vec3 hit = null;
		switch(side)
		{
			case 0:
				hit = start.XZintercept(end, cuboid.min.y);
				break;
			case 1:
				hit = start.XZintercept(end, cuboid.max.y);
				break;
			case 2:
				hit = start.XYintercept(end, cuboid.min.z);
				break;
			case 3:
				hit = start.XYintercept(end, cuboid.max.z);
				break;
			case 4:
				hit = start.YZintercept(end, cuboid.min.x);
				break;
			case 5:
				hit = start.YZintercept(end, cuboid.max.x);
				break;
		}
		if(hit == null)
			return;
		
		switch(side)
		{
			case 0:
			case 1:
				if(!MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x) || !MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z)) return;
				break;
			case 2:
			case 3:
				if(!MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x) || !MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y)) return;
				break;
			case 4:
			case 5:
				if(!MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y) || !MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z)) return;
				break;
		}
		
		double dist = vec2.set(hit).subtract(start).magSquared();
		if(dist < s_dist)
		{
			s_side = side;
			s_dist = dist;
			s_vec.set(vec);
		}
	}
	
	public MovingObjectPosition rayTraceCuboid(Vec3 start, Vec3 end, Cuboid6 cuboid)
	{
		s_dist = Double.MAX_VALUE;
		s_side = -1;
		
		for(int i = 0; i < 6; i++)
			traceSide(i, start, end, cuboid);
		
		if(s_side < 0)
			return null;
		
		MovingObjectPosition mop = new MovingObjectPosition(0, 0, 0, s_side, s_vec.toVec3D());
		mop.typeOfHit = null;
		return mop;
	}
	
	public MovingObjectPosition rayTraceCuboids(Vec3 start, Vec3 end, List<IndexedCuboid6> cuboids)
	{
		double c_dist = Double.MAX_VALUE;
		MovingObjectPosition c_hit = null;
		
		for(IndexedCuboid6 cuboid : cuboids)
		{
			MovingObjectPosition mop = rayTraceCuboid(start, end, cuboid);
			if(mop != null && s_dist < c_dist)
			{
				mop = new ExtendedMOP(mop, cuboid.data, s_dist);
				c_dist = s_dist;
				c_hit = mop;
				c_cuboid = cuboid;
			}
		}
		
		return c_hit;
	}
	
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
		//TODO
	}*/
}
