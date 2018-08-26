package yuma140902.uptodatemod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.blocks.Stone;

public final class Recipes {
	private Recipes() {}
	
	public static void register() {
		//4つ並べて磨かれた〇〇
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_GRANITE), 
				"##",
				"##",
				'#', new ItemStack(MyBlocks.stone, 1, Stone.META_GRANITE));
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_DIORITE), 
				"##",
				"##",
				'#', new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE));
		
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.stone, 4, Stone.META_SMOOTH_ANDESITE), 
				"##",
				"##",
				'#', new ItemStack(MyBlocks.stone, 1, Stone.META_ANDESITE));
		
		//丸石+ネザー水晶->閃緑岩
		GameRegistry.addRecipe(
				new ItemStack(MyBlocks.stone, 2, Stone.META_DIORITE),
				"SN",
				"NS",
				'S', Blocks.cobblestone,
				'N', Items.quartz
				);
		
		//閃緑岩+丸石->安山岩
		GameRegistry.addShapelessRecipe(
				new ItemStack(MyBlocks.stone, 2, Stone.META_ANDESITE),
				Blocks.cobblestone,
				new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE)
				);
		
		//閃緑岩+ネザー水晶->花崗岩
		GameRegistry.addShapelessRecipe(
				new ItemStack(MyBlocks.stone, 2, Stone.META_GRANITE),
				Items.quartz,
				new ItemStack(MyBlocks.stone, 1, Stone.META_DIORITE)
				);
	}
}
