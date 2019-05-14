package yuma140902.uptodatemod.integration;

public interface IPlugin {
	String getModId();
	String getModName();
	boolean isModLoaded();
	boolean isIntegrationEnabled();
}
