package yuma140902.uptodatemod.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.ModUpToDateMod;

public class Stone extends Block implements IMyBlock {
	public static final int META_MAX = 6;
	public static final int META_GRANITE = 1;
	public static final int META_SMOOTH_GRANITE = 2;
	public static final int META_DIORITE = 3;
	public static final int META_SMOOTH_DIORITE = 4;
	public static final int META_ANDESITE = 5;
	public static final int META_SMOOTH_ANDESITE = 6;
	
	public Stone() {
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeStone);
		this.setHarvestLevel("pickaxe", 1);
		this.setLightLevel(0.0F);
	}
	
	@Override
	public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
		return true;
	}
	
	@Override
	public void register() {
		this.setBlockName("newstone");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":newstone");
		GameRegistry.registerBlock(this, ItemBlockStone.class, "newstone");
	}
	
	private IIcon[] iIcons = new IIcon[META_MAX + 1];
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		for(int i = 1; i <= META_MAX; ++i) { //メタデータ0は、ない
			switch (i) {
				case META_GRANITE:
					iIcons[i] = register.registerIcon(this.getTextureName() + "_granite");
					break;
				case META_SMOOTH_GRANITE:
					iIcons[i] = register.registerIcon(this.getTextureName() + "_granite_smooth");
					break;
					
				case META_DIORITE:
					iIcons[i] = register.registerIcon(this.getTextureName() + "_diorite");
					break;
					
				case META_SMOOTH_DIORITE:
					iIcons[i] = register.registerIcon(this.getTextureName() + "_diorite_smooth");
					break;
					
				case META_ANDESITE:
					iIcons[i] = register.registerIcon(this.getTextureName() + "_andesite");
					break;
					
				case META_SMOOTH_ANDESITE:
					iIcons[i] = register.registerIcon(this.getTextureName() + "_andesite_smooth");
					break;
				
				default:
					iIcons[i] = register.registerIcon(this.getTextureName() + "-" + i);
					break;
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iIcons[meta];
	}
	
	@SuppressWarnings({
		"unchecked", "rawtypes"
	})
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for(int i = 1; i <= META_MAX; ++i) { //メタデータ0は、なし
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
}
