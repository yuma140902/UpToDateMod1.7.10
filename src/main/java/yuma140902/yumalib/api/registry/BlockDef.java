package yuma140902.yumalib.api.registry;

import net.minecraft.item.ItemBlock;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})

public @interface BlockDef {
	/**
	 * GameRegistryに登録するときに使う名前。
	 * この項目が空文字列で、クラスが{@link INameProvider}を実装していた場合、{@link INameProvider#getName()}が使用される。
	 */
	String name();
	
	/**
	 * langファイルなどから指定するときの名前。
	 * 省略した場合は{@link BlockDef#name()}が使われる
	 */
	String unlocalizedName() default "";
	
	/**
	 * テクスチャの名前。省略した場合は{@link BlockDef#name()}が使われる
	 */
	String textureName() default "";
	
	/**
	 * 対応する{@link ItemBlock}のクラス
	 */
	Class<? extends ItemBlock> itemBlock() default ItemBlock.class;
	
	/**
	 * ブロックがゲームに登場するために、すべて有効になっている必要のある{@link EnumDisableableFeatures}のリスト
	 */
	EnumDisableableFeatures[] requirements() default {};
	
	
	interface INameProvider {
		String getName();
	}
}
