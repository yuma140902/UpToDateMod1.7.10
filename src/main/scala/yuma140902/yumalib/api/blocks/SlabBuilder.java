package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;

/**
 * 既存のフルサイズのブロックから{@link BlockGenericWall}のインスタンスを作る
 */
public class SlabBuilder {
	private final Block baseBlock;
	private int meta = 0;
	private final String name;
	private String specialSideTextureName = null;
	private boolean ignoreMetaInRecipe = false;
	
	private SlabBuilder(@Nullable Block baseBlock, String name) {
		this.baseBlock = baseBlock;
		this.name = name;
	}

	/**
	 * 新しいビルダーを開始する
	 * @param baseBlock もとになるブロック
	 * @param name 名前。ModID部分は不要
	 */
	public static SlabBuilder of(@Nullable Block baseBlock, String name) {
		return new SlabBuilder(baseBlock, name);
	}

	/**
	 * もとになるブロックのメタを指定する
	 * @return this
	 */
	public SlabBuilder meta(int meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * 側面のテクスチャにベースのブロックとは別のテクスチャを指定する
	 * @param name テクスチャの名前。ModIDなし
	 * @return this
	 */
	public SlabBuilder specialSideTexture(String name) {
		this.specialSideTextureName = name;
		return this;
	}

	/**
	 * ベースのブロックのメタデータに関わらず作成できるレシピを登録する
	 * @return this
	 */
	public SlabBuilder setIgnoreMetaInRecipe() {
		this.ignoreMetaInRecipe = true;
		return this;
	}

	/**
	 * ビルドする
	 */
	@Nullable
	public BlockGenericSlab build() {
		if(this.baseBlock == null) return null;
		return new BlockGenericSlab(false, baseBlock, meta, name, specialSideTextureName, ignoreMetaInRecipe);
	}
}
