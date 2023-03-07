package yuma140902.uptodatemod.util;

import java.util.List;

/**
 * @deprecated use scala instead
 */
@Deprecated
public final class ListUtils {
    private ListUtils() {
    }

    public static boolean contains(int[] list, int item) {
        for (int listItem : list) {
            if (listItem == item) return true;
        }
        return false;
    }

    //listにstrが含まれているかどうか
    public static boolean contains(List<String> list, String str) {
        for (String strInList : list) {
            if (strInList.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
