package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.StringUtil;

public class ItemPrismarineCrystals extends Item implements IRegisterable {
	public ItemPrismarineCrystals() {
		setCreativeTab(CreativeTabs.tabMaterials);
  }
  
  public void register() {
		this.setUnlocalizedName(StringUtil.getDomainedUnlocalizedName("prismarine_crystals"));
		this.setTextureName(StringUtil.getDomainedTextureName("prismarine_crystals"));
		GameRegistry.registerItem(this, "prismarine_crystals");
  }
}
