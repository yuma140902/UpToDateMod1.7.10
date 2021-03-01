package yuma140902.yumalib.api.client.model;


import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.yumalib.ModYumaLib;
import yuma140902.yumalib.api.McConst;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Minecraft1.8あたりで追加されたブロックモデル(jsonファイル)に似せたブロックモデル。仕様はあとでgistにまとめる
 */
public class YLBlockModel {
	public static int renderId(){
		return ModYumaLib.ylBlockModelRenderID;
	}
	
	/**
	 * ブロックモデルを構成する直方体
	 */
	public static class Element {
		private final int[] from;
		private final int[] to;
		private final Face faceDown;
		private final Face faceUp;
		private final Face faceNorth;
		private final Face faceSouth;
		private final Face faceWest;
		private final Face faceEast;
		
		/**
		 * @param from 直方体の始点 [x, y, z]
		 * @param to 直方体の終点 [x, y, z]
		 */
		public Element(int[] from, int[] to, Face faceDown, Face faceUp, Face faceNorth, Face faceSouth, Face faceWest, Face faceEast){
			if(from.length != 3 || to.length != 3){
				throw new IllegalArgumentException();
			}
			this.from = from;
			this.to = to;
			this.faceDown = faceDown;
			this.faceUp = faceUp;
			this.faceNorth = faceNorth;
			this.faceSouth = faceSouth;
			this.faceWest = faceWest;
			this.faceEast = faceEast;
		}
		
		/**
		 * 直方体の始点。
		 * ちなみに1ブロックは16*16*16
		 * @return [x, y, z]
		 */
		public int[] getFrom(){
			return this.from;
		}
		
		public int getFromX() {return this.from[0];}
		public int getFromY() {return this.from[1];}
		public int getFromZ() {return this.from[2];}
		
		/**
		 * 直方体の終点。
		 * ちなみに1ブロックは16*16*16
		 * @return [x, y, z]
		 */
		public int[] getTo(){
			return this.to;
		}
		
		public int getToX(){return this.to[0];}
		public int getToY(){return this.to[1];}
		public int getToZ(){return this.to[2];}
		
		@Nullable
		public Face getFace(int side){
			switch (side){
				case McConst.SIDE_BOTTOM: return faceDown;
				case McConst.SIDE_TOP: return faceUp;
				case McConst.SIDE_NORTH: return faceNorth;
				case McConst.SIDE_SOUTH: return faceSouth;
				case McConst.SIDE_WEST: return faceWest;
				case McConst.SIDE_EAST: return faceEast;
			}
			return null;
		}
	}
	
	/**
	 * {@link Element} の各面
	 */
	public static class Face {
		private final int[] uv;
		private final String textureName;
		private final ForgeDirection cullFace;
		private final int rotation;
		
		/**
		 * @param uv テクスチャの範囲 [u1, v1, u2, v2]
		 * @param textureName テクスチャの名前
		 * @param cullFace ここで指定された位置にブロックが他のブロックと接触した場合、その面の描画をしない。指定しない場合は {@link ForgeDirection#UNKNOWN}
		 * @param rotation テクスチャの回転。0, 90, 180, 270のいずれかの値
		 */
		public Face(int[] uv, String textureName, ForgeDirection cullFace, int rotation){
			if(uv.length != 4){
				throw new IllegalArgumentException();
			}
			if(!(rotation == 0 || rotation == 90 || rotation == 180 || rotation == 270)){
				throw new IllegalArgumentException();
			}
			this.uv = uv;
			this.textureName = textureName;
			this.cullFace = cullFace == null ? ForgeDirection.UNKNOWN : cullFace;
			this.rotation = rotation;
		}
		
		/**
		 * テクスチャの適用する範囲
		 * @return [u1, v1, u2, v2]
		 */
		public int[] getUV(){
			return this.uv;
		}
		
		public int getUMin() {
			return this.uv[0];
		}
		
		public int getUMax(){
			return this.uv[1];
		}
		
		public int getVMin(){
			return this.uv[2];
		}
		
		public int getVMax(){
			return this.uv[3];
		}
		
		/**
		 * テクスチャを識別する文字列
		 */
		public String getTextureName(){
			return this.textureName;
		}
		
		/**
		 * ここで指定された位置にブロックが他のブロックと接触した場合、その面の描画をしない。
		 * @return 無指定なら {@link ForgeDirection#UNKNOWN}
		 */
		@Nonnull
		public ForgeDirection getCullFace(){
			return this.cullFace;
		}
		
		/**
		 * テクスチャの回転
		 * @return 0, 90, 180 or 270
		 */
		public int getRotation(){
			return this.rotation;
		}
	}
	
	private final Element[] elements;
	
	public YLBlockModel(Element[] elements){
		this.elements = elements;
	}
	
	public Element[] getElements(){
		return this.elements;
	}
}
