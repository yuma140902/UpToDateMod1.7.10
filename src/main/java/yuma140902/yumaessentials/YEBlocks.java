package yuma140902.yumaessentials;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import yuma140902.yumalib.ModYumaLib;
import yuma140902.yumalib.api.IRegisterable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class YEBlocks {
	private static final List<Block> blocks = new ArrayList<>();
	
	public static List<Block> items(){
		return ImmutableList.copyOf(blocks);
	}
	
	private static void add(@Nullable Block block) {
		if(block != null) {
			blocks.add(block);
		}
	}
	
	private static void add(@Nullable Block... blockList) {
		if(blockList == null) return;
		for(final Block item : blockList) {
			add(item);
		}
	}
	
	public static void registerAll() {
		for(final Block block : blocks) {
			if(block instanceof IRegisterable) {
				((IRegisterable) block).register();
			}
		}
	}
	
	
	static {
		ModYumaLib.LOGGER.info("Blocks init");
		add(null,
						null
		);
	}
}
