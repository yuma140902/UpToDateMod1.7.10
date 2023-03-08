package yuma140902.yumalib.api.blocks;

import java.util.List;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.registry.Contexts;

/**
 * {@link BlockDoor}を便利にした抽象クラス。
 * <p>
 *     継承して実装してください
 * </p>
 */
public abstract class BlockGenericDoor extends BlockDoor implements IRegisterable {

	/** ドアの上半分用のテクスチャ */
	@SideOnly(Side.CLIENT)
	protected IIcon[] iconTop;
	/** ドアの下半分用のテクスチャ */
	@SideOnly(Side.CLIENT)
	protected IIcon[] iconBottom;
	
	public BlockGenericDoor() {
		super(Material.wood);
		this.setHardness(3.0F);
		this.setStepSound(soundTypeWood);
		this.disableStats();
	}

	/**
	 * これは多分呼ばれない。代わりに{@link #getIcon(IBlockAccess, int, int, int, int)}が呼ばれると思う。
	 * @param p_149691_1_ side
	 * @param p_149691_2_ meta
	 */
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return iconBottom[0];
	}
	
	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		if (side != 1 && side != 0)
		{
			int i1 = this.func_150012_g(world, x, y, z);
			int j1 = i1 & 3;
			boolean flag = (i1 & 4) != 0;
			boolean flag1 = false;
			boolean flag2 = (i1 & 8) != 0;
			
			if (flag)
			{
				if (j1 == 0 && side == 2)
				{
					flag1 = !flag1;
				}
				else if (j1 == 1 && side == 5)
				{
					flag1 = !flag1;
				}
				else if (j1 == 2 && side == 3)
				{
					flag1 = !flag1;
				}
				else if (j1 == 3 && side == 4)
				{
					flag1 = !flag1;
				}
			}
			else
			{
				if (j1 == 0 && side == 5)
				{
					flag1 = !flag1;
				}
				else if (j1 == 1 && side == 3)
				{
					flag1 = !flag1;
				}
				else if (j1 == 2 && side == 4)
				{
					flag1 = !flag1;
				}
				else if (j1 == 3 && side == 2)
				{
					flag1 = !flag1;
				}
				
				if ((i1 & 16) != 0)
				{
					flag1 = !flag1;
				}
			}
			
			return flag2 ? this.iconTop[flag1?1:0] : this.iconBottom[flag1?1:0];
		}
		else
		{
			return this.iconBottom[0];
		}
	}
	
	@Override
	public void register() {
		this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized(getName()));
		this.setBlockTextureName(Contexts.current().nameProvider().domainedTexture(getNameForTexture()));
		GameRegistry.registerBlock(this, getName());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		this.iconTop = new IIcon[2];
		this.iconBottom = new IIcon[2];
		this.iconTop[0] = register.registerIcon(this.getTextureName() + "_top");
		this.iconBottom[0] = register.registerIcon(this.getTextureName() + "_bottom");
		this.iconTop[1] = new IconFlipped(this.iconTop[0], true, false);
		this.iconBottom[1] = new IconFlipped(this.iconBottom[0], true, false);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {}
	
	@Override
	public Item getItem(World world, int x, int y, int z) {
		return getItem();
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int p_149650_3_) {
		return (meta & 8) != 0 ? null : getItem();
	}

	/** テクスチャ名。ModIDなし */
	protected abstract String getNameForTexture();
	/** ブロックの名前。ModIDなし */
	protected abstract String getName();
	/** アイテムの名前。ModIDなし */
	protected abstract Item getItem();
}
