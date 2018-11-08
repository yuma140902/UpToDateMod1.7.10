package yuma140902.uptodatemod.entity.item;

import net.minecraft.world.World;

public class EntityAcaciaBoat extends EntityModBoatBase {

	public EntityAcaciaBoat(World world) {
		super(world);
	}

	@Override
	protected Type getType() {
		return Type.ACACIA;
	}
	
}
