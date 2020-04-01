package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import yuma140902.yumalib.api.util.NameProvider;

public class WallBuilder {
	@Nullable
	private Block block;
	private int meta;
	private String name;
	private NameProvider nameProvider;
	
	public WallBuilder(NameProvider nameProvider) {
		this.nameProvider = nameProvider;
	}
	
	public WallBuilder create(@Nullable Block block, String name) {
		this.block = block;
		this.name = name;
		this.meta = 0;
		return this;
	}
	
	public WallBuilder meta(int meta) {
		this.meta = meta;
		return this;
	}
	
	public BlockGenericWall build() {
		if(this.block == null) {
			return null;
		}
		return new BlockGenericWall(block, meta, name, nameProvider);
	}
}
