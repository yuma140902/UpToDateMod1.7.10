package yuma140902.yumalib.api.blockstate;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import yuma140902.yumalib.api.McConst;

/**
 * トラップドアのメタデータ
 */
public class GenericTrapdoorState implements IBlockState {
	//メタデータの仕様
	/*
	 * 2進数で4桁
	 * 0b0000
	 *
	 * 下位1,2桁目 : 方角
	 * 0b**00 : south
	 * 0b**01 : north
	 * 0b**10 : east
	 * 0b**11 : west
	 *
	 * 下位3桁目 : 開いているか否か
	 * 0b*0** : 閉じている CLOSE
	 * 0b*1** : 開いている OPEN
	 *
	 * 下位4桁目 : 上側に付いているか否か
	 * 0b0*** : 下側
	 * 0b1*** : 上側
	 */
	
	protected final Orientation orientation;
	protected final boolean     isOpen;
	protected final boolean     isUpper;
	
	private GenericTrapdoorState(Orientation orientation, boolean isOpen, boolean isUpper){
		this.orientation = orientation;
		this.isOpen = isOpen;
		this.isUpper = isUpper;
	}
	
	public static GenericTrapdoorState fromMetadata(int metadata){
		Orientation orientation = Orientation.values()[metadata & 0b0011];
		boolean isOpen = (metadata & 0b0100) != 0;
		boolean isUpper = (metadata & 0b1000) != 0;
		return new GenericTrapdoorState(orientation, isOpen, isUpper);
	}
	
	public GenericTrapdoorState(int hitSide, float hitY) {
		this.orientation = Orientation.fromSide(hitSide);
		this.isOpen = false;
		if(hitSide != McConst.SIDE_TOP && hitSide != McConst.SIDE_BOTTOM && hitY > 0.5F){
			this.isUpper = true;
		}
		else if(hitSide == McConst.SIDE_TOP){
			this.isUpper = false;
		}
		else if(hitSide == McConst.SIDE_BOTTOM){
			this.isUpper = true;
		}
		else {
			this.isUpper = false;
		}
	}
	
	public GenericTrapdoorState rotate(EntityLivingBase placer){
		int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		Orientation orientation =
						  l == 0 ? Orientation.SOUTH
						: l == 1 ? Orientation.WEST
						: l == 2 ? Orientation.NORTH
						: Orientation.EAST;
		return new GenericTrapdoorState(orientation, this.isOpen, this.isUpper);
	}
	
	public Orientation orientation(){
		return this.orientation;
	}
	
	public boolean isOpen(){
		return this.isOpen;
	}
	
	public boolean isUpper(){
		return this.isUpper;
	}
	
	@Override public int metadata() {
		return (orientation.ordinal() & 0b0011) | (isOpen ? 0b0100 : 0) | (isUpper ? 0b1000 : 0);
	}
	
	public enum Orientation {
		SOUTH,
		NORTH,
		EAST,
		WEST;
		
		public static boolean isSameSide(int side, Orientation orientation) {
			return   (side == McConst.SIDE_SOUTH && orientation == SOUTH)
						|| (side == McConst.SIDE_NORTH && orientation == NORTH)
						|| (side == McConst.SIDE_EAST && orientation == EAST)
						|| (side == McConst.SIDE_WEST && orientation == WEST);
		}
		
		public static boolean isOppositeSide(int side, Orientation orientation) {
			return   (side == McConst.SIDE_NORTH && orientation == SOUTH)
						|| (side == McConst.SIDE_SOUTH && orientation == NORTH)
						|| (side == McConst.SIDE_WEST && orientation == EAST)
						|| (side == McConst.SIDE_EAST && orientation == WEST);
		}
		
		public static Orientation fromSide(int side){
			return    side == McConst.SIDE_SOUTH ? NORTH
							: side == McConst.SIDE_NORTH ? SOUTH
							: side == McConst.SIDE_EAST ? WEST
							: side == McConst.SIDE_WEST ? EAST
							: SOUTH;
		}
	}
}
