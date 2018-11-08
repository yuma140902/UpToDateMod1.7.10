package yuma140902.uptodatemod.entity.item;

import net.minecraft.world.World;

public class EntitySpruceBoat extends EntityModBoatBase {

	public EntitySpruceBoat(World world) {
		super(world);
	}

	@Override
	protected Type getType() {
		return Type.SPRUCE;
	}
	
}
