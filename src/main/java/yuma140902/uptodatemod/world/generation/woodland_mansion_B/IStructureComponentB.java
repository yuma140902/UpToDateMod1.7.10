package yuma140902.uptodatemod.world.generation.woodland_mansion_B;

public interface IStructureComponentB {
	
	void generate();
	
	public static class InnerEmpty implements IStructureComponentB {
		@Override
		public void generate() {}
	}
	
	public static class OuterEmpty implements IStructureComponentB {
		@Override
		public void generate() {}
	}
}
