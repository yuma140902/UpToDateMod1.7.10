package yuma140902.uptodatemod.asm;

import static org.objectweb.asm.Opcodes.*;
import static yuma140902.uptodatemod.asm.UpToDateCoreTransformer.*;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public final class UpToDateCoreAdapter {
	private UpToDateCoreAdapter() {}
	
	public static class ClassAdaper extends ClassVisitor {
		
		private static final String GETICON_METHOD_NAME = "getIcon";
		private static final String IICON_CLASS_NAME = "net.minecraft.util.IIcon";
		
		public ClassAdaper(ClassVisitor cv) {
			super(ASM4, cv);
		}
		
		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			// BlockDirt#getIcon(int, int)かどうか判定
			if(GETICON_METHOD_NAME.equals(mapMethodName(BLOCK_DIRT_CLASS_NAME, name, desc))
					&& toDesc(IICON_CLASS_NAME, int.class, int.class).equals(desc)) {
				// Adapterを差し込む
				return new MethodAdaper(super.visitMethod(access, name, desc, signature, exceptions));
			}
			
			// Adapterを差し込まずにそのまま返す
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
	}
	
	public static class MethodAdaper extends MethodVisitor {
		public MethodAdaper(MethodVisitor mv) {
			super(ASM4, mv);
		}
		
		@Override
		public void visitCode() {
			// TODO 自動生成されたメソッド・スタブ
			super.visitVarInsn(ALOAD, 0);
			super.visitVarInsn(ALOAD, 1);
			super.visitVarInsn(ALOAD, 2);
		}
	}
}
