package yuma140902.yumalib.api.blockstate;

/**
 * {@link net.minecraft.block.BlockRotatedPillar}の状態
 * @param <T> ブロックの種類を表すEnum。最大4種類まで
 */
public class VanillaRotatedPillarState<T extends Enum<T>> implements IBlockState {
	
	/*
	 * メタデータの仕様
	 *
	 * 下位2ビットはブロックの種類、4種類まで
	 * 0b**00
	 * 0b**01
	 * 0b**10
	 * 0b**11
	 *
	 * 上位2ビットは原木の軸
	 * 0b00**: 上下
	 * 0b01**: 東西
	 * 0b10**: 南北
	 * 0b11**: すべて同じテクスチャ
	 */
	
	protected final T blockType;
	protected final Axis axis;
	protected final BlockTypeProvider<T> provider;
	
	public VanillaRotatedPillarState(T blockType, Axis axis, Class<T> cls){
		this(blockType, axis, new BlockTypeProvider<>(cls));
	}
	
	protected VanillaRotatedPillarState(T blockType, Axis axis, BlockTypeProvider<T> blockTypeProvider){
		this.blockType = blockType;
		this.axis = axis;
		this.provider = blockTypeProvider;
	}
	
	public static <T extends Enum<T>> VanillaRotatedPillarState<T> fromHitSide(int hitSide, T blockType, Class<T> cls){
		return fromHitSide(hitSide, blockType, new BlockTypeProvider<>(cls));
	}
	
	protected static <T extends Enum<T>> VanillaRotatedPillarState<T> fromHitSide(int hitSide, T blockType, BlockTypeProvider<T> blockTypeProvider) {
		Axis axis;
		switch (hitSide){
			case 0: case 1:
				axis = Axis.UP_DOWN;
				break;
			case 2: case 3:
				axis = Axis.NORTH_SOUTH;
				break;
			case 4: case 5:
				axis = Axis.EAST_WEST;
				break;
			default:
				axis = Axis.UP_DOWN;
				break;
		}
		return new VanillaRotatedPillarState<>(blockType, axis, blockTypeProvider);
	}
	
	public static <T extends Enum<T>> VanillaRotatedPillarState<T> fromMetadata(int meta, Class<T> cls){
		return fromMetadata(meta, new BlockTypeProvider<>(cls));
	}
	
	protected static <T extends Enum<T>> VanillaRotatedPillarState<T> fromMetadata(int meta, BlockTypeProvider<T> blockTypeProvider){
		T blockType = blockTypeProvider.getBlockType(meta & 0b0011);
		Axis axis = Axis.fromMetadata(meta);
		return new VanillaRotatedPillarState<>(blockType, axis, blockTypeProvider);
	}
	
	public VanillaRotatedPillarState<T> blockTypeChanged(T blockType){
		return new VanillaRotatedPillarState<T>(blockType, this.axis, this.provider);
	}
	
	public T blockType(){
		return this.blockType;
	}
	
	public Axis axis(){
		return this.axis;
	}
	
	@Override public int metadata() {
		return this.provider.getBit(this.blockType) | this.axis.bit;
	}
	
	/**
	 * ブロックの種類(下位2ビット)を使わないときにダミーとして利用するブロックタイプ
	 */
	public enum NoBlockType {
		NONE
	}
	
	/**
	 * ブロックの種類を表す汎用のブロックタイプ
	 */
	public enum GenericBlockType {
		T1,
		T2,
		T3,
		T4
	}
	
	/**
	 * ブロックの軸
	 */
	public enum Axis {
		UP_DOWN((byte)0b0000),
		EAST_WEST((byte)0b0100),
		NORTH_SOUTH((byte)0b1000),
		NO_AXIS((byte)0b1100);
		
		private final byte bit;
		Axis(byte bit){
			this.bit = bit;
		}
		
		public static Axis fromMetadata(int meta){
			int axisBit = (meta & 0b1100);
			for(final Axis axis : Axis.values()){
				if(axis.bit == axisBit) return axis;
			}
			return UP_DOWN;
		}
	}
	
	
	private static class BlockTypeProvider<T extends Enum<T>> {
		
		private final T[] values;
		
		public BlockTypeProvider(Class<T> cls){
			this.values = cls.getEnumConstants();
			if(this.values.length == 0) throw new IllegalArgumentException("No block types. Try using NoBlockType.NONE");
			if(this.values.length >= 5) throw new IllegalArgumentException("Too many block types");
		}
		
		public T getBlockType(int bit) {
			return this.values[bit % this.values.length];
		}
		
		public int getBit(T blockType){
			return blockType.ordinal();
		}
	}
	
}
