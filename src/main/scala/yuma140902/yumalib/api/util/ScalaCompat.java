package yuma140902.yumalib.api.util;

import scala.collection.JavaConverters;
import scala.collection.Seq;

import java.util.Arrays;

public class ScalaCompat {
    public static <T> T optionOrNull(final scala.Option<T> o) {
        if (o.isDefined()) return o.get();
        return null;
    }

    public static <T> Seq<T> seq(T[] values) {
        return JavaConverters.collectionAsScalaIterableConverter(Arrays.asList(values)).asScala().toSeq();
    }

    public static <T> T[] array(Seq<T> seq, T[] hint) {
        return JavaConverters.asJavaCollectionConverter(seq).asJavaCollection().toArray(hint);
    }
}
