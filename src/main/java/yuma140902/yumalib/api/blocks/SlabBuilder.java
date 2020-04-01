package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;
import yuma140902.yumalib.api.util.NameProvider;

public class SlabBuilder {
	private NameProvider nameProvider;
	private Block baseBlock;
	private int meta = 0;
	private String name;
	private String specialSideTexureName = null;
	private boolean ignoreMetaInRecipe = false;
	
	public SlabBuilder(NameProvider nameProvider) {
		this.nameProvider = nameProvider;
	}
	
	public SlabBuilder create(@Nullable Block baseBlock, String name) {
		this.baseBlock = baseBlock;
		this.name = name;
		this.meta = 0;
		this.specialSideTexureName = null;
		this.ignoreMetaInRecipe = false;
		return this;
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
		return new BlockGenericSlab(false, baseBlock, meta, name, specialSideTexureName, ignoreMetaInRecipe, nameProvider);
	}
}
