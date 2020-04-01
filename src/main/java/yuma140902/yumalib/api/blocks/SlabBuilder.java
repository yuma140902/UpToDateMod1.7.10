package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;

public class SlabBuilder {
	private Block baseBlock;
	private int meta = 0;
	private String name;
	private String specialSideTexureName = null;
	private boolean ignoreMetaInRecipe = false;
	
	public SlabBuilder(@Nullable Block baseBlock, String name) {
		this.baseBlock = baseBlock;
		this.name = name;
	}
	
	public SlabBuilder meta(int meta) {
		this.meta = meta;
		return this;
	}
	
	public SlabBuilder specialSideTexture(String name) {
		this.specialSideTexureName = name;
		return this;
	}
	
	public SlabBuilder setIgnoreMetaInRecipe() {
		this.ignoreMetaInRecipe = true;
		return this;
	}
	
	@Nullable
	public BlockGenericSlab build() {
		if(this.baseBlock == null) return null;
		return new BlockGenericSlab(false, baseBlock, meta, name, specialSideTexureName, ignoreMetaInRecipe);
	}
}
