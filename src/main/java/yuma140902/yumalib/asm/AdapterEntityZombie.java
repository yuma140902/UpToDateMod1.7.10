package yuma140902.yumalib.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AdapterEntityZombie {
	public static final String CLS_NAME = "net.minecraft.entity.monster.EntityZombie";
	private static final String SRG_ON_LIVING_UPDATE = "func_70636_d";
	private static final String MCP_ON_LIVING_UPDATE = "onLivingUpdate";
	public static final String BYPASS_SUPER_ON_LIVING_UPDATE = "bypassSuperOnLivingUpdate";
	
	private static boolean isInDevEnv = false;
	
	public static class ClassAdapter extends ClassVisitor {
		
		public ClassAdapter(ClassVisitor cv){
			super(ASM4, cv);
		}
		
		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			if(MCP_ON_LIVING_UPDATE.equals(name)) isInDevEnv = true;  // 開発環境かどうか取得する。Dirty
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
		
		@Override
		public void visitEnd() {
			YLAsmHelper.logger.info("Finishing transforming: " + CLS_NAME);
			MethodVisitor mv = super.visitMethod(ACC_PROTECTED, BYPASS_SUPER_ON_LIVING_UPDATE, "()V", null, null);
			YLAsmHelper.logger.info("Generating code for " + CLS_NAME + "#" + BYPASS_SUPER_ON_LIVING_UPDATE);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "net/minecraft/entity/monster/EntityMob", isInDevEnv ? MCP_ON_LIVING_UPDATE : SRG_ON_LIVING_UPDATE, "()V", false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
			super.visitEnd();
		}
	}
	
	public static class OnLivingUpdateMethodVisitor extends MethodVisitor {
		public OnLivingUpdateMethodVisitor(MethodVisitor mv) {
			super(ASM4, mv);
		}
		
		@Override
		public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
			if(name.equals(BYPASS_SUPER_ON_LIVING_UPDATE)){
				YLAsmHelper.logger.info("Generating code for " + CLS_NAME + "#" + BYPASS_SUPER_ON_LIVING_UPDATE);
				super.visitCode();
				super.visitVarInsn(ALOAD, 0);
				super.visitMethodInsn(INVOKESPECIAL, "net/minecraft/entity/monster/EntityMob", isInDevEnv ? MCP_ON_LIVING_UPDATE : SRG_ON_LIVING_UPDATE, "()V", false);
				super.visitInsn(RETURN);
				super.visitMaxs(0, 0);
				super.visitEnd();
			}
		}
	}
}
