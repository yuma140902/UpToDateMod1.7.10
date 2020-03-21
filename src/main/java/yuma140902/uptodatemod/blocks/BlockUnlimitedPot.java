package yuma140902.uptodatemod.blocks;

import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib_ee.api.IRegisterable;

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
		Block block = world.getBlock(x, y, z);
		if(block == Blocks.flower_pot || block == MyBlocks.unlimitedPot) {
			world.setBlock(x, y, z, MyBlocks.unlimitedPot);
			TileEntityFlowerPot tile = new TileEntityFlowerPot();
			tile.func_145964_a(itemstack.getItem(), itemstack.getItemDamage());
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
