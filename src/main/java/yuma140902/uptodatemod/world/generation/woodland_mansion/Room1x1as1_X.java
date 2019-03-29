package yuma140902.uptodatemod.world.generation.woodland_mansion;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.world.generation.loottable.WoodlandMansionChestGen;
import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

public class Room1x1as1_X implements IStructureComponent {
	
	@Override
	public String getName() {
		return "1x1_as1";
	}
	
	@Override
	public void generate(StructureRelativeCoordinateSystem relCoord) {
		
		// 辺
		for(int x = 0; x <= 6; ++x) {
			relCoord.setBlock(x, 0, 0, Blocks.cobblestone);
			relCoord.setBlock(x, 6, 0, Blocks.cobblestone);
			relCoord.setBlock(x, 0, -6, Blocks.cobblestone);
			relCoord.setBlock(x, 6, -6, Blocks.cobblestone);
		}
		for(int z = -1; z >= -5; --z) {
			relCoord.setBlock(0, 0, z, Blocks.cobblestone);
			relCoord.setBlock(0, 6, z, Blocks.cobblestone);
			relCoord.setBlock(6, 0, z, Blocks.cobblestone);
			relCoord.setBlock(6, 6, z, Blocks.cobblestone);
		}
		for(int y = 0; y <=6; ++y) {
			relCoord.setBlock(0, y, 0, Blocks.cobblestone);
			relCoord.setBlock(0, y, -6, Blocks.cobblestone);
			relCoord.setBlock(6, y, 0, Blocks.cobblestone);
			relCoord.setBlock(6, y, -6, Blocks.cobblestone);
		}
		
		// 筋交い 前の面
		relCoord.setBlock(1, 1, 0, Blocks.cobblestone);
		relCoord.setBlock(2, 2, 0, Blocks.cobblestone);
		relCoord.setBlock(3, 3, 0, Blocks.cobblestone);
		relCoord.setBlock(4, 4, 0, Blocks.cobblestone);
		relCoord.setBlock(5, 5, 0, Blocks.cobblestone);
		relCoord.setBlock(1, 5, 0, Blocks.cobblestone);
		relCoord.setBlock(2, 4, 0, Blocks.cobblestone);
		relCoord.setBlock(4, 2, 0, Blocks.cobblestone);
		relCoord.setBlock(5, 1, 0, Blocks.cobblestone);
		
		// 筋交い 後ろの面
		relCoord.setBlock(1, 1, -6, Blocks.cobblestone);
		relCoord.setBlock(2, 2, -6, Blocks.cobblestone);
		relCoord.setBlock(3, 3, -6, Blocks.cobblestone);
		relCoord.setBlock(4, 4, -6, Blocks.cobblestone);
		relCoord.setBlock(5, 5, -6, Blocks.cobblestone);
		relCoord.setBlock(1, 5, -6, Blocks.cobblestone);
		relCoord.setBlock(2, 4, -6, Blocks.cobblestone);
		relCoord.setBlock(4, 2, -6, Blocks.cobblestone);
		relCoord.setBlock(5, 1, -6, Blocks.cobblestone);
		
		// 筋交い 左の面
		relCoord.setBlock(0, 1, -1, Blocks.cobblestone);
		relCoord.setBlock(0, 2, -2, Blocks.cobblestone);
		relCoord.setBlock(0, 3, -3, Blocks.cobblestone);
		relCoord.setBlock(0, 4, -4, Blocks.cobblestone);
		relCoord.setBlock(0, 5, -5, Blocks.cobblestone);
		relCoord.setBlock(0, 1, -5, Blocks.cobblestone);
		relCoord.setBlock(0, 2, -4, Blocks.cobblestone);
		relCoord.setBlock(0, 4, -2, Blocks.cobblestone);
		relCoord.setBlock(0, 5, -1, Blocks.cobblestone);
		
		// 筋交い 右の面
		relCoord.setBlock(6, 1, -1, Blocks.cobblestone);
		relCoord.setBlock(6, 2, -2, Blocks.cobblestone);
		relCoord.setBlock(6, 3, -3, Blocks.cobblestone);
		relCoord.setBlock(6, 4, -4, Blocks.cobblestone);
		relCoord.setBlock(6, 5, -5, Blocks.cobblestone);
		relCoord.setBlock(6, 1, -5, Blocks.cobblestone);
		relCoord.setBlock(6, 2, -4, Blocks.cobblestone);
		relCoord.setBlock(6, 4, -2, Blocks.cobblestone);
		relCoord.setBlock(6, 5, -1, Blocks.cobblestone);
		
		// 上の面
		for(int x = 1; x <= 5; ++x) {
			relCoord.setBlock(x, 6, -2, Blocks.cobblestone);
			relCoord.setBlock(x, 6, -4, Blocks.cobblestone);
		}
		relCoord.setBlock(2, 6, -1, Blocks.cobblestone);
		relCoord.setBlock(4, 6, -1, Blocks.cobblestone);
		relCoord.setBlock(2, 6, -3, Blocks.cobblestone);
		relCoord.setBlock(4, 6, -3, Blocks.cobblestone);
		relCoord.setBlock(2, 6, -5, Blocks.cobblestone);
		relCoord.setBlock(4, 6, -5, Blocks.cobblestone);
		
		// チェスト
		relCoord.setBlock(5, 0, -1, Blocks.chest);
		relCoord.setMetadataWithNotify(5, 0, -1, relCoord.getRotatedChestMeta(Stat.META_CHEST_EAST));
		TileEntityChest tileentity = new TileEntityChest();
		WoodlandMansionChestGen.instance.setLoot(tileentity, relCoord.getWorld().rand);
		relCoord.setTileEntity(5, 0, -1, tileentity);
	}
	
}
