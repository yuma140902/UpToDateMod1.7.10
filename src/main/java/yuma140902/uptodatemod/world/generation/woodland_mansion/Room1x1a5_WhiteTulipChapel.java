package yuma140902.uptodatemod.world.generation.woodland_mansion;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityFlowerPot;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

public class Room1x1a5_WhiteTulipChapel implements IStructureComponent {
	
	@Override
	public String getName() {
		return "1x1_a5";
	}
	
	@Override
	public void generate(StructureRelativeCoordinateSystem relCoord) {
		int stairsSouth = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_SOUTH);
		int stairsLeft = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_EAST);
		int stairsRight = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_WEST);
		int stairsNorthUpper = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_NORTH | Stat.META_STAIRS_UPPER);
		
		// 手前左の階段
		relCoord.setBlock(1, 0, 0, Blocks.cobblestone);
		relCoord.setBlock(1, 1, 0, Blocks.cobblestone);
		relCoord.setBlock(1, 0, -1, Blocks.cobblestone);
		relCoord.setBlockAndMeta(1, 1, -1, Blocks.stone_stairs, stairsSouth);
		relCoord.setBlockAndMeta(1, 0, -2, Blocks.stone_stairs, stairsSouth);
		
		// 手前右の階段
		relCoord.setBlock(5, 0, 0, Blocks.cobblestone);
		relCoord.setBlock(5, 1, 0, Blocks.cobblestone);
		relCoord.setBlock(5, 0, -1, Blocks.cobblestone);
		relCoord.setBlockAndMeta(5, 1, -1, Blocks.stone_stairs, stairsSouth);
		relCoord.setBlockAndMeta(5, 0, -2, Blocks.stone_stairs, stairsSouth);
		
		// 左側、右側の壁
		for(int z = 0; z >= -6; --z) {
			relCoord.setBlock(0, 0, z, Blocks.cobblestone);
			relCoord.setBlock(0, 1, z, Blocks.cobblestone);
			relCoord.setBlock(6, 0, z, Blocks.cobblestone);
			relCoord.setBlock(6, 1, z, Blocks.cobblestone);
		}
		
		// 奥にある台
		relCoord.setBlockAndMeta(2, 0, -4, Blocks.stone_slab, Stat.META_SLAB_COBBLESTONE);
		relCoord.setBlockAndMeta(3, 0, -4, Blocks.stone_slab, Stat.META_SLAB_COBBLESTONE);
		relCoord.setBlockAndMeta(4, 0, -4, Blocks.stone_slab, Stat.META_SLAB_COBBLESTONE);
		relCoord.setBlockAndMeta(2, 0, -5, Blocks.stone_slab, Stat.META_SLAB_COBBLESTONE);
		relCoord.setBlockAndMeta(3, 0, -5, Blocks.stone_slab, Stat.META_SLAB_COBBLESTONE);
		relCoord.setBlockAndMeta(4, 0, -5, Blocks.stone_slab, Stat.META_SLAB_COBBLESTONE);
		
		// 奥の壁
		relCoord.setBlock(1, 0, -6, Blocks.cobblestone);
		relCoord.setBlock(1, 1, -6, Blocks.cobblestone);
		relCoord.setBlockAndMeta(1, 2, -6, Blocks.stone_stairs, stairsLeft);
		
		relCoord.setBlock(2, 0, -6, Blocks.cobblestone);
		relCoord.setBlock(2, 1, -6, Blocks.cobblestone);
		relCoord.setBlock(2, 2, -6, Blocks.cobblestone);
		relCoord.setBlockAndMeta(2, 3, -6, Blocks.stone_stairs, stairsLeft);
		
		relCoord.setBlock(3, 0, -6, Blocks.cobblestone);
		{
			relCoord.setBlock(3, 1, -6, Blocks.flower_pot);
			Item itemRedFlower = Item.getItemFromBlock(Blocks.red_flower);
			relCoord.setTileEntity(3, 1, -6, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_WHITE_TULIP));
		}
		relCoord.setBlockAndMeta(3, 2, -6, Blocks.stone_stairs, stairsNorthUpper);
		relCoord.setBlock(3, 3, -6, Blocks.cobblestone);
		
		relCoord.setBlock(4, 0, -6, Blocks.cobblestone);
		relCoord.setBlock(4, 1, -6, Blocks.cobblestone);
		relCoord.setBlock(4, 2, -6, Blocks.cobblestone);
		relCoord.setBlockAndMeta(4, 3, -6, Blocks.stone_stairs, stairsRight);
		
		relCoord.setBlock(5, 0, -6, Blocks.cobblestone);
		relCoord.setBlock(5, 1, -6, Blocks.cobblestone);
		relCoord.setBlockAndMeta(5, 2, -6, Blocks.stone_stairs, stairsRight);
	}
	
}
