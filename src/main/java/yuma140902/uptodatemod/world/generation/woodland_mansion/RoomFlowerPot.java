package yuma140902.uptodatemod.world.generation.woodland_mansion;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityFlowerPot;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

//1x1_a1
public class RoomFlowerPot implements IStructureComponent {

	@Override
	public void generate(StructureRelativeCoordinateSystem relCoord) {
		relCoord.setBlock(0, 0, 0, Blocks.cobblestone);
		relCoord.setBlock(1, 0, 0, Blocks.cobblestone);
		relCoord.setBlock(5, 0, 0, Blocks.cobblestone);
		relCoord.setBlock(6, 0, 0, Blocks.cobblestone);
		relCoord.setBlock(0, 1, 0, Blocks.cobblestone);
		relCoord.setBlock(1, 1, 0, Blocks.cobblestone);
		relCoord.setBlock(5, 1, 0, Blocks.cobblestone);
		relCoord.setBlock(6, 1, 0, Blocks.cobblestone);
		
		
		relCoord.setBlock(0, 0, -1, Blocks.cobblestone);
		relCoord.setBlock(6, 0, -1, Blocks.cobblestone);
		relCoord.setBlock(0, 1, -1, Blocks.cobblestone);
		relCoord.setBlock(6, 1, -1, Blocks.cobblestone);
		
		
		relCoord.setBlock(0, 0, -2, Blocks.cobblestone);
		relCoord.setBlock(6, 0, -2, Blocks.cobblestone);
		relCoord.setBlock(0, 1, -2, Blocks.cobblestone);
		relCoord.setBlock(6, 1, -2, Blocks.cobblestone);
		
		
		relCoord.setBlock(0, 0, -3, Blocks.cobblestone);
		relCoord.setBlock(6, 0, -3, Blocks.cobblestone);
		relCoord.setBlock(0, 1, -3, Blocks.cobblestone);
		relCoord.setBlock(6, 1, -3, Blocks.cobblestone);
		
		
		relCoord.setBlock(0, 0, -4, Blocks.cobblestone);
		relCoord.setBlock(6, 0, -4, Blocks.cobblestone);
		relCoord.setBlock(0, 1, -4, Blocks.cobblestone);
		relCoord.setBlock(6, 1, -4, Blocks.cobblestone);
		
		
		relCoord.setBlock(0, 0, -5, Blocks.cobblestone);
		relCoord.setBlock(1, 0, -5, Blocks.cobblestone);
		relCoord.setBlock(2, 0, -5, Blocks.cobblestone);
		relCoord.setBlock(3, 0, -5, Blocks.cobblestone);
		relCoord.setBlock(4, 0, -5, Blocks.cobblestone);
		relCoord.setBlock(5, 0, -5, Blocks.cobblestone);
		relCoord.setBlock(6, 0, -5, Blocks.cobblestone);
		
		relCoord.setBlockAndRotatedMeta(0, 1, -5, Blocks.stone_stairs, Stat.META_STAIRS_WEST);
		relCoord.setBlockAndRotatedMeta(6, 1, -5, Blocks.stone_stairs, Stat.META_STAIRS_EAST);
		
		Item itemRedFlower = Item.getItemFromBlock(Blocks.red_flower);
		relCoord.setBlock(1, 1, -5, Blocks.flower_pot);
		relCoord.setTileEntity(1, 1, -5, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_ALLIUM));
		relCoord.setBlock(2, 1, -5, Blocks.flower_pot);
		relCoord.setTileEntity(2, 1, -5, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_HOUSTONIA));
		relCoord.setBlock(3, 1, -5, Blocks.flower_pot);
		relCoord.setTileEntity(3, 1, -5, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_ALLIUM));
		relCoord.setBlock(4, 1, -5, Blocks.flower_pot);
		relCoord.setTileEntity(4, 1, -5, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_BLUE_ORCHID));
		relCoord.setBlock(5, 1, -5, Blocks.flower_pot);
		relCoord.setTileEntity(5, 1, -5, new TileEntityFlowerPot(itemRedFlower, Stat.META_REDFLOWER_OXEYE_DAISY));
		
		
		relCoord.setBlock(0, 0, -6, Blocks.cobblestone);
		relCoord.setBlock(6, 0, -6, Blocks.cobblestone);
		relCoord.setBlock(0, 1, -6, Blocks.cobblestone);
		relCoord.setBlock(6, 1, -6, Blocks.cobblestone);
		
		int north_stairs_meta = relCoord.getRotatedStairsMeta(Stat.META_STAIRS_NORTH);
		relCoord.setBlockAndMeta(1, 1, -6, Blocks.stone_stairs, north_stairs_meta);
		relCoord.setBlockAndMeta(2, 1, -6, Blocks.stone_stairs, north_stairs_meta);
		relCoord.setBlockAndMeta(3, 1, -6, Blocks.stone_stairs, north_stairs_meta);
		relCoord.setBlockAndMeta(4, 1, -6, Blocks.stone_stairs, north_stairs_meta);
		relCoord.setBlockAndMeta(5, 1, -6, Blocks.stone_stairs, north_stairs_meta);
		
		relCoord.setBlock(0, 2, -6, Blocks.cobblestone_wall);
		relCoord.setBlock(0, 3, -6, Blocks.cobblestone_wall);
		relCoord.setBlock(0, 4, -6, Blocks.cobblestone_wall);
		relCoord.setBlock(0, 5, -6, Blocks.cobblestone_wall);
		relCoord.setBlock(0, 6, -6, Blocks.cobblestone_wall);

		relCoord.setBlock(6, 2, -6, Blocks.cobblestone_wall);
		relCoord.setBlock(6, 3, -6, Blocks.cobblestone_wall);
		relCoord.setBlock(6, 4, -6, Blocks.cobblestone_wall);
		relCoord.setBlock(6, 5, -6, Blocks.cobblestone_wall);
		relCoord.setBlock(6, 6, -6, Blocks.cobblestone_wall);
	}
	
}
