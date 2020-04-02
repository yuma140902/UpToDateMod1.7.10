package yuma140902.yumalib.api.context;

import yuma140902.yumalib.api.util.NameProvider;

public class InitModContext {
	private NameProvider nameProvider;
	
	public InitModContext(NameProvider nameProvider) {
		this.nameProvider = nameProvider;
	}
	
	public NameProvider nameProvider() {return nameProvider;}
}
