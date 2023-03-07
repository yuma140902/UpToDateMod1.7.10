package yuma140902.yumalib;

import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.items.ItemDummyLogo$;
import yuma140902.yumalib.items.ItemGameModeSwitcher$;

import com.google.common.collect.ImmutableList;

import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * YumaLibの実装するアイテムを保持・ゲームへ登録するクラス
 */
public class YLItems {
    public static final ItemDummyLogo$ dummyLogo;
    public static final ItemGameModeSwitcher$ gmSwitcher;
    private static final List<Item> items = new ArrayList<>();

    static {
        ModYumaLib.LOGGER.info("Items init");
        add(
                dummyLogo = ItemDummyLogo$.MODULE$,
                gmSwitcher = ItemGameModeSwitcher$.MODULE$,
                null
        );
    }

    public static List<Item> items() {
        return ImmutableList.copyOf(items);
    }

    private static void add(@Nullable Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    private static void add(@Nullable Item... itemList) {
        if (itemList == null) return;
        for (final Item item : itemList) {
            add(item);
        }
    }

    /**
     * すべてのアイテムをゲームに登録する
     */
    public static void registerAll() {
        for (final Item item : items) {
            if (item instanceof IRegisterable) {
                ((IRegisterable) item).register();
            }
        }
    }
}
