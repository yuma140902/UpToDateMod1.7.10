package yuma140902.yumalib.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions({"yuma140902.yumalib.asm"})
public class YLCorePlugin implements IFMLLoadingPlugin {
	@Override
	public String[] getASMTransformerClass() {
		return new String[]{"yuma140902.yumalib.asm.YLCoreTransformer"};
	}
	
	@Override
	public String getModContainerClass() {
		return "yuma140902.yumalib.asm.YLCoreModContainer";
	}
	
	@Override
	public String getSetupClass() {
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) {
	
	}
	
	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
