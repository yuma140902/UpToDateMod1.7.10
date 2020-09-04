package yuma140902.yumaessentials.util;

import yuma140902.yumaessentials.YEConstants;
import yuma140902.yumalib.api.util.NameProvider;

public class StringUtil extends NameProvider {
	private StringUtil() {
		super(YEConstants.TEXTURE_DOMAIN, YEConstants.UNLOCALIZED_DOMAIN);
	}
	
	public static final StringUtil name = new StringUtil();
}
