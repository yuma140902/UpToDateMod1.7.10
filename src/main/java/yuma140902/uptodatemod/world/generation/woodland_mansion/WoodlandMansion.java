package yuma140902.uptodatemod.world.generation.woodland_mansion;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import yuma140902.uptodatemod.world.generation.structure.IStructureComponent;

public class WoodlandMansion {
	private static final int GRID_WIDTH_MAX = 15;
	private static final int GRID_LENGTH_MAX = 19;
	
	static final int ROOM_LENGTH = 7;
	static final int ROOM_HEIGHT = 7;
	static final int ROOF_HEIGHT = 4;
	
	private static enum ComponentType {
		room, wall, pillar
	}
	
	/** 1階の部屋、壁、柱 */
	private IStructureComponent[] gridFirstFloor = new IStructureComponent[GRID_WIDTH_MAX * GRID_LENGTH_MAX];
	/** 2階の部屋、壁、柱 */
	private IStructureComponent[] gridSecondFloor = new IStructureComponent[GRID_WIDTH_MAX * GRID_LENGTH_MAX];
	/** 2階と3階の間の屋根 */
	private IStructureComponent[] gridRoof1 = new IStructureComponent[GRID_WIDTH_MAX * GRID_LENGTH_MAX];
	/** 3階の部屋、壁、柱 */
	private IStructureComponent[] gridThirdloor = new IStructureComponent[GRID_WIDTH_MAX * GRID_LENGTH_MAX];
	/** 3階の上の屋根 */
	private IStructureComponent[] gridRoof2 = new IStructureComponent[GRID_WIDTH_MAX * GRID_LENGTH_MAX];
	
	private static ComponentType getComponentTypeAt(int i1, int i2) {
		// 奇偶が別なら壁
		if((i1 + i2) % 2 == 1) return ComponentType.wall;
		// 両方奇数なら部屋
		else if((i1 * i2) % 2 == 1) return ComponentType.room;
		// 両方偶数なら柱
		else return ComponentType.pillar;
	}
	
	private static int getLength(int i) {
		if(i % 2 == 0) return 1;
		else return ROOM_LENGTH;
	}
	
	/**
	 * worldの座標x, zにおける地面の高さを求める
	 */
	private static int getBaseHeight(World world, int x, int z) {
		for(int y = 255; y >= 0; --y) {
			Block block = world.getBlock(x, y, z);
			if(block.isOpaqueCube()) return y;
		}
		return 1;
	}
	
	/**
	 * 上下に部屋などがあるかどうかを判断して適切な屋根オブジェクトを返します。2階と3階の間の屋根用です
	 * @param world 屋根オブジェクトの初期化に使用される
	 * @param rand 屋根オブジェクトの初期化に使用される
	 * @param x 屋根オブジェクトの初期化に使用される
	 * @param y 屋根オブジェクトの初期化に使用される
	 * @param z 屋根オブジェクトの初期化に使用される
	 * @param i1 インデックス
	 * @param i2 インデックス
	 * @return 適切な屋根オブジェクト
	 */
	private IStructureComponent getAppropriateRoof1(World world, Random rand, int x, int y, int z, int i1, int i2) {
		int index = i1 * GRID_WIDTH_MAX + i2;
		
		if(gridSecondFloor[index] == null) return null;
		
		ComponentType type = getComponentTypeAt(i1, i2);
		switch(type) {
			case room:
				return gridThirdloor[index] == null ? new ComponentRoofRoomOutside(world, rand, x, y, z) : new ComponentRoofRoomRoom(world, rand, x, y, z);
			case wall:
				return gridThirdloor[index] == null ? new ComponentRoofWallOutside(world, rand, x, y, z) : new ComponentRoofWallWall(world, rand, x, y, z);
			case pillar:
				return gridThirdloor[index] == null ? new ComponentRoofPillarOutside(world, rand, x, y, z) : new ComponentRoofPillarPillar(world, rand, x, y, z);
			default:
				return null;
		}
	}
	
	/**
	 * 下に部屋などがあるかどうかを判断して適切な屋根オブジェクトを返します。3階の上の屋根用です
	 * @param world 屋根オブジェクトの初期化に使用される
	 * @param rand 屋根オブジェクトの初期化に使用される
	 * @param x 屋根オブジェクトの初期化に使用される
	 * @param y 屋根オブジェクトの初期化に使用される
	 * @param z 屋根オブジェクトの初期化に使用される
	 * @param i1 インデックス
	 * @param i2 インデックス
	 * @return 適切な屋根オブジェクト
	 */
	private IStructureComponent getAppropriateRoof2(World world, Random rand, int x, int y, int z, int i1, int i2) {
		int index = i1 * GRID_WIDTH_MAX + i2;
		
		if(gridThirdloor[index] == null) return null;
		
		ComponentType type = getComponentTypeAt(i1, i2);
		switch(type) {
			case room:
				return new ComponentRoofRoomOutside(world, rand, x, y, z);
			case wall:
				return new ComponentRoofWallOutside(world, rand, x, y, z);
			case pillar:
				return new ComponentRoofPillarOutside(world, rand, x, y, z);
			default:
				return null;
		}
	}
	
	/**
	 * ランダムな部屋を返します。
	 * @param world
	 * @param rand
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private static IStructureComponent getRandomRoom(World world, Random rand, int x, int y, int z) {
		return new ComponentRoomFlowerPot(world, rand, x , y, z);
	}
	
	/**
	 * 森の洋館を生成します
	 * @param world
	 * @param rand
	 * @param chunkX
	 * @param chunkZ
	 */
	public void generateAt(World world, Random rand, int chunkX, int chunkZ) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		int y = getBaseHeight(world, x, z);
		
		for(int i = 0; i < GRID_WIDTH_MAX; ++i) {
			for(int j = 0; j < GRID_LENGTH_MAX; ++j) {
				int index = i*GRID_WIDTH_MAX + j;
				ComponentType type = getComponentTypeAt(i, j);
				switch(type) {
					case room:
						gridFirstFloor[index] = getRandomRoom(world, rand, x, y, z);
						gridSecondFloor[index] = getRandomRoom(world, rand, x, y + ROOM_HEIGHT, z);
						gridThirdloor[index] = getRandomRoom(world, rand, x, y + ROOM_HEIGHT + ROOM_HEIGHT + ROOF_HEIGHT, z);
						x += ROOM_LENGTH;
						break;
					case wall:
						//gridFirstFloor[index] = get
				}
				gridRoof1[index] = getAppropriateRoof1(world, rand, x, y + ROOM_HEIGHT + ROOM_HEIGHT, z, i, j);
				gridRoof2[index] = getAppropriateRoof2(world, rand, x, y + ROOM_HEIGHT + ROOM_HEIGHT + ROOF_HEIGHT + ROOM_HEIGHT, z, i, j);
			}
			
			z += getLength(i);
		}
		//TODO:
	}
	
	/**
	 * 森の洋館が指定座標に生成できるか確認します
	 * @param chunkX
	 * @param chunkZ
	 * @return
	 */
	public static boolean canSpawnAt(int chunkX, int chunkZ) {
		return chunkX == 0 && chunkZ == 0; // TODO:
	}
}
