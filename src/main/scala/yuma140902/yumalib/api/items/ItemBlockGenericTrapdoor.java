package yuma140902.yumalib.api.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import yuma140902.yumalib.api.blocks.BlockGenericTrapDoor;

/**
 * {@link BlockGenericTrapDoor}に対応するItemBlock
 */
public class ItemBlockGenericTrapdoor extends ItemBlock {
	public ItemBlockGenericTrapdoor(Block block) {
		super(block);
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		if(result){
			// hitY、sideとplayerを同時に取りたかった
			BlockGenericTrapDoor.onBlockPlaced(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
		}
		return result;
	}
}
