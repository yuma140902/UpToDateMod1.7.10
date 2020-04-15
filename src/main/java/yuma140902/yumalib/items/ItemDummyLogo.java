package yuma140902.yumalib.items;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.registry.Contexts;

public class ItemDummyLogo extends Item implements IRegisterable {
	@Override
	public void register() {
		this.setUnlocalizedName(Contexts.DEFAULT.nameProvider().domainedUnlocalized("dummy_logo"));
		this.setTextureName(Contexts.DEFAULT.nameProvider().domainedTexture("dummy_logo"));
		GameRegistry.registerItem(this, "dummy_logo");
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {}
}
