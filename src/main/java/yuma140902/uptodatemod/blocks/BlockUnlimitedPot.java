package yuma140902.uptodatemod.blocks;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockUnlimitedPot extends BlockFlowerPot implements IRegisterable {
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("unlimited_pot"));
		setBlockTextureName("minecraft:flower_pot");
		GameRegistry.registerBlock(this, "unlimited_pot");
	}
	
	/**
	 * ポットの中身を設定
	 * @param itemstack
	 * @param player
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return アイテムの設置に成功したらtrue
	 */
	public boolean putItemIn(@Nonnull ItemStack itemstack, @Nonnull EntityPlayer player, @Nonnull World world, int x, int y, int z) {
		if(world.getBlock(x, y, z).isFlowerPot()) {
			world.setBlock(x, y, z, MyBlocks.unlimitedPot);
			TileEntityFlowerPot tile = new TileEntityFlowerPot();
			tile.func_145964_a(itemstack.getItem(), 0);
			tile.markDirty();
			world.setTileEntity(x, y, z, tile);
			world.markBlockForUpdate(x, y, z);
			
			if (!player.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
      {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
      }
			
			return true;
		}
		else {
			return false;
		}
	}
}
