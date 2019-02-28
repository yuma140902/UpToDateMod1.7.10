package yuma140902.uptodatemod.asm;

import java.util.Map;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

// see: https://mcmodding.jp/modding/index.php/Coremods%E3%81%AE%E5%9F%BA%E7%A4%8E

@TransformerExclusions({"yuma140902.uptodatemod"})
public class UpToDateCorePlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {"yuma140902.uptodatemod.asm.UpToDateCoreTransformer"};
	}

	@Override
	public String getModContainerClass() {
		return "yuma140902.uptodatemod.asm.UpToDateCoreModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public String getAccessTransformerClass() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}
