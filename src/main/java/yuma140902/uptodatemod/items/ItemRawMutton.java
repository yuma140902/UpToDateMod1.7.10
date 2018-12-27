package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;

public class ItemRawMutton extends ItemFood implements IRegisterable {
	
	public ItemRawMutton() {
		super(2, true);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(ModUpToDateMod.MOD_ID + ".raw_mutton");
		this.setTextureName(ModUpToDateMod.MOD_ID + ":raw_mutton");
		GameRegistry.registerItem(this, "raw_mutton");
	}
	
}
