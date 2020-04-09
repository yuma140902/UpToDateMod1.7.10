package yuma140902.uptodatemod.blocks;

import net.minecraft.item.Item;
import yuma140902.uptodatemod.MyItems;
import yuma140902.yumalib.api.blocks.BlockGenericDoor;

public class BlockDoorDarkOak extends BlockGenericDoor {
	
	@Override
	protected String getNameForTexture() {
		return "dark_oak_door";
	}
	
	@Override
	protected String getName() {
		return "door_dark_oak";
	}
	
	@Override
	protected Item getItem() {
		return MyItems.itemDoorDarkOak;
	}
}
