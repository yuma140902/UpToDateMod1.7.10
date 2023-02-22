package yuma140902.yumalib.api.client.model;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * {@link YLBlockModel}をモデルとして持つブロックにつけられるインターフェイス
 */
public interface IBlockWithYLBlockModel {
	/**
	 * ワールドに描画されるときのモデル
	 */
	YLBlockModel getYLBlockModelInWorld(IBlockAccess world, int x, int y, int z);
	
	/**
	 * インベントリ内に描画されるときのモデル
	 * @return モデル。アイコンとして描画したいときはnull
	 */
	YLBlockModel getYLBlockModelInInventory(int metadata);
	
	/**
	 * textureNameに対応するIIconを返す
	 * @param textureName テクスチャの種類を決めるための任意の文字列。他のブロックのものと被っても良い。
	 */
	IIcon getFaceTexture(String textureName);
}
