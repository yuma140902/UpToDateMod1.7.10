// Warning: No line numbers available in class file
package moze_intel.projecte.api.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface IBlacklistProxy {
   void blacklistInterdiction(Class<? extends Entity> arg0);

   void blacklistSwiftwolf(Class<? extends Entity> arg0);

   void blacklistTimeWatch(Class<? extends TileEntity> arg0);

   void whitelistNBT(ItemStack arg0);
}

/*
	DECOMPILATION REPORT

	Decompiled from: Z:\dev\mcmod\UpToDateMod\UpToDateMod1.7.10\libs\ProjectE-1.7.10-PE1.10.1-deobf.jar
	Total time: 39 ms
	
	Decompiled with FernFlower.
*/