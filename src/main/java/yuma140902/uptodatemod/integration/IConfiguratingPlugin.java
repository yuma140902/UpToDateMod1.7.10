package yuma140902.uptodatemod.integration;

import net.minecraftforge.common.config.Configuration;

public interface IConfiguratingPlugin extends IPlugin {
	void initConfig(Configuration cfg);
	void syncConfig(Configuration cfg);
	void wrapConfig(Configuration cfg);
}
