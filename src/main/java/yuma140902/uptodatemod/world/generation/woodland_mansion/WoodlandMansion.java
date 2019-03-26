package yuma140902.uptodatemod.world.generation.woodland_mansion;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import yuma140902.uptodatemod.world.generation.structure.Rotation2D;
import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

public final class WoodlandMansion {
	private WoodlandMansion() {}
	
	/**
	 * 指定した場所に森の洋館が生成できるかどうかを返します
	 * @param chunkX チャンクのX座標
	 * @param chunkZ チャンクのZ座標
	 */
	public static boolean canSpawnAt(int chunkX, int chunkZ) {
		return chunkX == 0 && chunkZ == 0; //TODO:
	}
	
	/**
	 * worldの座標x, zにおける地面の高さを求める
	 */
	private static int getBaseHeight(World world, int x, int z) {
		for(int y = 255; y >= 0; --y) {
			Block block = world.getBlock(x, y, z);
			if(block.isOpaqueCube()) return y + 1;
		}
		return 1;
	}
	
	/**
	 * 部屋や廊下の配置を決定します。
	 */
	public static WoodlandMansionGrid prepairArrangement(World world, Random rand, int chunkX, int chunkZ) {
		int x = chunkX * 16 + 8;
		int z = chunkZ * 16 + 8;
		int y = getBaseHeight(world, x, z);
		
		WoodlandMansionGrid grid = new WoodlandMansionGrid(world, rand, x, y, z);
		WoodlandMansionHallway.placeRandomStairsAndHallway(grid, rand);
		placeRandomRooms(grid);
		return grid;
	}
	
	private static void placeRandomRooms(WoodlandMansionGrid grid) {
		//TODO:
	}
	
	public static void generate(WoodlandMansionGrid grid) {
		test(grid);
		
		//TODO:
//		generateWalls(grid);
//		
//		IStructureComponentB[] components = grid.getArray();
//		for (IStructureComponentB component : components) {
//			if(component != null) 	component.generate();
//		}
	}
	
	private static void test(WoodlandMansionGrid grid) {
		int y = grid.getYCoord();
		World world = grid.getWorld();
		
		new Room1x1a3_Office().generate(new StructureRelativeCoordinateSystem(0, y, 0, Rotation2D.DEG0, world));
		new StructureRelativeCoordinateSystem(0, y, 0, Rotation2D.DEG0, world).setBlock(0, 9, 0, Blocks.coal_block);
		
		new Room1x1a3_Office().generate(new StructureRelativeCoordinateSystem(16, y, 0, Rotation2D.DEG90, world));
		new StructureRelativeCoordinateSystem(16, y, 0, Rotation2D.DEG90, world).setBlock(0, 9, 0, Blocks.iron_block);
		
		new Room1x1a3_Office().generate(new StructureRelativeCoordinateSystem(16, y, 16, Rotation2D.DEG180, world));
		new StructureRelativeCoordinateSystem(16, y, 16, Rotation2D.DEG180, world).setBlock(0, 9, 0, Blocks.redstone_block);
		
		new Room1x1a3_Office().generate(new StructureRelativeCoordinateSystem(0, y, 16, Rotation2D.DEG270, world));
		new StructureRelativeCoordinateSystem(0, y, 16, Rotation2D.DEG270, world).setBlock(0, 9, 0, Blocks.diamond_block);
		
	}
	
	/**
	 * 壁、天井、土台などを生成します
	 */
	private static void generateWalls(WoodlandMansionGrid grid) {
		//TODO:
		
	}
}
