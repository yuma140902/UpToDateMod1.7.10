package yuma140902.uptodatemod.world.generation.woodland_mansion;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import yuma140902.uptodatemod.util.ColorUtil;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.world.generation.loottable.WoodlandMansionChestGen;
import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

// チェス盤の部屋
public class Room1x1a4_Chess implements IStructureComponent {
	
	@Override
	public String getName() {
		return "1x1_a4";
	}
	
	@Override
	public void generate(StructureRelativeCoordinateSystem relCoord) {
		int darkOakLogUpDown = relCoord.getRotatedPillarMeta(Stat.LOG2_META_DARK_OAK | Stat.META_PILLAR_UP_DOWN);
		int darkOakLogWestEast = relCoord.getRotatedPillarMeta(Stat.LOG2_META_DARK_OAK | Stat.META_PILLAR_WEST_EAST);
		int darkOakLogNorthSouth = relCoord.getRotatedPillarMeta(Stat.LOG2_META_DARK_OAK | Stat.META_PILLAR_NORTH_SOUTH);
		int chestMeta = relCoord.getRotatedChestMeta(Stat.META_CHEST_SOUTH);
		int gray = ColorUtil.META_GRAY;
		int lightGray = ColorUtil.META_LIGHT_GRAY;
		
		// 左手前の縦柱
		relCoord.setBlockAndMeta(0, 0, 0, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(0, 1, 0, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(0, 2, 0, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(0, 3, 0, Blocks.log2, darkOakLogUpDown);
		
		// 右手前の縦柱
		relCoord.setBlockAndMeta(6, 0, 0, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(6, 1, 0, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(6, 2, 0, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(6, 3, 0, Blocks.log2, darkOakLogUpDown);
		
		// 左手前の縦柱の右のフェンス
		relCoord.setBlock(1, 0, 0, Blocks.fence);
		relCoord.setBlock(1, 1, 0, Blocks.fence);
		relCoord.setBlock(1, 2, 0, Blocks.fence);
		relCoord.setBlock(1, 3, 0, Blocks.fence);
		
		// 右手前の縦柱の左のフェンス
		relCoord.setBlock(5, 0, 0, Blocks.fence);
		relCoord.setBlock(5, 1, 0, Blocks.fence);
		relCoord.setBlock(5, 2, 0, Blocks.fence);
		relCoord.setBlock(5, 3, 0, Blocks.fence);
		
		// 手前上の横柱
		relCoord.setBlockAndMeta(0, 4, 0, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(1, 4, 0, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(2, 4, 0, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(3, 4, 0, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(4, 4, 0, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(5, 4, 0, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(6, 4, 0, Blocks.log2, darkOakLogWestEast);
		
		// TODO: チェストのルートテーブル
		// 手前上の横柱の上のチェスト
		relCoord.setBlock(3, 5, 0, Blocks.chest);
		relCoord.setMetadataWithNotify(3, 5, 0, chestMeta);
		TileEntityChest tileentity = new TileEntityChest();
		tileentity = WoodlandMansionChestGen.instance.setLoot(tileentity, relCoord.getWorld().rand);
		relCoord.setTileEntity(3, 5, 0, tileentity);
		
		// 左上の横柱
		relCoord.setBlockAndMeta(0, 4, -1, Blocks.log2, darkOakLogNorthSouth);
		relCoord.setBlockAndMeta(0, 4, -2, Blocks.log2, darkOakLogNorthSouth);
		relCoord.setBlockAndMeta(0, 4, -3, Blocks.log2, darkOakLogNorthSouth);
		relCoord.setBlockAndMeta(0, 4, -4, Blocks.log2, darkOakLogNorthSouth);
		relCoord.setBlockAndMeta(0, 4, -5, Blocks.log2, darkOakLogNorthSouth);
		
		// 右上の横柱
		relCoord.setBlockAndMeta(6, 4, -1, Blocks.log2, darkOakLogNorthSouth);
		relCoord.setBlockAndMeta(6, 4, -2, Blocks.log2, darkOakLogNorthSouth);
		relCoord.setBlockAndMeta(6, 4, -3, Blocks.log2, darkOakLogNorthSouth);
		relCoord.setBlockAndMeta(6, 4, -4, Blocks.log2, darkOakLogNorthSouth);
		relCoord.setBlockAndMeta(6, 4, -5, Blocks.log2, darkOakLogNorthSouth);
		
		// 左側、右側のフェンスの壁
		for(int z = -1; z >= -5; --z) {
			for(int y = 0; y <= 3; ++y) {
				relCoord.setBlock(0, y, z, Blocks.fence);
				relCoord.setBlock(6, y, z, Blocks.fence);
			}
		}
		
		
		// 左奥の縦柱
		relCoord.setBlockAndMeta(0, 0, -6, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(0, 1, -6, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(0, 2, -6, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(0, 3, -6, Blocks.log2, darkOakLogUpDown);
		
		// 右奥の縦柱
		relCoord.setBlockAndMeta(6, 0, -6, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(6, 1, -6, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(6, 2, -6, Blocks.log2, darkOakLogUpDown);
		relCoord.setBlockAndMeta(6, 3, -6, Blocks.log2, darkOakLogUpDown);
		
		// 奥上の横柱
		relCoord.setBlockAndMeta(0, 4, -6, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(1, 4, -6, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(2, 4, -6, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(3, 4, -6, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(4, 4, -6, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(5, 4, -6, Blocks.log2, darkOakLogWestEast);
		relCoord.setBlockAndMeta(6, 4, -6, Blocks.log2, darkOakLogWestEast);
		
		// 奥のフェンスの壁
		for(int x = 1; x <= 5; ++x) {
			for(int y = 0; y <= 3; ++y) {
				relCoord.setBlock(x, y, -6, Blocks.fence);
			}
		}
		
		// カーペット
		relCoord.setBlockAndMeta(2, 0, 0, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(3, 0, 0, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(4, 0, 0, Blocks.carpet, gray);
		
		relCoord.setBlockAndMeta(1, 0, -1, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(2, 0, -1, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(3, 0, -1, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(4, 0, -1, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(5, 0, -1, Blocks.carpet, gray);
		
		relCoord.setBlockAndMeta(1, 0, -2, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(2, 0, -2, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(3, 0, -2, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(4, 0, -2, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(5, 0, -2, Blocks.carpet, lightGray);
		
		relCoord.setBlockAndMeta(1, 0, -3, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(2, 0, -3, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(3, 0, -3, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(4, 0, -3, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(5, 0, -3, Blocks.carpet, gray);
		
		relCoord.setBlockAndMeta(1, 0, -4, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(2, 0, -4, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(3, 0, -4, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(4, 0, -4, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(5, 0, -4, Blocks.carpet, lightGray);
		
		relCoord.setBlockAndMeta(1, 0, -5, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(2, 0, -5, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(3, 0, -5, Blocks.carpet, gray);
		relCoord.setBlockAndMeta(4, 0, -5, Blocks.carpet, lightGray);
		relCoord.setBlockAndMeta(5, 0, -5, Blocks.carpet, gray);
		
	}
}
