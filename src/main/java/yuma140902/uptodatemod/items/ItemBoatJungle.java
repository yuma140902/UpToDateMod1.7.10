package yuma140902.uptodatemod.items;

import net.minecraft.world.World;
import yuma140902.uptodatemod.entity.item.EntityBoatJungle;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;

public class ItemBoatJungle extends ItemModBoatBase {

	@Override
	protected String getName() {
		return "item_boat_jungle";
	}

	@Override
	protected Type getType() {
		return Type.JUNGLE;
	}

	@Override
	protected EntityModBoatBase getNewEntityModBoat(World world, double d1, double d2, double d3) {
		return new EntityBoatJungle(world, d1, d2, d3);
	}
	
}
