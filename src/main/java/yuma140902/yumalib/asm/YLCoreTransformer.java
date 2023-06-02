package yuma140902.yumalib.asm;

import cpw.mods.fml.relauncher.FMLLaunchHandler;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class YLCoreTransformer implements IClassTransformer {
	public static final String CLS_BLOCK_DIRT = "net.minecraft.block.BlockDirt";
	public static final String[] TARGETS = {CLS_BLOCK_DIRT};
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if(!accept(transformedName)){
			return basicClass;
		}
		
		if(FMLLaunchHandler.side().isClient()){
			if(transformedName.equals(CLS_BLOCK_DIRT)){
				YLAsmHelper.logger.info("Transform: " + CLS_BLOCK_DIRT);
				YLAsmHelper.logger.info("Internal Name: " + name);
				YLAsmHelper.logger.info("Transformed Name: " + transformedName);
				ClassReader cr = new ClassReader(basicClass);
				ClassWriter cw = new ClassWriter(1);
				ClassVisitor cv = new AdapterBlockDirt.ClassAdapter(cw);
				cr.accept(cv, 0);
				
				return cw.toByteArray();
			}
		}
		return basicClass;
	}
	
	public static boolean accept(String className){
		for(String target: TARGETS){
			if(target.equals(className)) return true;
		}
		return false;
	}
}
