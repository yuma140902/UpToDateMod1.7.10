package yuma140902.uptodatemod.util;

public final class ListUtils {
	private ListUtils() {}
	
	public static boolean contains(int[] list, int item) {
		for(int listItem : list) {
			if(listItem == item) return true; 
		}
		return false;
	}
}
