package yuma140902.uptodatemod.entity.item;

import net.minecraft.world.World;

public class EntityBirchBoat extends EntityModBoatBase {

	public EntityBirchBoat(World world) {
		super(world);
	}

	@Override
	protected Type getType() {
		return Type.BIRCH;
	}
	
}
