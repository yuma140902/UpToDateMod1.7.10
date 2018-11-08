package yuma140902.uptodatemod.items;

import net.minecraft.world.World;
import yuma140902.uptodatemod.entity.item.EntityBoatSpruce;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;

public class ItemBoatSpruce extends ItemModBoatBase {

	@Override
	protected String getName() {
		return "item_boat_spruce";
	}

	@Override
	protected Type getType() {
		return Type.SPRUCE;
	}

	@Override
	protected EntityModBoatBase getNewEntityModBoat(World world, double d1, double d2, double d3) {
		return new EntityBoatSpruce(world, d1, d2, d3);
	}
	
}
