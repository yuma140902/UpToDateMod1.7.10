package yuma140902.yumalib.asm.util;

import com.google.common.base.Strings;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.TypePath;
import yuma140902.yumalib.asm.YLAsmHelper;

import java.util.Arrays;

import static org.objectweb.asm.Opcodes.ASM4;

// すべてのメソッドをオーバーライドしているわけではないので注意
public class DumpMethodVisitor extends MethodVisitor {
	public DumpMethodVisitor(MethodVisitor mv) {
		super(ASM4, mv);
	}
	
	@Override
	public void visitParameter(String name, int access) {
		YLAsmHelper.logger.info("mv#param: " + name + ", " + access);
		super.visitParameter(name, access);
	}
	
	@Override
	public void visitAttribute(Attribute attr) {
		YLAsmHelper.logger.info("mv#attrivute: " + attr);
		super.visitAttribute(attr);
	}
	
	@Override
	public void visitCode() {
		YLAsmHelper.logger.info("mv#code");
		super.visitCode();
	}
	
	@Override
	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
		YLAsmHelper.logger.info("mv#frame: {type: " + type + ", nLocal: " + nLocal + ", local: [" + Arrays.toString(local) + "], nStack: " + nStack + ", stack: [" + Arrays.toString(stack) + "]");
		super.visitFrame(type, nLocal, local, nStack, stack);
	}
	
	@Override
	public void visitInsn(int opcode) {
		YLAsmHelper.logger.info("mv#insn: opcode " + opcode);
		super.visitInsn(opcode);
	}
	
	@Override
	public void visitIntInsn(int opcode, int operand) {
		YLAsmHelper.logger.info("mv#intInsn: " + opcode + ", " + operand);
		super.visitIntInsn(opcode, operand);
	}
	
	@Override
	public void visitVarInsn(int opcode, int var) {
		YLAsmHelper.logger.info("mv#varInsn: " + opcode + ", " + var);
		super.visitVarInsn(opcode, var);
	}
	
	@Override
	public void visitTypeInsn(int opcode, String type) {
		YLAsmHelper.logger.info("mv#typeInsn: " + opcode + ", " + type);
		super.visitTypeInsn(opcode, type);
	}
	
	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		YLAsmHelper.logger.info("mv#fieldInsn: " + opcode + ", " + owner + ", " + name + ", " + desc);
		super.visitFieldInsn(opcode, owner, name, desc);
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		YLAsmHelper.logger.info("mv#methodInsn: " + opcode + ", " + owner + ", " + name + ", " + desc + ", " + itf);
		super.visitMethodInsn(opcode, owner, name, desc, itf);
	}
	
	@Override
	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
		YLAsmHelper.logger.info("mv#invokeDynamicInsn: " + name + ", " + desc + ", " + bsm + ", [" + Arrays.toString(bsmArgs) + "]");
		super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
	}
	
	@Override
	public void visitJumpInsn(int opcode, Label label) {
		YLAsmHelper.logger.info("mv#jumpInsn: " + opcode + ", " + label);
		super.visitJumpInsn(opcode, label);
	}
	
	@Override
	public void visitLabel(Label label) {
		YLAsmHelper.logger.info("mv#labelInsn: " + label);
		super.visitLabel(label);
	}
	
	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
		YLAsmHelper.logger.info("mv#localVariable: " + name + ", " + desc + ", " + signature + ", " + start + ", " + end + ", " + index);
		super.visitLocalVariable(name, desc, signature, start, end, index);
	}
	
	@Override
	public void visitEnd() {
		YLAsmHelper.logger.info("mv#end");
		super.visitEnd();
	}
}
