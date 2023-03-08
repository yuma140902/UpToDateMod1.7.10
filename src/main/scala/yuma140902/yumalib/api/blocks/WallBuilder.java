package yuma140902.yumalib.api.blocks;

import javax.annotation.Nullable;
import net.minecraft.block.Block;

/**
 * 既存のフルサイズのブロックから{@link BlockGenericWall}のインスタンスを作る
 */
public class WallBuilder {
	@Nullable
	private final Block block;
	private final String name;
	private int meta = 0;

	private WallBuilder(@Nullable Block block, String name) {
		this.block = block;
		this.name = name;
	}

	/**
	 * 新しいビルダーを開始する
	 * @param block もとになるブロック
	 * @param name 名前。ModID部分は不要
	 */
	public static WallBuilder of(@Nullable Block block, String name) {
		return new WallBuilder(block, name);
	}

	/**
	 * もとになるブロックのメタを指定する
	 * @param meta メタ
	 * @return this
	 */
	public WallBuilder meta(int meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * ビルドする
	 * @return {@link BlockGenericWall}のインスタンス
	 */
	public BlockGenericWall build() {
		if(this.block == null) {
			return null;
		}
		return new BlockGenericWall(block, meta, name);
	}
}
