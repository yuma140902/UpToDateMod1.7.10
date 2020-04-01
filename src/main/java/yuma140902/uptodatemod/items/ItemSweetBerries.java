package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IRegisterable;

public class ItemSweetBerries extends ItemSeedFood implements IRegisterable {
	
	public ItemSweetBerries() {
		super(2, 0.6F, MyBlocks.sweetBerryBush, Blocks.dirt);
		setCreativeTab(CreativeTabs.tabFood);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(StringUtil.name.domainedUnlocalized("sweet_berries"));
		this.setTextureName(StringUtil.name.domainedTexture("sweet_berries"));
		GameRegistry.registerItem(this, "sweet_berries");
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(world.getBlock(x, y, z) == Blocks.farmland) {
			return false;
		}
		
		return super.onItemUse(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}
	
}
