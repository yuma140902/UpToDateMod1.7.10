package yuma140902.yumalib.api.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * ドメインなしの文字列を受け取りドメイン付きの文字列を返す
 */
public class NameProvider {
	
	public final String TEXTURE_DOMAIN;
	public final String UNLOCALIZED_DOMAIN;
	
	public NameProvider(String textureDomain, String unlocalizedDomain) {
		this.TEXTURE_DOMAIN = textureDomain;
		this.UNLOCALIZED_DOMAIN = unlocalizedDomain;
	}
	
	public String domainedTexture(String name) {
		return TEXTURE_DOMAIN + ":" + name;
	}
	
	public String domainedUnlocalized(String name) {
		return UNLOCALIZED_DOMAIN + "." + name;
	}
	
	
	@Nonnull
	public String[] domainedTextures(String... names) {
		return _domainedTextures(TEXTURE_DOMAIN, names);
	}
	
	@Nonnull
	private static String[] _domainedTextures(String textureDomain, @Nullable String... names) {
		if(names == null) return new String[0];
		
		String[] ret = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			ret[i] = textureDomain + ":" + names[i];
		}
		return ret;
	}
}
