package yuma140902.uptodatemod.util.l10n;

import java.util.EnumMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class L10nStringMultiLigual implements L10nString {
	@Nonnull final EnumMap<EnumLanguage, String> dic = new EnumMap<>(EnumLanguage.class);
	
	public L10nStringMultiLigual put(@Nonnull EnumLanguage language, @Nonnull String message) {
		dic.put(language, message);
		return this;
	}
	
	@Override
	public String message() {
		return message(EnumLanguage.currentLanguage());
	}
	
	@Nonnull
	public String message(@Nullable EnumLanguage language) {
		if(language == null || !dic.containsKey(language)) {
			language = EnumLanguage.en_US;
		}
		
		String message = dic.get(language);
		return message != null ? message : "{{UpToDateMod:No String}}";
	}
	
	@Nonnull public L10nString nonnull() {
		return this;
	}
}
