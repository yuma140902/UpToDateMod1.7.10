package yuma140902.uptodatemod.blocks;

import net.minecraft.item.Item;
import yuma140902.uptodatemod.MyItems;
import yuma140902.yumalib.api.blocks.BlockGenericDoor;

public class BlockDoorSpruce extends BlockGenericDoor {
	
	@Override
	protected String getNameForTexture() {
		return "spruce_door";
	}
	
	@Override
	protected String getName() {
		return "door_spruce";
	}
	
	@Override
	protected Item getItem() {
		return MyItems.itemDoorSpruce;
	}
	
}
