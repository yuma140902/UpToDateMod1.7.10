package yuma140902.uptodatemod.world.generation.woodland_mansion;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityFlowerPot;
import yuma140902.uptodatemod.util.ColorUtil;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

//事務所 1x1_a3
public class RoomOffice implements IStructureComponent {

	@Override
	public void generate(StructureRelativeCoordinateSystem relCoord) {
		int south_stairs = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_SOUTH);
		int north_upper_stairs = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_NORTH | Stat.META_STAIRS_UPPER);
		int west_upper_stairs = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_WEST | Stat.META_STAIRS_UPPER);
		int east_upper_stairs = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_EAST | Stat.META_STAIRS_UPPER);
		int east_stairs = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_EAST);
		Item itemRedFlower = Item.getItemFromBlock(Blocks.red_flower);
		
		
		// 西側の机と椅子
		
		relCoord.setBlockAndMeta(1, 0, -1, Blocks.oak_stairs, south_stairs);
		
		
		relCoord.setBlockAndMeta(0, 0, -2, Blocks.stone_stairs, north_upper_stairs);
		relCoord.setBlockAndMeta(1, 0, -2, Blocks.stone_stairs, north_upper_stairs);
		relCoord.setBlockAndMeta(1, 1, -2, Blocks.carpet, ColorUtil.META_LIGHT_GRAY);
		
		
		relCoord.setBlock(0, 0, -3, Blocks.cobblestone);
		relCoord.setBlockAndMeta(1, 0, -3, Blocks.stone_stairs, west_upper_stairs);
		relCoord.setBlock(0, 1, -3, Blocks.flower_pot);
		relCoord.setTileEntity(0, 1, -3, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_RED_TULIP));
		
		
		relCoord.setBlock(0, 0, -4, Blocks.cobblestone);
		relCoord.setBlockAndMeta(1, 0, -4, Blocks.stone_stairs, west_upper_stairs);
		relCoord.setBlockAndMeta(1, 1, -4, Blocks.carpet, ColorUtil.META_LIGHT_GRAY);
		relCoord.setBlockAndMeta(2, 0, -4, Blocks.oak_stairs, east_stairs);
		
		
		relCoord.setBlock(0, 0, -5, Blocks.cobblestone);
		relCoord.setBlockAndMeta(1, 0, -5, Blocks.stone_stairs, west_upper_stairs);
		relCoord.setBlock(0, 1, -5, Blocks.flower_pot);
		relCoord.setTileEntity(0, 1, -5, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_RED_TULIP));
		
		
		relCoord.setBlock(0, 0, -6, Blocks.cobblestone);
		relCoord.setBlockAndMeta(1, 0, -6, Blocks.stone_stairs, west_upper_stairs);
		
		
		// 東側の机と椅子
		
		relCoord.setBlock(6, 0, -3, Blocks.planks);
		relCoord.setBlockAndMeta(6, 1, -3, Blocks.oak_stairs, south_stairs);
		
		
		relCoord.setBlockAndMeta(5, 1, -4, Blocks.stone_stairs, east_upper_stairs);
		relCoord.setBlockAndMeta(6, 1, -4, Blocks.stone_stairs, north_upper_stairs);
		relCoord.setBlockAndMeta(6, 2, -4, Blocks.carpet, ColorUtil.META_LIGHT_GRAY);
		
		
		relCoord.setBlockAndMeta(5, 1, -5, Blocks.stone_stairs, east_upper_stairs);
		relCoord.setBlock(6, 1, -5, Blocks.cobblestone);
		relCoord.setBlock(6, 2, -5, Blocks.flower_pot);
		relCoord.setTileEntity(6, 2, -5, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_RED_TULIP));
		
		
		relCoord.setBlockAndMeta(5, 1, -6, Blocks.stone_stairs, east_upper_stairs);
		relCoord.setBlock(6, 1, -6, Blocks.cobblestone);
	}
	
}
