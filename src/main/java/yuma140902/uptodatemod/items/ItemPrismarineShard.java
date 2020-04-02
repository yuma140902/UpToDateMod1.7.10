package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IRegisterable;

public class ItemPrismarineShard extends Item implements IRegisterable{

	public ItemPrismarineShard() {
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	public void register() {
		this.setUnlocalizedName(StringUtil.name.domainedUnlocalized("prismarine_shard"));
		this.setTextureName(StringUtil.name.domainedTexture("prismarine_shard"));
		GameRegistry.registerItem(this, "prismarine_shard");
	}
	
}
