package yuma140902.uptodatemod.world.generation.woodland_mansion;

import java.util.Random;
import net.minecraft.world.World;

public class WoodlandMansionGrid {
	public static final int GRID_X_MAX = 19, GRID_Z_MAX = 15;
	
	public WoodlandMansionGrid(World world, Random rand, int xCoord, int yCoord, int zCoord) {
		this.world = world;
		this.random = rand;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
	}
	
	private int xCoord, yCoord, zCoord;
	private World world;
	private Random random;
	
	public int getXCoord() {
		return xCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}
	
	public int getZCoord() {
		return zCoord;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Random getRand() {
		return random;
	}
	
	private IStructureComponentB[] grid = new IStructureComponentB[GRID_X_MAX * GRID_Z_MAX];
	
	public IStructureComponentB getAt(int gridX, int gridZ) {
		return grid[gridX * GRID_X_MAX + gridZ];
	}
	
	public IStructureComponentB[] getArray() {
		return grid;
	}
	
	public void placeAt(int gridX, int gridZ, IStructureComponentB component) {
		grid[gridX * GRID_X_MAX + gridZ] = component;
	}
}
