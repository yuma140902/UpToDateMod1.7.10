// Warning: No line numbers available in class file
package moze_intel.projecte.api.proxy;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface ITransmutationProxy {
   boolean registerWorldTransmutation(Block arg0, int arg1, Block arg2, int arg3, @Nullable Block arg4, int arg5);

   boolean hasKnowledgeFor(UUID arg0, ItemStack arg1);

   List<ItemStack> getKnowledge(UUID arg0);

   
   @Deprecated
   boolean hasFullKnowledge(UUID arg0);

   void addKnowledge(UUID arg0, ItemStack arg1);

   void removeKnowledge(UUID arg0, ItemStack arg1);

   void setEMC(UUID arg0, double arg1);

   double getEMC(UUID arg0);
}

/*
	DECOMPILATION REPORT

	Decompiled from: Z:\dev\mcmod\UpToDateMod\UpToDateMod1.7.10\libs\ProjectE-1.7.10-PE1.10.1-deobf.jar
	Total time: 5 ms
	 @deprecated 
	Decompiled with FernFlower.
*/