package yuma140902.uptodatemod.world.generation;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.util.DirectionUtil;
import yuma140902.uptodatemod.util.Stat;

public abstract class Fossiles {
	public static Fossiles getFossileByType(int type) {
		switch(type) {
			case 0:
				return fossileRibExtraBig;
			case 1:
				return fossileRibBig;
			case 2:
				return fossileRibMedium;
			case 3:
				return fossileRibSmall;
			case 4:
				return fossileHeadSmall;
			case 5:
				return fossileHeadMedium;
			case 6:
				return fossileHeadBig;
			case 7:
				return fossileHeadExtraBig;
			default:
				return fossileRibExtraBig;
		}
	}
	
	private static final int[] facingToMeta = new int[] { 0, 0, 8, 8, 4, 4 };
	
	private static void setBlock(World world, int x, int y, int z, int meta, Random random) {
		if(y == 0) return;
		int rand = random.nextInt(32);
		if(rand == 0) return;
		else if(rand == 1) world.setBlock(x, y, z, Blocks.coal_ore, 0, 2);
		else world.setBlock(x, y, z, MyBlocks.boneBlock, meta, 2);
	}
	
	private static void generatePillar(World world, int startX, int startY, int startZ, int length, int facing, Random random) {
		int meta = facingToMeta[facing];
		
		int offsetX = Facing.offsetsXForSide[facing];
		int offsetY = Facing.offsetsYForSide[facing];
		int offsetZ = Facing.offsetsZForSide[facing];
		
		for(; length > 0; --length) {
			setBlock(world, startX, startY, startZ, meta, random);
			startX += offsetX;
			startY += offsetY;
			startZ += offsetZ;
		}
	}
	
	private static void generateStructure(World world, int startX, int startY, int startZ, int[][][] structure, Random random) {
		for(int offsetY = 0; offsetY < structure.length; ++offsetY) {
			for(int i = 0; i < structure[0].length; ++i) {
				for(int j = 0; j < structure[0][0].length; ++j) {
					int meta = structure[offsetY][i][j];
					if(meta >= 0) {
						setBlock(world, startX + j, startY - offsetY, startZ + i, meta, random);
					}
				}
			}
		}
	}
	
	public abstract void spawnAt(World world, int x, int y, int z, Random random);
	
	private static final Fossiles fossileRibExtraBig = new Fossiles() {
		/*
		 * 一番大きい背骨
		 *                                     : : : : : :
		 * *#*#*#*#*#*#*        ####*####      # # # # # # 
		 *  : : : : : :         :       :      # # # # # #
		 *  : : : : : :         :       :      # # # # # #
		 *  : : : : : :         :       :     *************
		 *  @ @ @ @ @ @          @@   @@       # # # # # # 
		 *                                     # # # # # #
		 *                                     # # # # # #
		 *                                     : : : : : : 
		 */
		
		@Override
		public void spawnAt(World world, int x, int y, int z, Random random) {
			int facing = random.nextInt(4) + 2; //東西南北: 2～5
			int right = DirectionUtil.rightSides[facing];
			int left = DirectionUtil.leftSides[facing];
			
			generatePillar(world, x, y, z, 13, facing, random);  // *を生成
			
			int ribX = x;
			int ribZ = z;
			
			ribX += Facing.offsetsXForSide[facing];
			ribZ += Facing.offsetsZForSide[facing];
			for(int rib = 0; rib < 6; ++rib) {
				int ribXRight = ribX;
				int ribZRight = ribZ;
				generatePillar(world, ribX + Facing.offsetsXForSide[right], y, ribZ + Facing.offsetsZForSide[right], 4, right, random); // #を生成
				ribXRight += Facing.offsetsXForSide[right] * 4;
				ribZRight += Facing.offsetsZForSide[right] * 4;
				generatePillar(world, ribXRight, y - 1, ribZRight, 3, Stat.SIDE_BOTTOM, random); // :を生成
				ribXRight += Facing.offsetsXForSide[left];
				ribZRight += Facing.offsetsZForSide[left];
				generatePillar(world, ribXRight, y - 3 - 1, ribZRight, 2, left, random); // @を生成
				
				int ribXLeft = ribX;
				int ribZLeft = ribZ;
				generatePillar(world, ribX + Facing.offsetsXForSide[left],  y, ribZ + Facing.offsetsZForSide[left],  4, left, random); // #を生成
				ribXLeft += Facing.offsetsXForSide[left] * 4;
				ribZLeft += Facing.offsetsZForSide[left] * 4;
				generatePillar(world, ribXLeft, y - 1, ribZLeft, 3, Stat.SIDE_BOTTOM, random); // :を生成
				ribXLeft += Facing.offsetsXForSide[right];
				ribZLeft += Facing.offsetsZForSide[right];
				generatePillar(world, ribXLeft, y - 3 - 1, ribZLeft, 2, right, random); // @を生成
				
				ribX += Facing.offsetsXForSide[facing] * 2;
				ribZ += Facing.offsetsZForSide[facing] * 2;
			}
			
		}
	};
	
	private static final Fossiles fossileRibBig = new Fossiles() {
		/*
		 * 二番目に大きい背骨
		 *                             # # # # # #
		 * *#*#*#*#*#*#*    ###*###    # # # # # #
		 *  : : : : : :     :     :    # # # # # #
		 *  : : : : : :     :     :   *************
		 *  : : : : : :     :@   @:    # # # # # #
		 *                             # # # # # #
		 *                             # # # # # #
		 */
		
		@Override
		public void spawnAt(World world, int x, int y, int z, Random random) {
			int facing = random.nextInt(4) + 2; //東西南北: 2～5
			int right = DirectionUtil.rightSides[facing];
			int left = DirectionUtil.leftSides[facing];
			
			generatePillar(world, x, y, z, 13, facing, random); // *を生成
			
			int ribX = x;
			int ribZ = z;
			
			ribX += Facing.offsetsXForSide[facing];
			ribZ += Facing.offsetsZForSide[facing];
			for(int rib = 0; rib < 6; ++rib) {
				int ribXRight = ribX;
				int ribZRight = ribZ;
				generatePillar(world, ribX + Facing.offsetsXForSide[right], y, ribZ + Facing.offsetsZForSide[right], 3, right, random); // #を生成
				ribXRight += Facing.offsetsXForSide[right] * 3;
				ribZRight += Facing.offsetsZForSide[right] * 3;
				generatePillar(world, ribXRight, y - 1, ribZRight, 3, Stat.SIDE_BOTTOM, random); // :を生成
				ribXRight += Facing.offsetsXForSide[left];
				ribZRight += Facing.offsetsZForSide[left];
				generatePillar(world, ribXRight, y - 3, ribZRight, 1, left, random); // @を生成
				
				int ribXLeft = ribX;
				int ribZLeft = ribZ;
				generatePillar(world, ribX + Facing.offsetsXForSide[left],  y, ribZ + Facing.offsetsZForSide[left],  3, left, random); // #を生成
				ribXLeft += Facing.offsetsXForSide[left] * 3;
				ribZLeft += Facing.offsetsZForSide[left] * 3;
				generatePillar(world, ribXLeft, y - 1, ribZLeft, 3, Stat.SIDE_BOTTOM, random); // :を生成
				ribXLeft += Facing.offsetsXForSide[right];
				ribZLeft += Facing.offsetsZForSide[right];
				generatePillar(world, ribXLeft, y - 3, ribZLeft, 1, right, random); // @を生成
				
				ribX += Facing.offsetsXForSide[facing] * 2;
				ribZ += Facing.offsetsZForSide[facing] * 2;
			}
		}
	};
	
	private static final Fossiles fossileRibMedium = new Fossiles() {
		/*
		 * 背骨(中)
		 * 
		 *                          : : : : : :
		 * *#*#*#*#*#*#*     #*#    # # # # # #
		 *  : : : : : :     :   :  *************
		 *  : : : : : :     :   :   # # # # # #
		 *  @ @ @ @ @ @      @ @    : : : : : :
		 */
		
		@Override
		public void spawnAt(World world, int x, int y, int z, Random random) {
			int facing = random.nextInt(4) + 2; //東西南北: 2～5
			int right = DirectionUtil.rightSides[facing];
			int left = DirectionUtil.leftSides[facing];
			
			generatePillar(world, x, y, z, 13, facing, random); // *を生成
			
			int ribX = x;
			int ribZ = z;
			
			ribX += Facing.offsetsXForSide[facing];
			ribZ += Facing.offsetsZForSide[facing];
			for(int rib = 0; rib < 6; ++rib) {
				int ribXRight = ribX;
				int ribZRight = ribZ;
				generatePillar(world, ribX + Facing.offsetsXForSide[right], y, ribZ + Facing.offsetsZForSide[right], 1, right, random); // #を生成
				ribXRight += Facing.offsetsXForSide[right] * 2;
				ribZRight += Facing.offsetsZForSide[right] * 2;
				generatePillar(world, ribXRight, y - 1, ribZRight, 2, Stat.SIDE_BOTTOM, random); // :を生成
				ribXRight += Facing.offsetsXForSide[left];
				ribZRight += Facing.offsetsZForSide[left];
				generatePillar(world, ribXRight, y - 2 - 1, ribZRight, 1, left, random); // @を生成
				
				int ribXLeft = ribX;
				int ribZLeft = ribZ;
				generatePillar(world, ribX + Facing.offsetsXForSide[left],  y, ribZ + Facing.offsetsZForSide[left],  1, left, random); // #を生成
				ribXLeft += Facing.offsetsXForSide[left] * 2;
				ribZLeft += Facing.offsetsZForSide[left] * 2;
				generatePillar(world, ribXLeft, y - 1, ribZLeft, 2, Stat.SIDE_BOTTOM, random); // :を生成
				ribXLeft += Facing.offsetsXForSide[right];
				ribZLeft += Facing.offsetsZForSide[right];
				generatePillar(world, ribXLeft, y - 2 - 1, ribZLeft, 1, right, random); // @を生成
				
				ribX += Facing.offsetsXForSide[facing] * 2;
				ribZ += Facing.offsetsZForSide[facing] * 2;
			}
		}
	};
	
	
	private static final Fossiles fossileRibSmall = new Fossiles() {
		/*
		 * 背骨(小)
		 * 
		 * *************    *      : : : : : :
		 *  : : : : : :    : :    *************
		 *  : : : : : :    : :     : : : : : : 
		 */
		
		@Override
		public void spawnAt(World world, int x, int y, int z, Random random) {
			int facing = random.nextInt(4) + 2; //東西南北: 2～5
			int right = DirectionUtil.rightSides[facing];
			int left = DirectionUtil.leftSides[facing];
			
			generatePillar(world, x, y, z, 13, facing, random); // *を生成
			
			int ribX = x;
			int ribZ = z;
			
			ribX += Facing.offsetsXForSide[facing];
			ribZ += Facing.offsetsZForSide[facing];
			for(int rib = 0; rib < 6; ++rib) {
				int ribXRight = ribX;
				int ribZRight = ribZ;
				ribXRight += Facing.offsetsXForSide[right];
				ribZRight += Facing.offsetsZForSide[right];
				generatePillar(world, ribXRight, y - 1, ribZRight, 2, Stat.SIDE_BOTTOM, random); // :を生成
				
				ribX += Facing.offsetsXForSide[facing] * 2;
				ribZ += Facing.offsetsZForSide[facing] * 2;
			}
		}
	};
	
	private static final Fossiles fossileHeadSmall = new Fossiles() {
		
		/*
		 * 頭(小)
		 */
		
		private final int[][][] structure = new int[][][] {
			{
				{ 4,  4,  4,  4},
				{-1,  4,  4, -1},
				{-1,  4,  4, -1},
				{-1, -1, -1, -1}
			},
			{
				{ 0, -1, -1, 0},
				{ 0, -1, -1, 0},
				{ 0, -1, -1, 0},
				{-1,  0,  0, -1}
			},
			{
				{ 0,  4,  4,  0},
				{ 0, -1, -1,  0},
				{ 0, -1, -1,  0},
				{-1,  0,  0, -1}
			},
			{
				{ 0, -1, -1,  0},
				{-1,  4,  4, -1},
				{-1,  4,  4, -1},
				{-1, -1, -1, -1}
			}
		};
		
		@Override
		public void spawnAt(World world, int x, int y, int z, Random random) {
			generateStructure(world, x, y, z, structure, random);
		}
	};
	
	private static final Fossiles fossileHeadMedium = new Fossiles() {
		
		/*
		 * 頭(中)
		 */
		
		private final int[][][] structure = new int[][][] {
			{
				{ 4,  4,  4,  4,  4},
				{-1,  4,  4,  4, -1},
				{-1,  4,  4,  4, -1},
				{-1,  4,  4,  4, -1},
				{-1, -1, -1, -1, -1}
			},
			{
				{ 0, -1,  0, -1,  0},
				{ 8, -1, -1, -1,  8},
				{ 8, -1, -1, -1,  8},
				{ 8, -1, -1, -1,  8},
				{ 0,  4,  4,  4,  0},
			},
			{
				{ 0,  4,  4,  4,  0},
				{ 8, -1, -1, -1,  8},
				{ 8, -1, -1, -1,  8},
				{ 8, -1, -1, -1,  8},
				{ 0,  4,  4,  4,  0},
			},
			{
				{ 0, -1, -1, -1,  0},
				{-1,  4,  4,  4, -1},
				{-1,  4,  4,  4, -1},
				{-1,  4,  4,  4, -1},
				{-1,  4,  4,  4, -1}
			}
		};

		@Override
		public void spawnAt(World world, int x, int y, int z, Random random) {
			generateStructure(world, x, y, z, structure, random);
		}
		
	};
	
	private static final Fossiles fossileHeadBig = new Fossiles() {
		
		private final int[][][] structure = new int[][][] {
			{
				{-1, -1,  4, -1,  4, -1, -1},
				{-1,  4,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4,  4, -1},
				{-1, -1, -1, -1, -1, -1, -1}
			},
			{
				{-1,  0, -1,  0, -1,  0, -1},
				{ 0, -1, -1, -1, -1, -1,  0},
				{ 8, -1, -1, -1, -1, -1,  8},
				{ 0, -1, -1, -1, -1, -1,  0},
				{-1,  0,  4,  4,  4,  0, -1}
			},
			{
				{-1,  4,  4,  4,  4,  4, -1},
				{ 0, -1, -1, -1, -1, -1,  0},
				{ 8, -1, -1, -1, -1, -1,  8},
				{ 0, -1, -1, -1, -1, -1,  0},
				{-1,  0,  4,  4,  4,  0, -1}
			},
			{
				{-1,  0, -1, -1, -1,  0, -1},
				{ 0, -1, -1, -1, -1, -1,  0},
				{ 8, -1, -1, -1, -1, -1,  8},
				{ 0, -1, -1, -1, -1, -1,  0},
				{-1,  0,  4,  4,  4,  0, -1}
			},
			{
				{-1, -1, -1, -1, -1, -1, -1},
				{-1,  4,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4,  4, -1},
				{-1, -1, -1, -1, -1, -1, -1}
			}
		};
		
		@Override
		public void spawnAt(World world, int x, int y, int z, Random random) {
			generateStructure(world, x, y, z, structure, random);
		}
		
	};
	
	private static final Fossiles fossileHeadExtraBig = new Fossiles() {

		private final int[][][] structure = new int[][][] {
			{
				{-1, -1, -1, -1, -1, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1, -1, -1, -1, -1, -1}
			},
			{
				{-1, -1,  4,  4, -1, -1},
				{ 0, -1, -1, -1, -1,  0},
				{ 8, -1, -1, -1, -1,  8},
				{ 8, -1, -1, -1, -1,  8},
				{ 8, -1, -1, -1, -1,  8},
				{ 0, -1, -1, -1, -1,  0},
				{ -1, 0,  4,  4,  0, -1}
			},
			{
				{-1,  0,  0,  0,  0, -1},
				{ 0, -1, -1, -1, -1,  0},
				{ 8, -1, -1, -1, -1,  8},
				{ 8, -1, -1, -1, -1,  8},
				{ 8, -1, -1, -1, -1,  8},
				{ 0, -1, -1, -1, -1,  0},
				{ -1, 0,  4,  4,  0, -1}
			},
			{
				{-1,  0, -1, -1,  0, -1},
				{ 0, -1, -1, -1, -1,  0},
				{ 8, -1, -1, -1, -1,  8},
				{ 8, -1, -1, -1, -1,  8},
				{ 8, -1, -1, -1, -1,  8},
				{ 0, -1, -1, -1, -1,  0},
				{ -1, 0,  4,  4,  0, -1}
			},
			{
				{-1, -1, -1, -1, -1, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1,  4,  4,  4,  4, -1},
				{-1, -1, -1, -1, -1, -1}
			}
		};
		
		@Override
		public void spawnAt(World world, int x, int y, int z, Random random) {
			generateStructure(world, x, y, z, structure, random);
		}
		
	};
}
