package yuma140902.yumalib_ee.api.update;

public interface IUpdateChecker {
	void checkForUpdates();
	boolean hasNewVersionAvailable();
	String getNewVersionUrl();
	String getAvailableNewVersion();
	String getModName();
}
