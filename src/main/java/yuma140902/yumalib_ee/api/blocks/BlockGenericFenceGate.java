package yuma140902.yumalib_ee.api.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib_ee.api.IRegisterable;

public class BlockGenericFenceGate extends BlockFenceGate implements IRegisterable {
	private int iconSourcePlankMeta;
	private String name;
	
	public BlockGenericFenceGate(int iconSourcePlankMeta, String name) {
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
		this.setBlockName(StringUtil.getDomainedUnlocalizedName(name));
		this.setBlockTextureName(StringUtil.getDomainedTextureName(name));
		GameRegistry.registerBlock(this, name);
	}
}
