package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockIronTrapDoor extends BlockTrapDoor implements IRegisterable, IHasRecipes {

	public BlockIronTrapDoor() {
		super(Material.iron);
		this.setHardness(5.0F);
		this.setStepSound(soundTypeMetal);
	}

	@Override
	public void register() {
		this.setBlockName(StringUtil.getDomainedUnlocalizedName("trap_door_iron"));
		this.setBlockTextureName(StringUtil.getDomainedTextureName("trap_door_iron"));
		GameRegistry.registerBlock(this, "trap_door_iron");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.trapDoorIron, 1, 0),
				"##",
				"##",
				'#', Items.iron_ingot
				);
	}
	
}
