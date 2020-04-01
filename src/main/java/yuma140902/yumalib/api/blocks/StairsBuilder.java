package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import yuma140902.yumalib.api.util.NameProvider;

public class StairsBuilder {
	private Block baseBlock;
	private int meta = 0;
	private String name;
	private NameProvider nameProvider;
	
	public StairsBuilder(NameProvider nameProvider) {
		this.nameProvider = nameProvider;
	}
	
	public StairsBuilder create(@Nullable Block baseBlock, String name) {
		this.baseBlock = baseBlock;
		this.name = name;
		this.meta = 0;
		return this;
	}
	
	public StairsBuilder meta(int meta) {
		this.meta = meta;
		return this;
	}
	
	@Nullable
	public BlockGenericStairs build() {
		if(this.baseBlock == null) return null;
		return new BlockGenericStairs(baseBlock, meta, name, nameProvider);
	}
}
