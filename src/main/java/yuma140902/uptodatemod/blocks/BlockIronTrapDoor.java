package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class BlockIronTrapDoor extends BlockTrapDoor implements IRegisterable, IHasRecipes {

	public BlockIronTrapDoor() {
		super(Material.iron);
		this.setHardness(5.0F);
		this.setStepSound(soundTypeMetal);
	}

	@Override
	public void register() {
		this.setBlockName(StringUtil.name.domainedUnlocalized("trap_door_iron"));
		this.setBlockTextureName(StringUtil.name.domainedTexture("trap_door_iron"));
		GameRegistry.registerBlock(this, "trap_door_iron");
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addShaped(
				new ItemStack(MyBlocks.trapDoorIron, 1, 0),
				"##",
				"##",
				'#', Items.iron_ingot
				);
	}
	
}
