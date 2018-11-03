package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class GenericTrapDoor extends BlockTrapDoor implements IRegisterable {
	/** Set this to allow trapdoors to remain free-floating */
  public static boolean disableValidation = false;
  private String name;

  public GenericTrapDoor(String name)
  {
      super(Material.wood);
      this.name = name;
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setHardness(3.0F);
      this.setStepSound(soundTypeWood);
  }
  
  @Override
  public void register() {
  	this.setBlockName(ModUpToDateMod.MOD_ID + "." + name);
		this.setBlockTextureName(ModUpToDateMod.MOD_ID + ":" + name);
		GameRegistry.registerBlock(this, name);
  }
}
