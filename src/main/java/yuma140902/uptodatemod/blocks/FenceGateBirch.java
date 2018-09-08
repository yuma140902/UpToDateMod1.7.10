package yuma140902.uptodatemod.blocks;

import static yuma140902.uptodatemod.util.Stat.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceGateBirch extends BlockFenceGate implements IRegisterable {
	public FenceGateBirch() {
		super();
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return Blocks.planks.getIcon(p_149691_1_, PLANK_META_BIRCH);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_gate_birch");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_gate_birch");
		GameRegistry.registerBlock(this, "fence_gate_birch");
	}
}
