package yuma140902.uptodatemod.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.MyBlocks;

public enum EnumNoteBlockInstrument {
	ICECHIME("uptodate:note.icechime", Blocks.packed_ice),
	BELL("uptodate:note.bell", Blocks.gold_block),
	FLUTE("uptodate:note.flute", Blocks.clay),
	GUITAR("uptodate:note.guitar", Blocks.wool),
	XYLOBONE("uptodate:note.xylobone", MyBlocks.boneBlock);
	
	private String soundName;
	private Block block;
	
	public String getSoundName() {
		return soundName;
	}
	
	public boolean matches(Block block) {
		return (block == null) ? false : (this.block == block);
	}
	
	private EnumNoteBlockInstrument(String soundName, Block block) {
		this.soundName = soundName;
		this.block = block;
	}
	
	private static final EnumNoteBlockInstrument[] values = values();
	private static final int max = values.length;
  
  public static EnumNoteBlockInstrument fromId(int id)
  {
      return id < 0 || id >= max ? GUITAR : values[id];
  }
  
  public static int getLength() {
  	return max;
  }
  
}
