package yuma140902.uptodatemod.asm;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;

public class UpToDateCoreModContainer extends DummyModContainer {
	private static final String COREMOD_ID = "uptodate_core";
	private static final String COREMOD_NAME = "UpToDateCore";
	private static final String COREMOD_VERSION = "1.0.0";
	
	public UpToDateCoreModContainer() {
		super(new ModMetadata());
		
		ModMetadata meta = getMetadata();
		
		meta.modId = COREMOD_ID;
		meta.name = COREMOD_NAME;
		meta.version = COREMOD_VERSION;
		meta.authorList.add("yuma140902");
		meta.description = "The CoreMod of UpToDateMod";
		this.setEnabledState(true);
	}
}
