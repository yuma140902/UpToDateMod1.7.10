package yuma140902.uptodatemod.blocks;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.items.ItemBlockColored;
import yuma140902.uptodatemod.util.ColorUtil;

public class BlockConcretePowder extends BlockFalling implements IRegisterable {
	public static final int META_MAX = 15;
	
	private IIcon[] iicons = new IIcon[META_MAX + 1];
	
	public BlockConcretePowder() {
		super(Material.sand);
		this.setHardness(0.5F);
		this.setStepSound(soundTypeSand);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".concrete_powder");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":concrete_powder");
		GameRegistry.registerBlock(this, ItemBlockColored.class, "concrete_powder");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register) {
		for(int meta = 0; meta <= META_MAX; ++meta) {
			iicons[meta] = register.registerIcon(this.getTextureName() + "_" + ColorUtil.metaToString(meta));
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return iicons[meta];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for(int meta = 0; meta <= META_MAX; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public MapColor getMapColor(int meta) {
		return ColorUtil.metaToMapColor(meta);
	}
}
