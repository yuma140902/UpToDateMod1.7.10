package yuma140902.uptodatemod.world.generation.woodland_mansion_B;

public class WoodlandMansionGrid {
	public static final int GRID_X_MAX = 19, GRID_Z_MAX = 15;
	
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
