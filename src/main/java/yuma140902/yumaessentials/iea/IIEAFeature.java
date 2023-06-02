package yuma140902.yumaessentials.iea;

import cpw.mods.fml.relauncher.Side;

import javax.annotation.Nonnull;

/**
 * IEAのモジュール
 */
public interface IIEAFeature {
	/**
	 * 名前
	 * @return
	 */
	String name();
	
	/**
	 * 説明
	 * @return
	 */
	String description();
	
	/**
	 * どちらのSideで実行されるべきか
	 * @return {@link Side#CLIENT}ならクライアントのみ、{@link Side#SERVER}ならサーバーとクライアント両方で実行される。
	 */
	@Nonnull Side side();
	
	/**
	 * 機能を実行
	 * @return
	 */
	@Nonnull IEAFeatureResult run();
}
