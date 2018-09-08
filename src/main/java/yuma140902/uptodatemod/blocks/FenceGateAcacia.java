package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceGateAcacia extends BlockFenceGate implements IRegisterable {
	public FenceGateAcacia() {
		super();
	}
	
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return Blocks.planks.getIcon(p_149691_1_, 4);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_gate_acacia");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_gate_acacia");
		GameRegistry.registerBlock(this, "fence_gate_acacia");
	}
}
