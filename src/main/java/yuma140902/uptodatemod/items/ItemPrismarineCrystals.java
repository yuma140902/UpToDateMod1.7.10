package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IRegisterable;

public class ItemPrismarineCrystals extends Item implements IRegisterable {
	public ItemPrismarineCrystals() {
		setCreativeTab(CreativeTabs.tabMaterials);
  }
  
  @Override
	public void register() {
		this.setUnlocalizedName(StringUtil.name.domainedUnlocalized("prismarine_crystals"));
		this.setTextureName(StringUtil.name.domainedTexture("prismarine_crystals"));
		GameRegistry.registerItem(this, "prismarine_crystals");
  }
}
