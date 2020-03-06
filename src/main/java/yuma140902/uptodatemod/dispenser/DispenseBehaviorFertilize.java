package yuma140902.uptodatemod.dispenser;

import javax.annotation.Nonnull;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import yuma140902.uptodatemod.blocks.BlockSweetBerryBush;

public class DispenseBehaviorFertilize extends DispenseBehaviorBase {

	@Override
	protected boolean doDispense(World world, EnumFacing facing, int x, int y, int z, @Nonnull ItemStack itemstack) {
		if(!isFertilizer(itemstack)) return false;
		
		int dimId = world.provider.dimensionId;
		int targetX = x + facing.getFrontOffsetX();
		int targetY = y + facing.getFrontOffsetY();
		int targetZ = z + facing.getFrontOffsetZ();
		
		if(world.getBlock(targetX, targetY, targetZ) instanceof BlockSweetBerryBush) {
			int meta = world.getBlockMetadata(targetX, targetY, targetZ);
			boolean canFertilize = BlockSweetBerryBush.canFertilizeSweetBerryBush(meta);
			if(canFertilize) {
				BlockSweetBerryBush.fertilizeSweetBerryBush(world, targetX, targetY, targetZ);
			}
			return canFertilize;
		}
		
		return false;
	}
	
	private boolean isFertilizer(@Nonnull ItemStack itemstack) {
		Item item = itemstack.getItem();
		int meta = itemstack.getItemDamage();
		return (item == Items.dye && meta == 15);
	}
}
