package yuma140902.yumalib.api.registry;

import com.google.common.base.Strings;
import net.minecraft.block.Block;
import yuma140902.yumalib.ModYumaLib;

import java.util.List;

public class GameRegisterer {
	public void registerBlocks(List<Block> blocks, IModBlockRegistry registry){
		//TODO
		for(final Block block : blocks){
			BlockDef blockDef = block.getClass().getAnnotation(BlockDef.class);
			if(blockDef == null) {
				ModYumaLib.LOGGER.warn(String.format("%s has no @BlockDef annotation. Skipping.", block.getClass().toString()));
				assert false;
				continue;
			}
			if(Strings.isNullOrEmpty(blockDef.name()) && !(block instanceof BlockDef.INameProvider)){
				ModYumaLib.LOGGER.error(String.format("%s has no name to register. Skipping.", block));
				assert false;
				continue;
			}
			
			String registerName = Strings.isNullOrEmpty(blockDef.name()) ? ((BlockDef.INameProvider)block).getName() : blockDef.name();
			String unlocalizedName = Strings.isNullOrEmpty(blockDef.unlocalizedName()) ? registerName : blockDef.unlocalizedName();
			String textureName = Strings.isNullOrEmpty(blockDef.textureName()) ? registerName : blockDef.textureName();
			
			String domainedUnlocalizedName = Contexts.current().nameProvider().domainedUnlocalized(unlocalizedName);
			String domainedTextureName = textureName.contains(":") ? textureName : Contexts.current().nameProvider().domainedTexture()
		}
	}
}
