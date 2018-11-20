package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class GenericFenceGate extends BlockFenceGate implements IRegisterable {
	private int iconSourcePlankMeta;
	private String name;
	
	public GenericFenceGate(int iconSourcePlankMeta, String name) {
		super();
		this.iconSourcePlankMeta = iconSourcePlankMeta;
		this.name = name;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return Blocks.planks.getIcon(p_149691_1_, iconSourcePlankMeta);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":" + name);
		GameRegistry.registerBlock(this, name);
	}
}
