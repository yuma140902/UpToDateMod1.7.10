package yuma140902.uptodatemod.util.l10n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import cpw.mods.fml.common.FMLCommonHandler;

public enum EnumLanguage {
	en_US, ja_JP;
	
	@Nonnull
	public static EnumLanguage forLangName(@Nullable String langName) {
		if(langName == null) {
			return en_US;
		}
		else if(langName.toLowerCase() == "ja_jp") {
			return ja_JP;
		}
		else {
			return en_US;
		}
	}
	
	@Nonnull
	public static EnumLanguage currentLanguage() {
		return forLangName(FMLCommonHandler.instance().getCurrentLanguage());
	}
}
