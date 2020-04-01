package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;

public class StairsBuilder {
	private Block baseBlock;
	private int meta = 0;
	private String name;
	
	public StairsBuilder(@Nullable Block baseBlock, String name) {
		this.baseBlock = baseBlock;
		this.name = name;
	}
	
	public StairsBuilder meta(int meta) {
		this.meta = meta;
		return this;
	}
	
	@Nullable
	public BlockGenericStairs build() {
		if(this.baseBlock == null) return null;
		return new BlockGenericStairs(baseBlock, meta, name);
	}
}
