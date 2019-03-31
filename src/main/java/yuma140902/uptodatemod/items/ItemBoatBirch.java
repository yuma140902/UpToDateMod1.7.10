package yuma140902.uptodatemod.items;

import net.minecraft.world.World;
import yuma140902.uptodatemod.entity.item.EntityBoatBirch;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;

public class ItemBoatBirch extends ItemModBoatBase {

	@Override
	protected String getName() {
		return "item_boat_birch";
	}
	
	@Override
	protected String getNameForTexture() {
		return "birch_boat";
	}

	@Override
	protected Type getType() {
		return Type.BIRCH;
	}

	@Override
	protected EntityModBoatBase getNewEntityModBoat(World world, double d1, double d2, double d3) {
		return new EntityBoatBirch(world, d1, d2, d3);
	}
	
}
