package yuma140902.uptodatemod.entity.item;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyItems;
import yuma140902.yumalib.api.McConst;

public class EntityBoatSpruce extends EntityModBoatBase {

	public EntityBoatSpruce(World world) {
		super(world);
	}
	
	public EntityBoatSpruce(World world, double d1, double d2, double d3) {
		super(world, d1, d2, d3);
	}

	@Override
	protected Type getType() {
		return Type.SPRUCE;
	}
	
	@Override
	protected Item getItemBoat() {
		return MyItems.boatSpruce;
	}
	
	@Override
	protected int getPlankMeta() {
		return McConst.Meta.PLANK_SPRUCE;
	}
	
}
