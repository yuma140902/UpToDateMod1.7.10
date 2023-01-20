package yuma140902.yumalib.api.dispenser;

import javax.annotation.Nonnull;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * ディスペンサーの挙動を定義するクラス
 * <p>
 *     インスタンスはBlockDispenser.dispenseBehaviorRegistryに登録する
 * </p>
 */
public abstract class DispenseBehaviorBase extends BehaviorDefaultDispenseItem {
	@Override
	protected ItemStack dispenseStack(IBlockSource blockSource, ItemStack itemstack) {
		EnumFacing facing = BlockDispenser.func_149937_b(blockSource.getBlockMetadata());
        int x = blockSource.getXInt();
        int y = blockSource.getYInt();
        int z = blockSource.getZInt();
        ItemStack itemstackToConsume = itemstack.splitStack(1);
        boolean didDispense = doDispense(blockSource.getWorld(), facing, x, y, z, itemstackToConsume);
        if(!didDispense) {
            ++itemstack.stackSize;
        }
        return itemstack;
	}

    /**
     * @param world ディスペンサーの設置されているワールド
     * @param facing ディスペンサーの向き
     * @param x ディスペンサーのx座標
     * @param y ディスペンサーのy座標
     * @param z ディスペンサーのz座標
     * @param itemstack 使用しようとしているアイテム
     * @return itemstackを使用したか否か
     */
	protected abstract boolean doDispense(@Nonnull World world, @Nonnull EnumFacing facing, int x, int y, int z, @Nonnull ItemStack itemstack);
}
