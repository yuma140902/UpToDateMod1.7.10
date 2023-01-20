package yuma140902.yumalib.api.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.registry.Contexts;

/**
 * 既存のフルサイズのブロックに対応するフェンスゲートブロック。
 * <p>
 * 普通は継承する必要はない
 * </p>
 */
public class BlockGenericFenceGate extends BlockFenceGate implements IRegisterable {
	private final int iconSourcePlankMeta;
	private final String name;

	/**
	 * @param iconSourcePlankMeta テクスチャとして使うBlockPlankのメタ
	 * @param name 名前。ModIDなし
	 */
	public BlockGenericFenceGate(int iconSourcePlankMeta, String name) {
		super();
		this.iconSourcePlankMeta = iconSourcePlankMeta;
		this.name = name;
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return Blocks.planks.getIcon(p_149691_1_, iconSourcePlankMeta);
	}
	
	@Override
	public void register() {
		this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized(name));
		this.setBlockTextureName(Contexts.current().nameProvider().domainedTexture(name));
		GameRegistry.registerBlock(this, name);
	}
}
