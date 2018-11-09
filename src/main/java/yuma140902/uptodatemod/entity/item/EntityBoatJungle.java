package yuma140902.uptodatemod.entity.item;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.util.Stat;

public class EntityBoatJungle extends EntityModBoatBase {

	public EntityBoatJungle(World world) {
		super(world);
	}
	
	public EntityBoatJungle(World world, double d1, double d2, double d3) {
		super(world, d1, d2, d3);
	}

	@Override
	protected Type getType() {
		return Type.JUNGLE;
	}
	
	@Override
	protected Item getItemBoat() {
		return MyItems.boatJungle;
	}
	
	@Override
	protected int getPlankMeta() {
		return Stat.PLANK_META_JUNGLE;
	}
	
}
