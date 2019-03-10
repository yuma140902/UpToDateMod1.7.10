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
	
	private IStructureComponent[] grid = new IStructureComponent[GRID_X_MAX * GRID_Z_MAX];
	
	public IStructureComponent getAt(int gridX, int gridZ) {
		return grid[gridX * GRID_X_MAX + gridZ];
	}
	
	public IStructureComponent[] getArray() {
		return grid;
	}
	
	public void placeAt(int gridX, int gridZ, IStructureComponent component) {
		grid[gridX * GRID_X_MAX + gridZ] = component;
	}
}
