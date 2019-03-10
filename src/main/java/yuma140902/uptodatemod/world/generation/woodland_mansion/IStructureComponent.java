package yuma140902.uptodatemod.world.generation.woodland_mansion;

import yuma140902.uptodatemod.world.generation.structure.StructureRelativeCoordinateSystem;

public interface IStructureComponent {
	
	void generate(StructureRelativeCoordinateSystem relCoord);
	
	String getName();
	
	public static class InnerEmpty implements IStructureComponent {
		@Override
		public void generate(StructureRelativeCoordinateSystem relCoord) {}
		
		@Override
		public String getName() {
			return "InnerEmpty";
		}
	}
	
	public static class OuterEmpty implements IStructureComponent {
		@Override
		public void generate(StructureRelativeCoordinateSystem relCoord) {}
		
		@Override
		public String getName() {
			return "OuterEmpty";
		}
	}
}
