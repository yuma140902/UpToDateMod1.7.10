package yuma140902.yumalib.api.blocks;

import net.minecraft.block.Block;

/**
 * ドメインを指定可能な{@link Block.SoundType}
 */
public class CustomSoundType extends Block.SoundType {

	protected final String domain;
	
	public CustomSoundType(String domain, String name, float volume, float frequency) {
		super(name, volume, frequency);
		this.domain = domain;
	}
	
	public CustomSoundType(String domain, String name) {
		this(domain, name, 1.0F, 1.0F);
	}
	
	@Override
	public String getBreakSound() {
		return domain + ":" + super.getBreakSound();
	}
	
	@Override
	public String getStepResourcePath() {
		return domain + ":" + super.getStepResourcePath();
	}
	
	/**
	 * ブロック設置時の音を取得するのに使われると思われる
	 */
	@Override
	public String func_150496_b() {
		return this.getBreakSound();
	}
	
}
