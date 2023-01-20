package yuma140902.yumalib.api.update;

/**
 * 更新確認のインターフェース
 * <p>実装クラスのインスタンスは{@link yuma140902.yumalib.api.registry.UpdateCheckerRegistry}に登録する</p>
 */
public interface IUpdateChecker {
	void checkForUpdates();
	boolean hasNewVersionAvailable();
	String getNewVersionUrl();
	String getAvailableNewVersion();
	String getModName();
}
