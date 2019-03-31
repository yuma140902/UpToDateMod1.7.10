package yuma140902.uptodatemod.blocks.generics;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockGenericTrapDoor extends BlockTrapDoor implements IRegisterable, IHasRecipes {
	/** Set this to allow trapdoors to remain free-floating */
  private String name;
  private int plankMeta;
  
  private IIcon icon0, icon90, icon180, icon270;

  public BlockGenericTrapDoor(String name, int plankMeta)
  {
      super(Material.wood);
      this.name = name;
      this.plankMeta = plankMeta;
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setHardness(3.0F);
      this.setStepSound(soundTypeWood);
  }
  
  @Override
  public void registerBlockIcons(IIconRegister register) {
  	super.registerBlockIcons(register);
  	String texture_name = StringUtil.getDomainedTextureName(name);
  	icon0 = register.registerIcon(texture_name);
  	icon90 = register.registerIcon(texture_name + "_90");
  	icon180 = register.registerIcon(texture_name + "_180");
  	icon270 = register.registerIcon(texture_name + "_270");
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int side, int meta) {
  	//メタデータの仕様
  	/*
  	 * 2進数で4桁
  	 * 0b0000
  	 * 
  	 * 下位1,2桁目 : 方角
  	 * 0b**00 : south
  	 * 0b**01 : north
  	 * 0b**10 : east
  	 * 0b**11 : west
  	 * 
  	 * 下位3桁目 : 開いているか否か
  	 * 0b*0** : 閉じている CLOSE
  	 * 0b*1** : 開いている OPEN
  	 * 
  	 * 下位4桁目 : 上側に付いているか否か
  	 * 0b0*** : 下側
  	 * 0b1*** : 上側
  	 */
  	
  	//上記メタデータの仕様より、
  	// meta & 0b0011
  	//で方角を取得できる
  	
  	int direction = meta & 0b0011;
  	boolean isOpen = (meta & 0b0100) >>> 2 == 0 ? false : true;
  	boolean isTop = (meta & 0b1000) >>> 3 == 0 ? false : true;
  	
  	if(!isOpen) {
  		if (side == Stat.SIDE_TOP || side == Stat.SIDE_BOTTOM) {
    		switch(direction) {
    			case 0b0000:
    				return icon0;
    			case 0b0001:
    				return icon180;
    			case 0b0010:
    				return icon270;
    			case 0b0011:
    				return icon90;
    		}
    	}
    	
    	else {
    		return Blocks.planks.getIcon(1, plankMeta);
    	}
  	}
  	
  	else if(isTop) {
  		if(isSameSide(side, direction) || isOppositeSide(side, direction)) {
  			return icon180;
  		}
  		
  		else {
  			return Blocks.planks.getIcon(1, plankMeta);
  		}
  	}
  	
  	else {
  		if(isSameSide(side, direction) || isOppositeSide(side, direction)) {
  			return icon0;
  		}
  		
  		else {
  			return Blocks.planks.getIcon(1, plankMeta);
  		}
  	}
  	
  	return Blocks.planks.getIcon(1, plankMeta);
  }
  
  private boolean isSameSide(int side, int directionMeta) {
  	return
  			   (side == Stat.SIDE_SOUTH && directionMeta == 0b0000)
  			|| (side == Stat.SIDE_NORTH && directionMeta == 0b0001)
  			|| (side == Stat.SIDE_EAST && directionMeta == 0b0010)
  			|| (side == Stat.SIDE_WEST && directionMeta == 0b0011);
  }
  
  private boolean isOppositeSide(int side, int directionMeta) {
  	return
  			   (side == Stat.SIDE_NORTH && directionMeta == 0b0000)
  			|| (side == Stat.SIDE_SOUTH && directionMeta == 0b0001)
  			|| (side == Stat.SIDE_WEST && directionMeta == 0b0010)
  			|| (side == Stat.SIDE_EAST && directionMeta == 0b0011);
  }
  
  @Override
  public void register() {
  	this.setBlockName(StringUtil.getDomainedUnlocalizedName(name));
		this.setBlockTextureName(StringUtil.getDomainedTextureName(name));
		GameRegistry.registerBlock(this, name);
  }
  
  @Override
  public void registerRecipes() {
  	GameRegistry.addRecipe(
				new ItemStack(this, 2, 0),
				"###",
				"###",
				'#', new ItemStack(Blocks.planks, 1, plankMeta)
				);
  }
}
