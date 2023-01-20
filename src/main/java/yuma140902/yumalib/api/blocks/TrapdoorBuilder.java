package yuma140902.yumalib.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import yuma140902.yumalib.api.util.BlockWithMetadata;
import yuma140902.yumalib.api.util.ItemWithMetadata;

/**
 * {@link BlockGenericTrapDoor}のインスタンスを作る
 */
public class TrapdoorBuilder {
	private Material          material;
	private String            name;
	private ItemWithMetadata  ingredient;
	private float             hardness;
	private Block.SoundType   stepSound;
	private boolean           rotateTex = true;
	private BlockWithMetadata blockForSideTex;
	
	private TrapdoorBuilder(){}

	/**
	 * 木製のトラップドア用のビルダーを開始する
	 * @param name 名前。ModIDなし
	 */
	public static TrapdoorBuilder ofWooden(String name){
		TrapdoorBuilder builder = new TrapdoorBuilder();
		builder.name = name;
		builder.material = Material.wood;
		builder.hardness = 3.0F;
		builder.stepSound = Block.soundTypeWood;
		return builder;
	}

	/**
	 * 木製のトラップドア用のビルダーを開始する
	 * @param name 名前。ModIDなし
	 * @param plankMeta BlockPlankのメタデータ
	 */
	public static TrapdoorBuilder ofWooden(String name, int plankMeta){
		return ofWooden(name).ingredient(plankMeta).sideTex(plankMeta);
	}

	/**
	 * 金属製のトラップドア用のビルダーを開始する
	 * @param name 名前。ModIDなし
	 */
	public static TrapdoorBuilder ofMetal(String name){
		TrapdoorBuilder builder = new TrapdoorBuilder();
		builder.name = name;
		builder.material = Material.iron;
		builder.hardness = 5.0F;
		builder.stepSound = Block.soundTypeMetal;
		return builder;
	}

	/**
	 * 原料を指定する
	 * @return this
	 */
	public TrapdoorBuilder ingredient(ItemWithMetadata ingredient){
		this.ingredient = ingredient;
		return this;
	}
	
	protected TrapdoorBuilder ingredient(int plankMeta){
		return ingredient(new ItemWithMetadata(Blocks.planks, plankMeta));
	}

	/**
	 * 側面に表示するテクスチャを、他のブロックとメタで指定する
	 * @return this
	 */
	public TrapdoorBuilder sideTex(Block block, int metadata){
		this.blockForSideTex = new BlockWithMetadata(block, metadata);
		return this;
	}
	
	protected TrapdoorBuilder sideTex(int plankMeta){
		return sideTex(Blocks.planks, plankMeta);
	}

	/**
	 * 正面のテクスチャを回転させない
	 * @return this
	 */
	public TrapdoorBuilder noRotateTex(){
		this.rotateTex = false;
		return this;
	}

	/**
	 * ビルドする
	 */
	public BlockGenericTrapDoor build(){
		return new BlockGenericTrapDoor(material, name, hardness, stepSound, ingredient, rotateTex, (blockForSideTex != null), blockForSideTex);
	}
}
