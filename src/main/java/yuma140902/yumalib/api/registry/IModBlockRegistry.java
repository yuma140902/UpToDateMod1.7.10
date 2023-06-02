package yuma140902.yumalib.api.registry;

import net.minecraft.block.Block;

import javax.annotation.Nullable;

/**
 * Mod別に、ブロックが登録されているレジストリの定義
 */
public interface IModBlockRegistry {
	void register(Block block, String key);
	
	@Nullable
	Block get(String key);
}
