package yuma140902.yumalib.api.client.renderer;

import yuma140902.yumalib.ModYumaLib;

public class RenderIDProvider {
	public static int getNewRenderId(){
		return ModYumaLib.proxy.getNewRenderId();
	}
}
