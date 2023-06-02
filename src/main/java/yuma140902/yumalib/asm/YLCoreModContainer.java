package yuma140902.yumalib.asm;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

import java.util.Arrays;

public class YLCoreModContainer extends DummyModContainer {

	public YLCoreModContainer(){
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "yumalibcore";
		meta.name = "YumaLibCore";
		meta.version = "0.0.0";
		meta.authorList = Arrays.asList("yuma140902");
		meta.description = "CoreMod for UpToDateMod and YumaLib";
		setEnabledState(true);
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
}
