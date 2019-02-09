package yuma140902.uptodatemod.world.generation.woodland_mansion_B;

import java.util.Random;

public final class WoodlandMansionB {
	private WoodlandMansionB() {}
	
	/**
	 * 指定した場所に森の洋館が生成できるかどうかを返します
	 * @param chunkX チャンクのX座標
	 * @param chunkZ チャンクのZ座標
	 */
	public static boolean canSpawnAt(int chunkX, int chunkZ) {
		return chunkX == 0 && chunkZ == 0; //TODO:
	}
	
	/**
	 * 部屋や廊下の配置を決定します。
	 */
	public static WoodlandMansionGrid prepairArrangement(Random rand) {
		WoodlandMansionGrid grid = new WoodlandMansionGrid();
		WoodlandMansionHallway.placeRandomStairsAndHallway(grid, rand);
		placeRandomRooms(grid);
		return grid;
	}
	
	private static void placeRandomRooms(WoodlandMansionGrid grid) {
		//TODO:
	}
	
	public static void generate(WoodlandMansionGrid grid) {
		generateWalls(grid);
		
		IStructureComponentB[] components = grid.getArray();
		for (IStructureComponentB component : components) {
			component.generate();
		}
	}
	
	/**
	 * 壁、天井、土台などを生成します
	 */
	private static void generateWalls(WoodlandMansionGrid grid) {
		//TODO:
	}
}
