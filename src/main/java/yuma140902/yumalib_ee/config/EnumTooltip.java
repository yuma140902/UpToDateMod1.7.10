package yuma140902.yumalib_ee.config;

public enum EnumTooltip {
	Always,
	OnlyWhenAdvancedMode,
	Never,
	;
	private static String[] cache = null;
	public static String[] stringValues() {
		if(cache != null) {
			return cache;
		}
		
		EnumTooltip[] values = values();
		cache = new String[values.length];
		for(int i=0; i < cache.length; ++i) {
			cache[i] = values[i].toString();
		}
		
		return cache;
	}
	
	public boolean toBoolean(boolean isInAdvancedMode) {
		if(this.equals(Never)) return false;
		else if(this.equals(Always)) return true;
		return isInAdvancedMode;
	}
}
