package yuma140902.yumalib.api.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.items.ItemBlockGenericTrapdoor;
import yuma140902.yumalib.api.registry.Contexts;
import yuma140902.yumalib.api.util.WorldUtils;
import yuma140902.yumalib.api.blockstate.GenericTrapdoorState;

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
  	icon0 = register.registerIcon(getTextureName());
  	icon90 = register.registerIcon(getTextureName() + "_90");
  	icon180 = register.registerIcon(getTextureName() + "_180");
  	icon270 = register.registerIcon(getTextureName() + "_270");
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int side, int meta) {
		GenericTrapdoorState state = GenericTrapdoorState.fromMetadata(meta);
  	
  	GenericTrapdoorState.Orientation orientation = state.orientation();
  	boolean isOpen = state.isOpen();
  	boolean isTop = state.isUpper();
  	
  	if(!isOpen) {
  		if (side == McConst.SIDE_TOP || side == McConst.SIDE_BOTTOM) {
    		switch(orientation) {
					case SOUTH:
    				return icon0;
					case NORTH:
    				return icon180;
					case EAST:
    				return icon270;
					case WEST:
    				return icon90;
    		}
    	}
    	
    	else {
    		return Blocks.planks.getIcon(1, plankMeta);
    	}
  	}
  	
  	else if(isTop) {
  		if(GenericTrapdoorState.Orientation.isSameSide(side, orientation) || GenericTrapdoorState.Orientation.isOppositeSide(side, orientation)) {
  			return icon180;
  		}
  		
  		else {
  			return Blocks.planks.getIcon(1, plankMeta);
  		}
  	}
  	
  	else {
  		if(GenericTrapdoorState.Orientation.isSameSide(side, orientation) || GenericTrapdoorState.Orientation.isOppositeSide(side, orientation)) {
  			return icon0;
  		}
  		
  		else {
  			return Blocks.planks.getIcon(1, plankMeta);
  		}
  	}
  	
  	return Blocks.planks.getIcon(1, plankMeta);
  }
  
  @Override
  public void register() {
  	this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized(name));
		this.setBlockTextureName(Contexts.current().nameProvider().domainedTexture(name));
		GameRegistry.registerBlock(this, ItemBlockGenericTrapdoor.class, name);
  }
  
  @Override
  public void registerRecipes() {
  	RecipeRegister.addShaped(
				new ItemStack(this, 2, 0),
				"###",
				"###",
				'#', new ItemStack(Blocks.planks, 1, plankMeta)
				);
  }
	
	public static void onBlockPlaced(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata){
  	GenericTrapdoorState state;
 		if(side == McConst.SIDE_BOTTOM || side == McConst.SIDE_TOP){
 			state = new GenericTrapdoorState(side, hitY).rotate(player);
		}else{
 			state = new GenericTrapdoorState(side, hitY);
		}
 		WorldUtils.setMeta(world, x, y, z, state.metadata());
	}
}
