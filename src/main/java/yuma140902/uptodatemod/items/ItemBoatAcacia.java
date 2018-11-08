package yuma140902.uptodatemod.items;

import net.minecraft.world.World;
import yuma140902.uptodatemod.entity.item.EntityBoatAcacia;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;

public class ItemBoatAcacia extends ItemModBoatBase {

	@Override
	protected String getName() {
		return "item_boat_acacia";
	}

	@Override
	protected Type getType() {
		return Type.ACACIA;
	}

	@Override
	protected EntityModBoatBase getEntityModBoat(World world, double d1, double d2, double d3) {
		return new EntityBoatAcacia(world, d1, d2, d3);
	}
	
}
