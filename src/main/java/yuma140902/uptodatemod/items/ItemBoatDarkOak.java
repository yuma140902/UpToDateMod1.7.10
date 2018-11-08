package yuma140902.uptodatemod.items;

import net.minecraft.world.World;
import yuma140902.uptodatemod.entity.item.EntityBoatDarkOak;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;

public class ItemBoatDarkOak extends ItemModBoatBase {

	@Override
	protected String getName() {
		return "item_boat_dark_oak";
	}

	@Override
	protected Type getType() {
		return Type.DARK_OAK;
	}

	@Override
	protected EntityModBoatBase getNewEntityModBoat(World world, double d1, double d2, double d3) {
		return new EntityBoatDarkOak(world, d1, d2, d3);
	}
	
}
