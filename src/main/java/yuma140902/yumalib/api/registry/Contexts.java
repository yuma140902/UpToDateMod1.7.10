package yuma140902.yumalib.api.registry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import yuma140902.yumalib.ModYumaLib;
import yuma140902.yumalib.YLConstants;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.context.InitModContext;
import yuma140902.yumalib.api.util.NameProvider;

/**
 * {@link InitModContext}のレジストリ
 */
public class Contexts {
	@Nonnull
	public static final InitModContext DEFAULT = new InitModContext(YLConstants.MOD_NAME, new NameProvider(YLConstants.MOD_ID, YLConstants.MOD_ID));
	@Nullable
	private static InitModContext current = null;
	
	/**
	 * 注意: {@link IRegisterable#register()}以外の場所から{@link InitModContext}を利用するのは危険
	 * @return 現在の{@link InitModContext}。設定されていなければYumaLibのデフォルト値
	 */
	@Nonnull
	public static InitModContext current() {
		return current != null ? current : DEFAULT;
	}

	/**
	 * 現在のコンテキストを上書きする
	 */
	public static void setContext(@Nonnull InitModContext context) {
		current = context;
		ModYumaLib.LOGGER.info("Context updated: " + context.addonModName());
	}

	/**
	 * 現在のコンテキストを消去してデフォルト値に戻す
	 */
	public static void removeContext() {
		current = null;
		ModYumaLib.LOGGER.info("Context reset");
	}
}
