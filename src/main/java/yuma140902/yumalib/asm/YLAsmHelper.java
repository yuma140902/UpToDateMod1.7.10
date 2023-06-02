package yuma140902.yumalib.asm;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class YLAsmHelper {
	public static Logger logger = LogManager.getLogger("YumaLibCore ASM");
	
	/**
	 クラスの名前を難読化(obfuscation)する。
	 */
	public static String unmapClassName(String name){
		return FMLDeobfuscatingRemapper.INSTANCE.unmap(name.replace('.','/')).replace('/', '.');
	}
	
	/**
	 メソッドの名前を易読化(deobfuscation)する。
	 */
	public static String mapMethodName(String owner,String methodName,String desc){
		return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(unmapClassName(owner), methodName, desc);
	}
	
	/**
	 フィールドの名前を易読化(deobfuscation)する。
	 */
	public static String mapFieldName(String owner,String methodName,String desc){
		return FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(unmapClassName(owner), methodName, desc);
	}
}
