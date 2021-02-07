// Warning: No line numbers available in class file
package moze_intel.projecte.api.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IEMCProxy {
   void registerCustomEMC(ItemStack arg0, int arg1);

   void registerCustomEMC(Object arg0, int arg1);

   boolean hasValue(Block arg0);

   boolean hasValue(Item arg0);

   boolean hasValue(ItemStack arg0);

   int getValue(Block arg0);

   int getValue(Item arg0);

   int getValue(ItemStack arg0);
}

/*
	DECOMPILATION REPORT

	Decompiled from: Z:\dev\mcmod\UpToDateMod\UpToDateMod1.7.10\libs\ProjectE-1.7.10-PE1.10.1-deobf.jar
	Total time: 3 ms
	
	Decompiled with FernFlower.
*/