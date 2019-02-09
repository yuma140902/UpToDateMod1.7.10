package yuma140902.uptodatemod.world.generation.structure;

import java.util.Random;
import net.minecraft.world.World;

public abstract class StructureComponentBase implements IStructureComponent {
	protected World world;
	protected Random random;
	protected int xCoord;
	protected int yCoord;
	protected int zCoord;
	
	public StructureComponentBase(World world, Random rand, int x, int y, int z) {
		this.world = world;
		this.random = rand;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
	}
}
