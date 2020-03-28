package yuma140902.uptodatemod.entity.item;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyItems;
import yuma140902.yumalib.api.McConst;

public class EntityBoatAcacia extends EntityModBoatBase {

	public EntityBoatAcacia(World world) {
		super(world);
	}
	
	public EntityBoatAcacia(World world, double d1, double d2, double d3) {
		super(world, d1, d2, d3);
	}

	@Override
	protected Type getType() {
		return Type.ACACIA;
	}
	
	@Override
	protected Item getItemBoat() {
		return MyItems.boatAcacia;
	}

	@Override
	protected int getPlankMeta() {
		return McConst.Meta.PLANK_ACACIA;
	}
	
}
