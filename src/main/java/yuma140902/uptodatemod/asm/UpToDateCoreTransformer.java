package yuma140902.uptodatemod.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.launchwrapper.IClassTransformer;

public class UpToDateCoreTransformer implements IClassTransformer {
	
	protected static final String BLOCK_DIRT_CLASS_NAME = "net.minecraft.block.BlockDirt";
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		return applyCoarseDirt(name, transformedName, basicClass);
	}
	
	public byte[] applyCoarseDirt(String name, String transformedName, byte[] basicClass) {
		if (FMLLaunchHandler.side() != Side.CLIENT || !BLOCK_DIRT_CLASS_NAME.equals(transformedName)) {
			return basicClass;
		}
		
		ClassReader cr = new ClassReader(basicClass);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassVisitor cv = new UpToDateCoreAdapter.ClassAdaper(cw);
		cr.accept(cv, 0);
		
		return cw.toByteArray();
	}
	
	
	/**
	 * クラスの名前を難読化(obfuscation)する。
	 */
	public static String unmapClassName(String name) {
		return FMLDeobfuscatingRemapper.INSTANCE.unmap(name.replace('.', '/')).replace('/', '.');
	}
	
	/**
	 * メソッドの名前を易読化(deobfuscation)する。
	 */
	public static String mapMethodName(String owner, String methodName, String desc) {
		return FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(unmapClassName(owner), methodName, desc);
	}
	
	/**
	 * フィールドの名前を易読化(deobfuscation)する。
	 */
	public static String mapFieldName(String owner, String methodName, String desc) {
		return FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(unmapClassName(owner), methodName, desc);
	}
	
	/**
	 * 下の{@link #toDesc(Object)}をMethodのDescriptor用に使えるようにしたもの。
	 * 下手なクラスをここに入れようとするとまずいので確信がない限りStringで入れるべき。
	 * 
	 * @param returnType
	 *          {@link String}型か、{@link Class}型で目的のMethodの返り値の型を指定する。
	 * @param rawDesc
	 *          {@link String}型か、{@link Class}型でMethodの引数たちの型を指定する。
	 * @throws IllegalArgumentException
	 *           引数に{@link String}型か、{@link Class}型以外が入ったら投げられる。
	 * @return Javaバイトコードで扱われる形の文字列に変換されたDescriptor。
	 */
	public static String toDesc(Object returnType, Object... rawDesc) {
		StringBuilder sb = new StringBuilder("(");
		for (Object o : rawDesc) {
			sb.append(toDesc(o));
		}
		sb.append(')');
		sb.append(toDesc(returnType));
		return sb.toString();
	}
	
	/**
	 * {@link Class#forName}とか{@link Class#getCanonicalName()}したりするとまだ読み込まれてなかったりしてまずいので安全策。
	 * 下手なクラスをここに入れようとするとまずいので確信がない限りStringで入れるべき。
	 * 
	 * @param raw
	 *          {@link String}型か、{@link Class}型でASM用の文字列に変換したいクラスを指定する。
	 * @throws IllegalArgumentException
	 *           {@param raw}に{@link String}型か、{@link Class}型以外が入ったら投げられる。
	 * @return Javaバイトコードで扱われる形の文字列に変換されたクラス。
	 */
	public static String toDesc(Object raw) {
		if (raw instanceof Class) {
			Class<?> clazz = (Class<?>) raw;
			return Type.getDescriptor(clazz);
		}
		else if (raw instanceof String) {
			String desc = (String) raw;
			desc = desc.replace('.', '/');
			desc = desc.matches("L.+;") ? desc : "L" + desc + ";";
			return desc;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
