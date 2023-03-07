package yuma140902.uptodatemod.event_handlers;

import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import yuma140902.yumalib.api.util.BlockWithMetadata;

public class WoodStrippingInfo implements IWoodStrippingInfo {

	@Nonnull
	private final Function<BlockWithMetadata, BlockWithMetadata> blockWithMetaProvider;
	@Nonnull
	private final String                                         soundName;
	
	private WoodStrippingInfo(@Nullable Function<BlockWithMetadata, BlockWithMetadata> blockWithMetaProvider, @Nonnull String soundName) {
		this.blockWithMetaProvider = blockWithMetaProvider != null ? blockWithMetaProvider : state -> state;
		this.soundName = soundName;
	}

	@Override
	public BlockWithMetadata blockStateToPlace(BlockWithMetadata oldState) {
		return this.blockWithMetaProvider.apply(oldState);
	}

	@Override
	public String soundName() {return this.soundName;}
	
	
	@Nonnull
	public static IWoodStrippingInfo of(@Nonnull Function<BlockWithMetadata, BlockWithMetadata> blockWithMetaProvider, @Nonnull String soundName) {
		return new WoodStrippingInfo(blockWithMetaProvider, soundName);
	}
	
}
