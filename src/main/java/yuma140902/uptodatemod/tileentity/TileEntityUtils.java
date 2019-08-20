package yuma140902.uptodatemod.tileentity;

import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TileEntityUtils {
	private TileEntityUtils() {}
	
	public static void dropItems(IInventory inventory, World world, int x, int y, int z) {
		for(int i = 0; i < inventory.getSizeInventory(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);
			if(itemstack != null && !world.isRemote) {
				Random rand = world.rand;
				float xDiff = rand.nextFloat() * 0.6F + 0.1F;
				float yDiff = rand.nextFloat() * 0.6F + 0.1F;
				float zDiff = rand.nextFloat() * 0.6F + 0.1F;
				
				EntityItem entityItem = new EntityItem(world, x + xDiff, y + yDiff, z + zDiff, itemstack.copy());
				float motionScale = 0.025F;
				entityItem.motionX = (float) rand.nextGaussian() * motionScale;
				entityItem.motionY = (float) rand.nextGaussian() * motionScale + 0.1F;
				entityItem.motionZ = (float) rand.nextGaussian() * motionScale;
				world.spawnEntityInWorld(entityItem);
			}
		}
	}
}
