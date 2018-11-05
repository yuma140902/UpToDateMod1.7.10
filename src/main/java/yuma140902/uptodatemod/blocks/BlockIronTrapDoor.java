package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class BlockIronTrapDoor extends BlockTrapDoor implements IRegisterable {

	public BlockIronTrapDoor() {
		super(Material.iron);
		this.setHardness(5.0F);
		this.setStepSound(soundTypeMetal);
	}

	@Override
	public void register() {
		this.setBlockName(ModUpToDateMod.MOD_ID + ".trap_door_iron");
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":trap_door_iron");
		GameRegistry.registerBlock(this, "trap_door_iron");
	}
	
}
