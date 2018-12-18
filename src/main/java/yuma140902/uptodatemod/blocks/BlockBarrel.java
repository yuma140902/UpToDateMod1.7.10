package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyGuis;

public class BlockBarrel extends Block implements IRegisterable, IHasRecipes {
	public static final int GUI_ID = MyGuis.INSTANCE.getNextGuiId();
	
	public BlockBarrel() {
		super(Material.wood);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public void register() {
		setBlockName(ModUpToDateMod.MOD_ID + ".barrel");
		//setBlockTextureName(ModUpToDateMod.MOD_ID + ":barrel");
		setBlockTextureName("log_oak_top");
		GameRegistry.registerBlock(this, "barrel");
	}
	
	@Override
	public void registerRecipes() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public boolean onBlockActivated(	World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(ModUpToDateMod.INSTANCE, BlockBarrel.GUI_ID, world, x, y, z);
		return true;
	}
}

