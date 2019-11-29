package yuma140902.uptodatemod.util.l10n;

import java.util.Map;
import javax.annotation.Nonnull;

public interface L10nString {
	@Nonnull String message();
	
	@Nonnull static L10nStringPlain of(String str) {
		return new L10nStringPlain(str != null ? str : "");
	}
	
	@Nonnull static L10nStringLangkey ofKey(@Nonnull String langKey) {
		return new L10nStringLangkey(langKey);
	}
	
	@Nonnull static L10nStringMultiLigual of(@Nonnull Map<EnumLanguage, String> map) {
		L10nStringMultiLigual stringml = new L10nStringMultiLigual();
		map.forEach((lang, msg) -> {if(lang != null && msg != null) stringml.put(lang, msg);});
		return stringml;
	}
	
	@Nonnull static L10nStringMultiLigual ml() {
		return new L10nStringMultiLigual();
	}
}
