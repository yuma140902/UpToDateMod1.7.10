package yuma140902.mcmodlib.api.update;

public interface IUpdateChecker {
	void checkForUpdates();
	boolean hasNewVersionAvailable();
	String getNewVersionUrl();
	String getAvailableNewVersion();
	String getModName();
}
