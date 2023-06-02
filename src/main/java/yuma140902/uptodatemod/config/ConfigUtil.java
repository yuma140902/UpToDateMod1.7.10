package yuma140902.uptodatemod.config;

import yuma140902.uptodatemod.config.model.IConfigCategory;
import yuma140902.uptodatemod.config.model.IConfigEntry;
import yuma140902.uptodatemod.config.model.IConfigProp;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class ConfigUtil {
	private static <T> void setL10nName(String domain, IConfigEntry entry, String langName) {
		entry.setLocalizationKey("config.uptodate." + domain + "." + langName);
	}
	
	private static <T> void setL10nNameAndTooltip(String domain, IConfigEntry entry, String langName) {
		setL10nName(domain, entry, langName);
		entry.addCommentLine(L10nString.ofKey("config.uptodate." + domain + "." + langName + ".tooltip"));
	}
	
	public static <T> void setL10nNameToProp(IConfigProp<T> prop, String key) {
		setL10nName("prop", prop, key);
	}
	
	public static <T> void setL10nNameAndTooltipToProp(IConfigProp<T> prop, String langName) {
		setL10nNameAndTooltip("prop", prop, langName);
	}
	
	public static void setL10nNameToCategory(IConfigCategory category, String langName) {
		setL10nName("category", category, langName);
	}
	
	public static void setL10nNameAndTooltipToCategory(IConfigCategory category, String langName) {
		setL10nNameAndTooltip("category", category, langName);
	}
}
