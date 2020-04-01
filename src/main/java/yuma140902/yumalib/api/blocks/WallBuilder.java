package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;

public class WallBuilder {
	@Nullable
	private Block block;
	private String name;
	private int meta = 0;
	
	public WallBuilder(@Nullable Block block, String name) {
		this.block = block;
		this.name = name;
	}
	
	public static WallBuilder create(@Nullable Block block, String name) {
		return new WallBuilder(block, name);
	}
	
	public WallBuilder meta(int meta) {
		this.meta = meta;
		return this;
	}
	
	public BlockGenericWall build() {
		if(this.block == null) {
			return null;
		}
		return new BlockGenericWall(block, meta, name);
	}
}
