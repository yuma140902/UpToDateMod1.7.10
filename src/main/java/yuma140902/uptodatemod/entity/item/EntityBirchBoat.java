package yuma140902.uptodatemod.entity.item;

import net.minecraft.world.World;

public class EntityBirchBoat extends EntityModBoatBase {

	public EntityBirchBoat(World world) {
		super(world);
	}
	
	public EntityBirchBoat(World world, double d1, double d2, double d3) {
		super(world, d1, d2, d3);
	}

	@Override
	protected Type getType() {
		return Type.BIRCH;
	}
	
}
