package yuma140902.yumalib.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import yuma140902.yumalib.asm.util.DumpMethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AdapterContainerWorkbench {
	public static final String CLS_NAME = "net.minecraft.inventory.ContainerWorkbench";
	private static final String SRG_ON_CONTAINER_CLOSED = "func_75134_a";
	private static final String MCP_ON_CONTAINER_CLOSED = "onContainerClosed";
	private static final String DESC_ON_CONTAINER_CLOSED = "(Lnet/minecraft/entity/player/EntityPlayer;)V";
	
	public static class ClassAdapter extends ClassVisitor {
		
		public ClassAdapter(ClassVisitor cv){
			super(ASM4, cv);
		}
		
		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
			if(SRG_ON_CONTAINER_CLOSED.equals(YLAsmHelper.mapMethodName(CLS_NAME, name, desc)) || MCP_ON_CONTAINER_CLOSED.equals(name)) {
				return new OnContainerClosedMethodVisitor(super.visitMethod(access, name, desc, signature, exceptions));
			}
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
		
		@Override
		public void visitEnd() {
			YLAsmHelper.logger.info("Finishing transforming: " + CLS_NAME);
			super.visitEnd();
		}
	}
	
	public static class OnContainerClosedMethodVisitor extends MethodVisitor {
		private MethodVisitor mv;
		public OnContainerClosedMethodVisitor(MethodVisitor mv) {
			super(ASM4, mv);
			this.mv = mv;
		}
		
		private static final String CLS_ENTITY_PLAYER = "net/minecraft/entity/player/EntityPlayer";
		private static final String SRG_DROPITEM = "func_71019_a";
		private static final String MCP_DROPITEM = "dropPlayerItemWithRandomChoice";
		
		@Override
		public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
			// TODO: テスト InvTreakerなどと競合しないかどうかも確認
			
			// INVOKEVIRTUAL player.dropPlayerItemWithRandomChoice(ItemStack, false) -> EntityItem を置き換え
			if(opcode == INVOKEVIRTUAL && CLS_ENTITY_PLAYER.equals(owner) && (SRG_DROPITEM.equals(YLAsmHelper.mapMethodName(owner, name, desc)) || MCP_DROPITEM.equals(name))) {
				YLAsmHelper.logger.info("Injecting code into " + MCP_DROPITEM);
				// player.dropPlayerItemWithRandomChoice(ItemStack, false) 呼び出しの前後は下のようになっている:
				/*
				 * L8
    		 * 	LINENUMBER 78 L8
    		 * 	ALOAD 1  -- player
    		 * 	ALOAD 3  -- itemstack
    		 * 	ICONST_0  -- `false`
    		 * 	INVOKEVIRTUAL net/minecraft/entity/player/EntityPlayer.dropPlayerItemWithRandomChoice (Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;
				 * 	POP      -- 戻り値を無視?
				 */
				// これを下のように書き換える:
				/*
				 * L8
				 * 	LINENUMBER 78 L8
				 * 	ALOAD 1
				 * 	ALOAD 3
				 * 	ICONST_0
				 *  POP
				 * 	POP
				 *  POP      -- 3回POPして ALOAD 1, ALOAD 3, ICONST_0 でプッシュされたplayer.dropPlayerItemWithRandomChoiceのownerと引数をスタックから追い出す
				 *  ALOAD 1  -- player
				 *  ALOAD 3  -- itemstack
				 * 	INVOKESTATIC yuma140902/yumalib/asm/Mixins.insertItemToPlayerOrDrop (Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack)Lnet/minecraft/entity/item/EntityItem;
				 * 	POP
				 */
				mv.visitInsn(POP); mv.visitInsn(POP); mv.visitInsn(POP);
				mv.visitFrame(F_SAME1, 0, null, 1, new Object[]{"net/minecraft/entity/player/EntityPlayer"});  // visitVarInsnに備えてstackmapの型を合わせる
				mv.visitVarInsn(ALOAD, 1);
				mv.visitFrame(F_SAME1, 0, null, 1, new Object[]{"net/minecraft/item/ItemStack"});  // visitVarInsnに備えてstackmapの型を合わせる
				mv.visitVarInsn(ALOAD, 3);
				mv.visitMethodInsn(INVOKESTATIC, "yuma140902/yumalib/asm/Mixins", "insertItemToPlayerOrDrop", "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/item/EntityItem;", false);
			}
			else{
				super.visitMethodInsn(opcode, owner, name, desc, itf);
			}
		}
		
		@Override
		public void visitEnd() {
			mv.visitMaxs(0, 0);
			super.visitEnd();
		}
	}
}
