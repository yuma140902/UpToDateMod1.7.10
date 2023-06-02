package yuma140902.yumalib.asm;

import cpw.mods.fml.relauncher.FMLLaunchHandler;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class YLCoreTransformer implements IClassTransformer {
	public static final String[] TARGETS = {AdapterEntityZombie.CLS_NAME};
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if(!accept(transformedName)){
			return basicClass;
		}
		
		if(FMLLaunchHandler.side().isClient()){
			if(transformedName.equals(AdapterEntityZombie.CLS_NAME)){
				YLAsmHelper.logger.info("Transform: " + AdapterEntityZombie.CLS_NAME);
				YLAsmHelper.logger.info("Internal Name: " + name);
				YLAsmHelper.logger.info("Transformed Name: " + transformedName);
				ClassReader cr = new ClassReader(basicClass);
				ClassWriter cw = new ClassWriter(1);
				ClassVisitor cv = new AdapterEntityZombie.ClassAdapter(cw);
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
