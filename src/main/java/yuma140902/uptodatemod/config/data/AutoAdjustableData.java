package yuma140902.uptodatemod.config.data;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yuma140902.uptodatemod.util.l10n.EnumLanguage;
import yuma140902.uptodatemod.util.l10n.L10nString;

public class AutoAdjustableData<T> {
	
	@Nonnull
	private final List<Adjustment> adjustments = new LinkedList<>();
	
	/**
	 * trueならtryAdjastは成功、falseなら失敗
	 */
	@Getter @Setter
	private boolean auto = true;
	
	@SuppressWarnings("null")
	@Nonnull
	private T value;

	@Nonnull
	public T get() {
		return value;
	}

	public void set(@Nonnull T value) {
		this.value = value;
	}
	
	
	public void tryAdjust(@Nonnull T value, @Nonnull Adjustment adjustment) {
		if(auto) {
			forceAdjust(value, adjustment);
		}
	}
	
	public void forceAdjust(@Nonnull T value, @Nonnull Adjustment adjustment) {
		auto = false;
		this.value = value;
		adjustments.add(adjustment);
	}
	
	@Nonnull
	public String adjustmentComments() {
		String comments = adjustments.stream().map(adjustment -> adjustment.commentString().message()).collect(Collectors.joining("\n"));
		return comments != null ? comments : "";
	}
	
	
	@RequiredArgsConstructor
	public static class Adjustment {
		@Getter
		@Nonnull
		private String adjusterName;
		@Getter
		@Nonnull
		private String message;
		
		@SuppressWarnings("null")
		@Nonnull
		public L10nString commentString() {
			StringBuilder en = new StringBuilder();
			en.append("Adjusted by ").append(adjusterName);
			if(message.isEmpty()) en.append(" : ").append(message);
			
			StringBuilder ja = new StringBuilder();
			ja.append(adjusterName).append("によって変更されました");
			if(message.isEmpty()) ja.append(" : ").append(message);
			
			
			return L10nString.ml()
				.put(EnumLanguage.en_US, en.toString())
				.put(EnumLanguage.ja_JP, ja.toString());
		}
	}
	
}
