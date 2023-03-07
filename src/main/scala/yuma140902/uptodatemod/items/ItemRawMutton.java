package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IRegisterable;

public class ItemRawMutton extends ItemFood implements IRegisterable {
	
	public ItemRawMutton() {
		super(2, true);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(StringUtil.name.domainedUnlocalized("raw_mutton"));
		this.setTextureName(StringUtil.name.domainedTexture("raw_mutton"));
		GameRegistry.registerItem(this, "raw_mutton");
	}
	
}
