package yuma140902.yumalib.api.util;

public class ScalaCompat {
    public static <T> T optionOrNull(final scala.Option<T> o) {
        if (o.isDefined()) return o.get();
        return null;
    }
}
