package yuma140902.uptodatemod.world.generation.woodland_mansion;

import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

//レールとはしごの部屋 1x1_a2
public class RoomRailLadder implements IStructureComponent {

	@Override
	public void generate(StructureRelativeCoordinateSystem relCoord) {
		int stairs_east = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_EAST);
		int stairs_west = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_WEST);
		int stairs_north = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_NORTH);
		int ladder_east = relCoord.getRotatedLadderMeta(Stat.META_LADDER_EAST);
		int ladder_west = relCoord.getRotatedLadderMeta(Stat.META_LADDER_WEST);
		int ladder_north = relCoord.getRotatedLadderMeta(Stat.META_LADDER_NORTH);
		
		
		relCoord.setBlock(0, 0, 0, Blocks.cobblestone);
		relCoord.setBlock(0, 1, 0, Blocks.cobblestone);
		relCoord.setBlock(0, 2, 0, Blocks.cobblestone);

		relCoord.setBlock(6, 0, 0, Blocks.cobblestone);
		relCoord.setBlock(6, 1, 0, Blocks.cobblestone);
		relCoord.setBlock(6, 2, 0, Blocks.cobblestone);
		
		relCoord.setBlockWithNotify(0, 3, 0, Blocks.rail, 0);
		relCoord.setBlockWithNotify(6, 3, 0, Blocks.rail, 0);
		
		for(int layer = -1; layer >= -5; --layer) {
			relCoord.setBlockAndMeta(0, 0, layer, Blocks.stone_stairs, stairs_west);
			relCoord.setBlockAndMeta(6, 0, layer, Blocks.stone_stairs, stairs_east);
		
			relCoord.setBlockAndMeta(0, 1, layer, Blocks.ladder, ladder_west);
			relCoord.setBlockAndMeta(6, 1, layer, Blocks.ladder, ladder_east);
		
			relCoord.setBlock(0, 2, layer, Blocks.cobblestone);
			relCoord.setBlock(6, 2, layer, Blocks.cobblestone);
		
			relCoord.setBlockWithNotify(0, 3, layer, Blocks.rail, 0);
			relCoord.setBlockWithNotify(6, 3, layer, Blocks.rail, 0);
		}

		
		relCoord.setBlock(0, 0, -6, Blocks.cobblestone);
		relCoord.setBlock(0, 1, -6, Blocks.cobblestone);
		relCoord.setBlock(0, 2, -6, Blocks.cobblestone);

		relCoord.setBlock(6, 0, -6, Blocks.cobblestone);
		relCoord.setBlock(6, 1, -6, Blocks.cobblestone);
		relCoord.setBlock(6, 2, -6, Blocks.cobblestone);
		
		relCoord.setBlockAndMeta(1, 0, -6, Blocks.stone_stairs, stairs_north);
		relCoord.setBlockAndMeta(2, 0, -6, Blocks.stone_stairs, stairs_north);
		relCoord.setBlockAndMeta(3, 0, -6, Blocks.stone_stairs, stairs_north);
		relCoord.setBlockAndMeta(4, 0, -6, Blocks.stone_stairs, stairs_north);
		relCoord.setBlockAndMeta(5, 0, -6, Blocks.stone_stairs, stairs_north);
		
		relCoord.setBlockAndMeta(1, 1, -6, Blocks.ladder, ladder_north);
		relCoord.setBlockAndMeta(2, 1, -6, Blocks.ladder, ladder_north);
		relCoord.setBlockAndMeta(3, 1, -6, Blocks.ladder, ladder_north);
		relCoord.setBlockAndMeta(4, 1, -6, Blocks.ladder, ladder_north);
		relCoord.setBlockAndMeta(5, 1, -6, Blocks.ladder, ladder_north);
		
		relCoord.setBlock(1, 2, -6, Blocks.cobblestone);
		relCoord.setBlock(2, 2, -6, Blocks.cobblestone);
		relCoord.setBlock(3, 2, -6, Blocks.cobblestone);
		relCoord.setBlock(4, 2, -6, Blocks.cobblestone);
		relCoord.setBlock(5, 2, -6, Blocks.cobblestone);
		
		relCoord.setBlockWithNotify(0, 3, -6, Blocks.rail, 0);
		relCoord.setBlockWithNotify(1, 3, -6, Blocks.rail, 0);
		relCoord.setBlockWithNotify(2, 3, -6, Blocks.rail, 0);
		relCoord.setBlockAndRotatedMeta(3, 3, -6, Blocks.pumpkin, Stat.META_PUMPKIN_NORTH);
		relCoord.setBlockWithNotify(4, 3, -6, Blocks.rail, 0);
		relCoord.setBlockWithNotify(5, 3, -6, Blocks.rail, 0);
		relCoord.setBlockWithNotify(6, 3, -6, Blocks.rail, 0);
	}
	
}
