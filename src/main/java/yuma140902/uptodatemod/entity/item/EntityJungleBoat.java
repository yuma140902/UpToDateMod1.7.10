package yuma140902.uptodatemod.entity.item;

import net.minecraft.world.World;

public class EntityJungleBoat extends EntityModBoatBase {

	public EntityJungleBoat(World world) {
		super(world);
	}

	@Override
	protected Type getType() {
		return Type.JUNGLE;
	}
	
}
