package yuma140902.yumalib.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import static org.objectweb.asm.Opcodes.ASM4;

public class AdapterBlockDirt {
	public static class ClassAdapter extends ClassVisitor {
		private static String SRG_II_GETICON = "func_149691_a";
		private static String MCP_II_GETICON = "getIcon";
		
		public ClassAdapter(ClassVisitor cv){
			super(ASM4, cv);
		}
		
		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			if(SRG_II_GETICON.equals(YLAsmHelper.mapMethodName(YLCoreTransformer.CLS_BLOCK_DIRT, name, desc)) || MCP_II_GETICON.equals(name)){
				YLAsmHelper.logger.info("Processing " + SRG_II_GETICON);
				return new IIGetIconVisitor(super.visitMethod(access, name, desc, signature, exceptions));
			}
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
		
		@Override
		public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
			YLAsmHelper.logger.info("visitField: " + name);
			return super.visitField(access, name, desc, signature, value);
		}
	}
	
	public static class IIGetIconVisitor extends MethodVisitor {
		public IIGetIconVisitor(MethodVisitor mv) {
			super(ASM4, mv);
		}
		
		@Override
		public void visitCode() {
			super.visitCode();
			label
		}
	}
}
