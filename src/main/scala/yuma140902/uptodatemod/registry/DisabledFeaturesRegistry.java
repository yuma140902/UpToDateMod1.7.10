package yuma140902.uptodatemod.registry;

import java.util.TreeSet;

public class DisabledFeaturesRegistry {
	private DisabledFeaturesRegistry() {}
	public static DisabledFeaturesRegistry INSTANCE = new DisabledFeaturesRegistry();
	
	private TreeSet<EnumDisableableFeatures> registry = new TreeSet<EnumDisableableFeatures>();
	
	public boolean isEnabled(EnumDisableableFeatures feature) {
		return !registry.contains(feature);
	}
	
	public void setDisabled(EnumDisableableFeatures feature) {
		registry.add(feature);
	}
}
