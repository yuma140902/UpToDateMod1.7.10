package yuma140902.uptodatemod.world.generation.woodland_mansion;

import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

public interface IStructureComponentB {
	
	void generate(StructureRelativeCoordinateSystem relCoord);
	
	public static class InnerEmpty implements IStructureComponentB {
		@Override
		public void generate(StructureRelativeCoordinateSystem relCoord) {}
	}
	
	public static class OuterEmpty implements IStructureComponentB {
		@Override
		public void generate(StructureRelativeCoordinateSystem relCoord) {}
	}
}
