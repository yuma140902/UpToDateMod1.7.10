package yuma140902.uptodatemod.integration;

import yuma140902.uptodatemod.config.IConfigBridge;

public interface IConfiguratingPlugin extends IPlugin {
	void initConfig(IConfigBridge cfg);
	void syncConfig(IConfigBridge cfg);
	void wrapConfig(IConfigBridge cfg);
}
