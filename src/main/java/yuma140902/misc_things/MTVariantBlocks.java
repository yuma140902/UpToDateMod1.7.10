package yuma140902.misc_things;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.blocks.BlockGenericSlab;
import yuma140902.yumalib.api.blocks.SlabBuilder;

public class MTVariantBlocks {
	@Nonnull
	private static final List<Block> list = new ArrayList<>();
	
	private static void add(@Nullable Block block) {
		if(block != null) list.add(block);
	}
	
	private static void add(@Nonnull Block... blocks) {
		for(final Block block : blocks) {
			add(block);
		}
	}
	
	public static List<Block> getBlocks(){
		return ImmutableList.copyOf(list);
	}
	
	public static void register() {
		for(final Block block : list) {
			if(block instanceof IRegisterable) {
				((IRegisterable) block).register();
			}
		}
	}
	
	public static final BlockGenericSlab slabLogAcacia;
	public static final BlockGenericSlab slabLogBirch;
	public static final BlockGenericSlab slabLogDarkOak;
	public static final BlockGenericSlab slabLogJungle;
	public static final BlockGenericSlab slabLogOak;
	public static final BlockGenericSlab slabLogSpruce;
	
	static {
		ModMiscThings.LOGGER.info("Additional Variant Blocks : init");
		
		add(
				slabLogAcacia = SlabBuilder.of(Blocks.log2, "slab_log_acacia").meta(McConst.Meta.LOG2_ACACIA).build(),
				slabLogBirch = SlabBuilder.of(Blocks.log, "slab_log_birch").meta(McConst.Meta.LOG_BIRCH).build(),
				slabLogDarkOak = SlabBuilder.of(Blocks.log2, "slab_log_dark_oak").meta(McConst.Meta.LOG2_DARK_OAK).build(),
				slabLogJungle = SlabBuilder.of(Blocks.log, "slab_log_jungle").meta(McConst.Meta.LOG_JUNGLE).build(),
				slabLogOak = SlabBuilder.of(Blocks.log, "slab_log_oak").meta(McConst.Meta.LOG_OAK).build(),
				slabLogSpruce = SlabBuilder.of(Blocks.log, "slab_log_spruce").meta(McConst.Meta.LOG_SPRUCE).build(),
				null
		);
	}
}
