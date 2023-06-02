package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.gui.ModGuiHandler;
import yuma140902.uptodatemod.tileentity.ITileEntityDroppable;
import yuma140902.uptodatemod.tileentity.TileEntityBlastFurnace;
import yuma140902.uptodatemod.util.StringUtil;

public class BlockBlastFurnace extends BlockContainer implements IRegisterable, IHasRecipes {
	
	public BlockBlastFurnace() {
		super(Material.rock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.getDomainedUnlocalizedName("blast_furnace"));
		setBlockTextureName(StringUtil.getDomainedTextureName("blast_furnace"));
		GameRegistry.registerBlock(this, "blast_furnace");
	}
	
	@Override
	public void registerRecipes() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		// TODO 自動生成されたメソッド・スタブ
		super.registerBlockIcons(p_149651_1_);
	}
	
	// ================= 登録処理 ここまで =================
	
	
	// ================= 描画処理 ここから =================
	
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		// TODO 自動生成されたメソッド・スタブ
		return super.getIcon(p_149691_1_, p_149691_2_);
	}
	
	// ================= 描画処理 ここまで =================
	
	
	// ================= イベント ここから =================
	
	@Override
	public boolean onBlockActivated(	World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(ModUpToDateMod.INSTANCE, ModGuiHandler.BLAST_FURNACE, world, x, y, z);
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(
			World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_,
			ItemStack p_149689_6_) {
		// TODO 自動生成されたメソッド・スタブ
		super.onBlockPlacedBy(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_, p_149689_6_);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		drop(world, x, y, z);
		
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	private void drop(World world, int x, int y, int z) {
		ITileEntityDroppable tileentity = (ITileEntityDroppable) world.getTileEntity(x, y, z);
		if(tileentity != null) {
			tileentity.drop();
		}
	}
	
	// ================= イベント ここまで =================
	
	// ================= BlockContainer ここから =================
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBlastFurnace();
	}
	
	// ================= BlockContainer ここまで =================
}
