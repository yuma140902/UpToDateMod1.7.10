package yuma140902.uptodatemod.util.l10n;

import javax.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class L10nStringPlain implements L10nString {
	@Nonnull final String message;
	
	@Override
	public String message() {
		return this.message;
	}
}
