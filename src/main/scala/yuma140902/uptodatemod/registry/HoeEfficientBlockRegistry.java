package yuma140902.uptodatemod.registry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.init.Blocks;
import yuma140902.uptodatemod.MyBlocks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HoeEfficientBlockRegistry {
	private HoeEfficientBlockRegistry(){
		addBlock(Blocks.hay_block);
		addBlock(Blocks.sponge);
		addBlock(MyBlocks.netherWartBlock);
		addBlock(MyBlocks.sponge);
		addBlockClass(BlockLeavesBase.class);
	}
	
	public static final HoeEfficientBlockRegistry INSTANCE = new HoeEfficientBlockRegistry();
	
	private final List<Block> exactBlocks = new ArrayList<>();
	private final List<Class<?>> classes = new ArrayList<>();
	
	public void addBlock(@Nullable Block block){
		if(block != null)
			this.exactBlocks.add(block);
	}
	
	public void addBlockClass(Class<?> cls){
		this.classes.add(cls);
	}
	
	public boolean isHoeEfficient(Block targetBlock){
		if(targetBlock == null) return false;
		for(final Block block : this.exactBlocks){
			if(block == targetBlock) return true;
		}
		for(final Class<?> cls : this.classes){
			if(cls.isInstance(targetBlock)) return true;
		}
		return false;
	}
}
