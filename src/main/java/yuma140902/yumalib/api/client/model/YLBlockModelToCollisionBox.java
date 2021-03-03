package yuma140902.yumalib.api.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * {@link YLBlockModel} から当たり判定します
 */
public class YLBlockModelToCollisionBox {
	
	/**
	 * {@link net.minecraft.block.Block#addCollisionBoxesToList(World, int, int, int, AxisAlignedBB, List, Entity)} から呼び出す
	 */
	public static void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, YLBlockModel model){
		for(final YLBlockModel.Element element : model.getElements()){
			AxisAlignedBB aabb = newAABBFromElement(x, y, z, element);
			addIfIntersects(aabb, mask, list);
		}
	}
	
	public static AxisAlignedBB newAABBFromElement(int x, int y, int z, YLBlockModel.Element element){
		double xFrom = x + element.getFromX() / 16f;
		double xTo = x + element.getToX() / 16f;
		double yFrom = y + element.getFromY() / 16f;
		double yTo = y + element.getToY() / 16f;
		double zFrom = z + element.getFromZ() / 16f;
		double zTo = z + element.getToZ() / 16f;
		return AxisAlignedBB.getBoundingBox(xFrom, yFrom, zFrom, xTo, yTo, zTo);
	}
	
	private static void addIfIntersects(AxisAlignedBB aabb, AxisAlignedBB mask, List list) {
		if (aabb != null && mask.intersectsWith(aabb)) {
			list.add(aabb);
		}
	}
}
