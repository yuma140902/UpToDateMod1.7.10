package yuma140902.uptodatemod.blocks.generics;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.items.generics.ItemBlockGenericStrippedLog;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGenericStrippedLog extends BlockRotatedPillar implements IRegisterable, IHasRecipes {
	
	private String name;
	private int plank;
	
	public BlockGenericStrippedLog(String name, int plankMeta) {
		super(Material.wood);
		this.name = name;
		this.plank = plankMeta;
		
		setHardness(2.0F);
		setStepSound(soundTypeWood);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName(name));
		setBlockTextureName(StringUtil.getDomainedTextureName(name));
		GameRegistry.registerBlock(this, ItemBlockGenericStrippedLog.class, name);
		OreDictionary.registerOre("logWood", this);
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(this, 3, 12),
				"##",
				"##",
				'#', this
				);
		
		GameRegistry.addRecipe(
				new ItemStack(Blocks.planks, 4, this.plank),
				"#",
				'#', new ItemStack(this, 1, OreDictionary.WILDCARD_VALUE)
				);
		
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 12)); // 樹幹
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int p_150163_1_) {
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		field_150164_N = register.registerIcon(getTextureName() + "_top");
	}
	
	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public int damageDropped(int meta) {
		if(meta == 12) return 12;
		return 0;
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		if((meta & 0b0100) == 0b0100) return 12;
		return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int meta) {
		byte b0 = 4;
		int i1 = b0 + 1;
		
		if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
			for (int j1 = -b0; j1 <= b0; ++j1) {
				for (int k1 = -b0; k1 <= b0; ++k1) {
					for (int l1 = -b0; l1 <= b0; ++l1) {
						Block block = world.getBlock(x + j1, y + k1, z + l1);
						if (block.isLeaves(world, x + j1, y + k1, z + l1)) {
							block.beginLeavesDecay(world, x + j1, y + k1, z + l1);
						}
					}
				}
			}
		}
	}
}
