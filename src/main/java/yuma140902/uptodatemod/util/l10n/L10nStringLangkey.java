package yuma140902.uptodatemod.util.l10n;

import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import net.minecraft.util.StatCollector;

@RequiredArgsConstructor
public class L10nStringLangkey implements L10nString {
	@Nonnull final String langKey;
	
	@Override
	public String message() {
		String str = StatCollector.translateToLocal(langKey);
		return str != null ? str : "";
	}
}
