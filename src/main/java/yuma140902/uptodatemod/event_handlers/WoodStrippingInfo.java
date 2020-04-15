package yuma140902.uptodatemod.event_handlers;

import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import yuma140902.yumalib.api.util.BlockState;

public class WoodStrippingInfo implements IWoodStrippingInfo {

	@Nonnull
	private final Function<BlockState, BlockState> blockStateProvider;
	@Nonnull
	private final String soundName;
	
	private WoodStrippingInfo(@Nullable Function<BlockState, BlockState> blockStateProvider, @Nonnull String soundName) {
		this.blockStateProvider = blockStateProvider != null ? blockStateProvider : state -> state;
		this.soundName = soundName;
	}

	@Override
	public BlockState blockStateToPlace(BlockState oldState) {
		return this.blockStateProvider.apply(oldState);
	}

	@Override
	public String soundName() {return this.soundName;}
	
	
	@Nonnull
	public static IWoodStrippingInfo of(@Nonnull Function<BlockState, BlockState> blockStateProvider, @Nonnull String soundName) {
		return new WoodStrippingInfo(blockStateProvider, soundName);
	}
	
}
