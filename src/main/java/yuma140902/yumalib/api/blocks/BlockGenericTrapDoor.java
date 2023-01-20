package yuma140902.yumalib.api.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.items.ItemBlockGenericTrapdoor;
import yuma140902.yumalib.api.registry.Contexts;
import yuma140902.yumalib.api.util.BlockWithMetadata;
import yuma140902.yumalib.api.util.ItemWithMetadata;
import yuma140902.yumalib.api.util.WorldUtils;
import yuma140902.yumalib.api.blockstate.GenericTrapdoorState;

/**
 * トラップドア。
 * <p>
 * 普通は継承する必要はなく、{@link TrapdoorBuilder}を使ってインスタンスを生成すればよい
 * </p>
 * <p>
 *     回転に対応しているので便利
 * </p>
 */
public class BlockGenericTrapDoor extends BlockTrapDoor implements IRegisterable, IHasRecipes {
	/** Set this to allow trapdoors to remain free-floating */
  private final String name;
  private final ItemWithMetadata ingredient;
  private final boolean rotateTex;
  private final boolean sideTex;
  private final BlockWithMetadata blockForSideTex;
  
  private IIcon icon0, icon90, icon180, icon270;
	
	/**
	 * @param material ブロックの材質
	 * @param name ブロックの名前
	 * @param ingredient 原料
	 * @param rotateTex 上面・下面のテクスチャを回転させるかどうか
	 * @param sideTex 側面のテクスチャとして別ブロックのものを使うかどうか
	 * @param blockForSideTex 側面のテクスチャとして使うブロック
	 */
  protected BlockGenericTrapDoor(Material material, String name, float hardness, SoundType stepSound, ItemWithMetadata ingredient, boolean rotateTex, boolean sideTex, BlockWithMetadata blockForSideTex)
  {
      super(material);
      this.name = name;
      this.ingredient = ingredient;
      this.rotateTex = rotateTex;
      this.sideTex = sideTex;
      this.blockForSideTex = blockForSideTex;
      
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setHardness(hardness);
      this.setStepSound(stepSound);
  }
  
  @Override
  public void registerBlockIcons(IIconRegister register) {
  	super.registerBlockIcons(register);
  	icon0 = register.registerIcon(getTextureName());
  	if(this.rotateTex) {
			icon90 = register.registerIcon(getTextureName() + "_90");
			icon180 = register.registerIcon(getTextureName() + "_180");
			icon270 = register.registerIcon(getTextureName() + "_270");
		}
  	else{
  		icon90 = icon180 = icon270 = icon0;
		}
  }
  
  @SideOnly(Side.CLIENT)
  private IIcon getSideIcon(){
		return sideTex ? this.blockForSideTex.block.getIcon(McConst.SIDE_TOP, this.blockForSideTex.meta) : icon0;
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
    	return getSideIcon();
  	}
  	
  	else if(isTop) {
  		if(GenericTrapdoorState.Orientation.isSameSide(side, orientation) || GenericTrapdoorState.Orientation.isOppositeSide(side, orientation)) {
  			return icon180;
  		}
  		return getSideIcon();
  	}
  	
  	else {
  		if(GenericTrapdoorState.Orientation.isSameSide(side, orientation) || GenericTrapdoorState.Orientation.isOppositeSide(side, orientation)) {
  			return icon0;
  		}
  		return getSideIcon();
  	}
  }
  
  @Override
  public void register() {
  	this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized(name));
		this.setBlockTextureName(Contexts.current().nameProvider().domainedTexture(name));
		GameRegistry.registerBlock(this, ItemBlockGenericTrapdoor.class, name);
  }
  
  @Override
  public void registerRecipes() {
  	if(ingredient == null) return;
  	RecipeRegister.addShaped(
				new ItemStack(this, 2, 0),
				"###",
				"###",
				'#', this.ingredient.newItemStack()
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
