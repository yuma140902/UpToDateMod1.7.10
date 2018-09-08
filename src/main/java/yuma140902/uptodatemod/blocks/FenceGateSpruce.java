package yuma140902.uptodatemod.blocks;

import static yuma140902.uptodatemod.util.Stat.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class FenceGateSpruce extends BlockFenceGate implements IRegisterable {
	public FenceGateSpruce() {
		super();
	}
	
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return Blocks.planks.getIcon(p_149691_1_, PLANK_META_SPRUCE);
	}
	
	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".fence_gate_spruce");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":fence_gate_spruce");
		GameRegistry.registerBlock(this, "fence_gate_spruce");
	}
}
