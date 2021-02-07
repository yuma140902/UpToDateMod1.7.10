package yuma140902.uptodatemod.event_handlers;

import javax.annotation.Nullable;
import yuma140902.yumalib.api.util.BlockWithMetadata;

public interface IWoodStrippingInfo {
	/**
	 * 斧で右クリックされた{@link BlockWithMetadata}から、新たに設置すべき{@link BlockWithMetadata}を返す<br>
	 * @param blockWithMetadata 斧で右クリックされた{@link BlockWithMetadata}
	 * @return 新たに設置すべき{@link BlockWithMetadata}。nullなら何も起こらない
	 */
	@Nullable
	BlockWithMetadata blockStateToPlace(BlockWithMetadata blockWithMetadata);
	
	/**
	 * ブロックを設置するときの音。nullで音を鳴らさない
	 */
	@Nullable
	String soundName();
}
