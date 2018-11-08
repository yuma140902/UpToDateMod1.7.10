package yuma140902.uptodatemod.entity.item;

import net.minecraft.world.World;

public class EntityDarkOakBoat extends EntityModBoatBase {

	public EntityDarkOakBoat(World world) {
		super(world);
	}

	@Override
	protected Type getType() {
		return Type.DARK_OAK;
	}
	
}
