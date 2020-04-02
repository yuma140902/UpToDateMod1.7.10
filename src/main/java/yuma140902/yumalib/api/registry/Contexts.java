package yuma140902.yumalib.api.registry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import yuma140902.yumalib.YLConstants;
import yuma140902.yumalib.api.context.InitModContext;
import yuma140902.yumalib.api.util.NameProvider;

/**
 * Mod初期化時の文脈(YumaLibのAPIを呼び出しているのはどのModかなどの情報)を表す。
 * これにより、いちいちメソッドにMod名などを渡す手間が省ける
 * @author yuma1
 *
 */
public class Contexts {
	@Nonnull
	public static final InitModContext DEFAULT = new InitModContext(new NameProvider(YLConstants.MOD_ID, YLConstants.MOD_ID));
	@Nullable
	private static InitModContext current = null;
	
	@Nonnull
	public static InitModContext current() {
		return current != null ? current : DEFAULT;
	}
	
	public static void setContext(InitModContext context) {
		current = context;
	}
	
	public static void removeContext() {
		current = null;
	}
}
