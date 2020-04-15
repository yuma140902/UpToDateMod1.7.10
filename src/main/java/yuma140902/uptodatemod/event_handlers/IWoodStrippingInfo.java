package yuma140902.uptodatemod.event_handlers;

import javax.annotation.Nullable;
import yuma140902.yumalib.api.util.BlockState;

public interface IWoodStrippingInfo {
	/**
	 * 斧で右クリックされた{@link BlockState}から、新たに設置すべき{@link BlockState}を返す<br>
	 * @param blockState 斧で右クリックされた{@link BlockState}
	 * @return 新たに設置すべき{@link BlockState}。nullなら何も起こらない
	 */
	@Nullable
	BlockState blockStateToPlace(BlockState blockState);
	
	/**
	 * ブロックを設置するときの音。nullで音を鳴らさない
	 */
	@Nullable
	String soundName();
}
