package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;

/**
 * 既存のフルサイズのブロックから{@link BlockGenericStairs}のインスタンスを作る
 */
public class StairsBuilder {
	private final Block baseBlock;
	private final String name;
	private int meta = 0;
	
	private StairsBuilder(@Nullable Block baseBlock, String name) {
		this.baseBlock = baseBlock;
		this.name = name;
	}

	/**
	 * 新しいビルダーを開始する
	 * @param baseBlock もとになるブロック
	 * @param name 名前。ModID部分は不要
	 */
	public static StairsBuilder of(@Nullable Block baseBlock, String name) {
		return new StairsBuilder(baseBlock, name);
	}

	/**
	 * もとになるブロックのメタを指定する
	 * @return this
	 */
	public StairsBuilder meta(int meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * ビルドする
	 */
	@Nullable
	public BlockGenericStairs build() {
		if(this.baseBlock == null) return null;
		return new BlockGenericStairs(baseBlock, meta, name);
	}
}
